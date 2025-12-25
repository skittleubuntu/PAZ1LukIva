package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Utilites.Factory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class QuestionDAO {

    private final JdbcOperations jdbc;
    private NoteDAO noteDAO;

    public QuestionDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
        this.noteDAO = Factory.getNoteDao();
    }

    private final RowMapper<Question> questionRowMapper = (rs, rowNum) -> {

        int qidRaw = rs.getInt("id");
        Integer questionId = rs.wasNull() ? null : qidRaw;

        int freqRaw = rs.getInt("idFreq");
        Integer idFreq = rs.wasNull() ? null : freqRaw;

        String difficultText = rs.getString("question_duficult");
        String instrumentText = rs.getString("instrumentType");

        QuestionDifficulty difficult = QuestionDifficulty.valueOf(difficultText);
        InstrumentType instrumentType = InstrumentType.valueOf(instrumentText);

        Note freqNote = (idFreq != null)
                ? noteDAO.getNoteByID(idFreq)
                : null;

        List<Note> notes = noteDAO.loadNotesForQuestion(questionId);

        return new Question(notes, difficult, instrumentType, freqNote);
    };

    public List<Question> loadQuestionForQuiz(int quizId) {
        String sql = "SELECT id, idFreq, question_duficult, instrumentType FROM questions WHERE quizes_id = ?";
        return jdbc.query(sql, questionRowMapper, quizId);
    }

    public void saveQuestion(int quizId, Question q) {

        String sql = """
        INSERT INTO questions (quizes_id, idFreq, question_duficult, instrumentType)
        VALUES (?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, quizId);

            if (q.getRefNote() != null) {
                ps.setInt(2, q.getRefNote().getId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setString(3, q.getDifficult().toString());
            ps.setString(4, q.getInstrumentType().toString());

            return ps;
        }, keyHolder);

        int questionId = keyHolder.getKey().intValue();
        System.out.println("Qid = " + questionId);

        noteDAO.linkNotesToQuestion(questionId, q);
    }

    public void deleteQuestionByQuizID(int quizId) {
        List<Integer> questionIds = jdbc.queryForList(
                "SELECT id FROM questions WHERE quizes_id = ?",
                Integer.class,
                quizId
        );

        for (int id : questionIds) {
            noteDAO.deleteNoteByQuestionID(id);
        }

        jdbc.update("DELETE FROM questions WHERE quizes_id = ?", quizId);
    }
}
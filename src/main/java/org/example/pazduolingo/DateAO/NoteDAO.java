package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.Utilites.Factory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteDAO {

    private final JdbcOperations jdbc;
    private final List<Note> notesCache = new ArrayList<>();

    private final RowMapper<Note> noteRowMapper = (rs, rowNum) ->
            new Note(
                    rs.getInt("id"),
                    rs.getInt("midiNumber"),
                    rs.getString("name"),
                    rs.getInt("octave")
            );

    public NoteDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
        loadNotes();
    }

    private void loadNotes() {
        notesCache.clear();
        notesCache.addAll(jdbc.query("SELECT id, midiNumber, name, octave FROM notes", noteRowMapper));
    }

    public List<Note> getAllNotes() {
        if (notesCache.isEmpty()) {
            loadNotes();
        }
        return notesCache;
    }

    public Note getRandomNote() {
        if (notesCache.isEmpty()) {
            loadNotes();
        }
        Random random = new Random();
        int index = random.nextInt(notesCache.size());
        return notesCache.get(index);
    }

    public Note getNoteByID(int id) {
        if (notesCache.isEmpty()) {
            loadNotes();
        }
        return notesCache.stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Note getNoteByName(String name) {
        if (notesCache.isEmpty()) {
            loadNotes();
        }
        if (name == null || name.equals("None")) {
            return null;
        }

        List<Note> floatNotes = new ArrayList<>();
        for (Note note : notesCache) {
            floatNotes.add(Factory.getFloatNote(note, notesCache));
            if (note.getName().equals(name)) {
                return note;
            }
        }

        return floatNotes.stream()
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void linkNotesToQuestion(int questionId, Question question) {
        String sql = "INSERT INTO questions_has_notes (questions_id, notes_id) VALUES (?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (Note note : question.getNotes()) {
            batchArgs.add(new Object[]{questionId, note.getId()});
        }
        jdbc.batchUpdate(sql, batchArgs);
    }

    public List<Note> loadNotesForQuestion(int questionId) {
        String sql = "SELECT notes_id FROM questions_has_notes WHERE questions_id = ?";
        List<Integer> noteIds = jdbc.queryForList(sql, Integer.class, questionId);
        List<Note> result = new ArrayList<>();
        for (int id : noteIds) {
            Note note = getNoteByID(id);
            if (note != null) {
                result.add(note);
            }
        }
        return result;
    }

    public void deleteNoteByQuestionID(int questionId) {
        String sql = "DELETE FROM questions_has_notes WHERE questions_id = ?";
        jdbc.update(sql, questionId);
    }
}

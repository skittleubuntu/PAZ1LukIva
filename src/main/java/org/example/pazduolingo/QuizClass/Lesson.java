package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Lesson {

    private final List<Quiz> questions = new ArrayList<>();

    public void generateLesson(int sizeOfLesson, int notesPerQuestion) {
        if (sizeOfLesson <= 0 || notesPerQuestion <= 0) return;

        NoteDAO noteDAO = new NoteDAO();
        questions.clear();

        for (int i = 0; i < sizeOfLesson; i++) {
            QuestionType type = getRandomQuestionType();
            Set<Note> notesForQuiz = generateUniqueNotes(noteDAO, notesPerQuestion);
            questions.add(new Quiz(type, notesForQuiz));
        }
    }

    private QuestionType getRandomQuestionType() {
        QuestionType[] types = QuestionType.values();
        return types[new Random().nextInt(types.length)];
    }

    private Set<Note> generateUniqueNotes(NoteDAO noteDAO, int count) {
        Set<Note> notes = new HashSet<>();
        while (notes.size() < count) {
            notes.add(noteDAO.getRandomNote());
        }
        return notes;
    }

    public List<Quiz> getQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Quiz q : questions) {
            sb.append(q).append("\n");
        }
        return sb.toString();
    }
}

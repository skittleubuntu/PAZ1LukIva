package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Quiz {

    private final List<Question> questions = new ArrayList<>();



    public void generateLesson(int sizeOfLesson, int notesPerQuestion) {
        if (sizeOfLesson <= 0 || notesPerQuestion <= 0) return;

        NoteDAO noteDAO = new NoteDAO();
        questions.clear();

        for (int i = 0; i < sizeOfLesson; i++) {

            Set<Note> notesForQuiz = generateUniqueNotes(noteDAO, notesPerQuestion);
            questions.add(new Question(notesForQuiz));
        }
    }



    private Set<Note> generateUniqueNotes(NoteDAO noteDAO, int count) {
        Set<Note> notes = new HashSet<>();
        while (notes.size() < count) {
            notes.add(noteDAO.getRandomNote());
        }
        return notes;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Question q : questions) {
            sb.append(q).append("\n");
        }
        return sb.toString();
    }
}

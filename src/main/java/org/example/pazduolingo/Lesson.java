package org.example.pazduolingo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lesson {

    private List<Quiz> questions = new ArrayList<>();

    public void generateLesson(int sizeOfLesson, int notesPerQuestion) {
        if (sizeOfLesson <= 0 || notesPerQuestion <= 0) return;

        NoteDAO notedao = new NoteDAO();

        questions = new ArrayList<>();

        for (int i = 0; i < sizeOfLesson; i++) {

            QuestionType type;
            int type_n = 1 + (int)(Math.random() * 4);

            switch (type_n) {
                case 1: type = QuestionType.NOTE_READING; break;
                case 2: type = QuestionType.ABSOLUTE_PITCH; break;
                case 3: type = QuestionType.RELATIVE_PITCH; break;
                case 4: type = QuestionType.NOTE_NAMES; break;
                default: type = QuestionType.NOTE_READING;
            }

            // Створюємо набір нот для одного питання
            Set<Note> notesForQuiz = new HashSet<>();
            while (notesForQuiz.size() < notesPerQuestion) {
                notesForQuiz.add(notedao.getRandomNote());
            }

            // Додаємо нове питання у список
            questions.add(new Quiz(type, notesForQuiz));
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Quiz q : questions) {
            sb.append(q).append("\n");
        }
        return sb.toString();
    }


    public List<Quiz> getQuestions() {
        return questions;
    }




}

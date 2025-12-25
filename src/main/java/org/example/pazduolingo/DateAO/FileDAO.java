package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Utilites.Factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDAO {
    private static NoteDAO NoteDAO = Factory.getNoteDao();
    public static void saveQuizToFile(Quiz quiz, File file) {
        if (file == null || quiz == null) return;

        try (PrintWriter pw = new PrintWriter(file)) {

            pw.println(quiz.getName());


            pw.println(quiz.getDescription() != null ? quiz.getDescription() : "");

            for (Question question : quiz.getQuestions()) {
                pw.println(question.getInstrumentType().toString());
                pw.println(question.getDifficult().toString());


                for (Note note : question.getNotes()) {
                    pw.print(note.getId() + " ");
                }
                pw.println();


                pw.println(question.getRefNote() != null ? question.getRefNote().getId() : "-1");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Quiz loadQuizFromFile(File file) {
        List<Question> questions = new ArrayList<>();
        String name;
        String desc;

        if (file == null) return null;

        try (Scanner sc = new Scanner(file)) {

            if (!sc.hasNextLine()) return null;
            name = sc.nextLine();

            if (!sc.hasNextLine()) {
                desc = "";
            } else {
                desc = sc.nextLine();
            }

            while (sc.hasNextLine()) {
                InstrumentType type = InstrumentType.valueOf(sc.nextLine().strip());
                QuestionDifficulty difficulty = QuestionDifficulty.valueOf(sc.nextLine().strip());
                List<Note> notes = new ArrayList<>();


                if (sc.hasNextLine()) {
                    String noteLine = sc.nextLine();
                    Scanner noteScanner = new Scanner(noteLine);
                    for (int i = 0; i < 4; i++) {
                        if (noteScanner.hasNextInt()) {
                            notes.add(NoteDAO.getNoteByID(noteScanner.nextInt()));
                        }
                    }
                    noteScanner.close();
                }


                int refNoteId = -1;
                if (sc.hasNextLine()) {
                    try {
                        refNoteId = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        throw e;
                    }
                }

                Note refNote = (refNoteId != -1) ? NoteDAO.getNoteByID(refNoteId) : null;

                questions.add(new Question(notes, difficulty, type, refNote));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new Quiz(1, questions, name, desc);
    }
}
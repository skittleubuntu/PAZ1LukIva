package org.example.pazduolingo.Main;

import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args){
//         t.println(true);
//        Note a = NoteDAO.getNoteByID(1);
//        Note b = NoteDAO.getNoteByID(2);
//        Note c = NoteDAO.getNoteByID(3);
//        Note d = NoteDAO.getNoteByID(4);
//
//        List<Note> notes = new ArrayList<>() ;
//        notes.add(a);
//        notes.add(b);
//        notes.add(c);
//        notes.add(d);
//
//
//        Note a1 = NoteDAO.getNoteByID(11);
//        Note b2 = NoteDAO.getNoteByID(21);
//        Note c3 = NoteDAO.getNoteByID(31);
//        Note d4 = NoteDAO.getNoteByID(41);
//        List<Note> notes1 = new ArrayList<>() ;
//
//
//
//
//        notes1.add(a1);
//        notes1.add(b2);
//        notes1.add(c3);
//        notes1.add(d4);
//
//        Question question1 = new Question(notes, QuestionDifficult.EASY, InstrumentType.GUITAR, NoteDAO.getRandomNote());
//        Question question2 = new Question(notes, QuestionDifficult.MEDIUM, InstrumentType.VIOLIN,NoteDAO.getRandomNote());
//
//
//
//        List<Question> questions = new ArrayList<>();
//
//        questions.add(question1);
//        questions.add(question2);
//
//         t.println("Questions count: " + questions.size());
//
//        Quiz quiz = new Quiz(questions, "Name", "Des");
//
//
//         t.println(quiz);
//        QuizDAO.saveQuiz(quiz);



        String Theme = "Light";
        int Volume = 100;
        String Type = "#";
        String Language = "English";

        Settings settings = new Settings();
        settings.Theme = Theme;
        settings.Volume = Volume;
        settings.Type = Type;
        settings.Language = Language;



        SqlDAO.dropTables();






    }


}

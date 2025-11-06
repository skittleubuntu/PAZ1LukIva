package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteDAO {

    private static final String DB_URL = "jdbc:sqlite:database";
    private static List<Note> notes = new ArrayList<>();



    public static Note getRandomNote() {
        if (notes.isEmpty()) {
            loadNotes();
        }
        Random random = new Random();
        int index = random.nextInt(notes.size());
        Note randomNote = notes.get(index);
        System.out.println(randomNote.toString());
        return randomNote;
    }


    public static Note getNoteByID(int id){

        for (Note note : notes){
            if(note.getId() == id){
                return note;
            }

        }
        return null;

    }



    private static void loadNotes() {
        notes.clear();
        String sql = "SELECT id, midiNumber, name, octave FROM notes";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int midiNumber = rs.getInt("midiNumber");
                String name = rs.getString("name");
                int octave = rs.getInt("octave");

                notes.add(new Note(id, midiNumber, name, octave));
            }



        } catch (SQLException e) {
            System.err.println("ERR TO CONNECT");
        }
    }

    public static List<Note> getAllNotes() {
        if (notes.isEmpty()) {
            new NoteDAO().loadNotes();
        }
        return notes;
    }



}

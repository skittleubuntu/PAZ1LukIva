package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.Utilites.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteDAO {

    private static final String DB_URL = "jdbc:sqlite:database.db";
    private static List<Note> notes = new ArrayList<>();



    public static Note getRandomNote() {
        if (notes.isEmpty()) {
            loadNotes();
        }
        Random random = new Random();
        int index = random.nextInt(notes.size());
        Note randomNote = notes.get(index);

        return randomNote;
    }


    public static Note getNoteByID(int id){

        if (notes.isEmpty()) {
            loadNotes();
        }
        for (Note note : notes){
            if(note.getId() == id){
                return note;
            }

        }
        return null;

    }

    public static Note getNoteByName(String name){

        if (notes.isEmpty()) {
            loadNotes();
        }
        List<Note> floatNotes = new ArrayList<>();
        if (name == null || name.equals("None")){
            return null;
        }
        for (Note note : notes){
            floatNotes.add(Factory.getFloatNote(note,notes));
            if(note.getName().equals(name)){
                return note;
            }

        }

        for (Note note : floatNotes){
            if(note.getName().equals(name)){
                return note;
            }

        }



        return null;


    }


    public static void linkNotesToQuestion(Connection conn, int questionId, Question question) throws SQLException {
        String insertQuestionNoteSQL = "INSERT INTO questions_has_notes (questions_id, notes_id) VALUES (?, ?)";


        try (PreparedStatement pstmtQN = conn.prepareStatement(insertQuestionNoteSQL)) {

            for (Note note : question.getNotes()) {
                pstmtQN.setInt(1, questionId);
                pstmtQN.setInt(2, note.getId());
                pstmtQN.addBatch();
            }
            pstmtQN.executeBatch();

        }
        catch (
                SQLException e
        )
        {
          
        }

    }


    public static List<Note> loadNotesForQuestion(Connection conn, int qID){
        String sql = "SELECT notes_id FROM questions_has_notes WHERE questions_id = ?;";
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setInt(1,qID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int noteId = rs.getInt("notes_id");
                Note note = getNoteByID(noteId);
                notes.add(note);

            }


        } catch (SQLException e) {
          
            throw new RuntimeException(e);
        }

        return notes;
    }


    public static void deleteNoteByQuestionID(Connection conn, int id){
        String sql =  "DELETE FROM questions_has_notes WHERE questions_id = ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1,id);
            pstm.executeUpdate();
            conn.commit();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
            
        }
    }

    public static List<Note> getAllNotes() {
        if (notes.isEmpty()) {
            loadNotes();
        }
        return notes;
    }



}

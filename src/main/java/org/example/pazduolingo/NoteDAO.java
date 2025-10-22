package org.example.pazduolingo;

import org.example.pazduolingo.Note;

import java.io.*;
import java.util.*;

public class NoteDAO {

    private final InputStream db_notes = getClass().getResourceAsStream("/database/notes.csv");
    private List<Note> notes = new ArrayList<>();

    public NoteDAO() {
        loadNotes();
    }

    public Note getRandomNote(){

        List<Note> noteList = new ArrayList<>(notes);
        Random random = new Random();
        int index = random.nextInt(noteList.size());
        System.out.println(noteList.get(index).toString());
        return noteList.get(index);
    }

    private void loadNotes() {

        InputStream is = getClass().getResourceAsStream("/database/notes.csv");
        if (is == null) {
            System.err.println("file not found in resources/database/notes.csv");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { skipHeader = false; continue; }

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    int midiNumber = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    int octave = Integer.parseInt(parts[3].trim());

                    notes.add(new Note(id, midiNumber, name, octave));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public String getNoteNameById(int id) {
        for (Note n : notes) {
            if (n.getId() == id) {
                return n.getName();
            }
        }
        return null;
    }

    // 3️⃣ Отримати октаву за ID
    public int getOctaveById(int id) {
        for (Note n : notes) {
            if (n.getId() == id) {
                return n.getOctave();
            }
        }
        return -1;
    }

    public int getMidiNumberById(int id) {
        for (Note n : notes) {
            if (n.getId() == id) {
                return n.getMidiNumber();
            }
        }
        return -1;
    }

    //all notes
    public List<Note> getAllNotes() {
        return notes;
    }
}

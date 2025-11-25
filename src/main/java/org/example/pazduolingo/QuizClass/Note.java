package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Settings.Settings;
import org.example.pazduolingo.Utilites.Sounder;

import java.util.List;
import java.util.Objects;

public class Note implements Comparable<Note>{

    private String name;
    private int octave;
    private int id;
    private int midiNumber;

    public Note( int id, int midiNumber,  String name,int octave) {
        this.name = name;
        this.octave = octave;
        this.midiNumber = midiNumber;
        this.id = id;
    }


    private void translate(Settings settings, List<Note> notes){
        if (SettingsDAO.loadSettings().Type.equals("#")){
            return;
        }
        else {
            this.name = notes.get(id).getName() + "♭";
        }

    }


    public String getName() {
        return name + octave;
    }

    public int getOctave() {
        return octave;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return octave == note.octave && id == note.id && Objects.equals(name, note.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, octave, id);
    }

    public int getMidiNumber() {
        return midiNumber;
    }

    public String toString() {
        return name + octave + " (MIDI: " + midiNumber + ", ID: " + id + ")";
    }


    public int compareTo(Note o) {
        return Integer.compare(midiNumber, o.midiNumber);
    }
}

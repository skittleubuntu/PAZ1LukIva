package org.example.hearo.Utilites;

import org.example.hearo.QuizClass.InstrumentType;
import org.example.hearo.QuizClass.Note;

import java.util.List;

public class Factory {

    public static Sounder createSounder(InstrumentType type) {
        int midiInstrument;
        int duration;

        switch (type) {
            case GUITAR -> {
                midiInstrument = 24;
                duration = 800;
            }
            case VIOLIN -> {
                midiInstrument = 40;
                duration = 1200;
            }
            case FLUTE -> {
                midiInstrument = 73;
                duration = 1000;
            }
            case PIANO -> {
                midiInstrument = 0;
                duration = 1000;
            }
            default -> {
                midiInstrument = 0;
                duration = 1000;
            }
        }

        return new Sounder(midiInstrument, duration);
    }

    public static Note getFloatNote(Note note, List<Note> notes){
        String name = "";

            if (note.getNameClear().contains("#")) {

                int nextIndex = note.getId();
                if (nextIndex < notes.size()) {
                    name = notes.get(nextIndex).getNameClear().replace("#", "") + "♭";

                }
            }
            else{
                name = note.getNameClear();
            }


        return new Note(note.getId(), note.getMidiNumber(), name, note.getOctave());

    }
}

package org.example.pazduolingo.Utilites;

import org.example.pazduolingo.QuizClass.InstrumentType;

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
}

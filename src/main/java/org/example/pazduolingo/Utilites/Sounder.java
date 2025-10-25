package org.example.pazduolingo.Utilites;

import javax.sound.midi.*;

public class Sounder {

    private final int instrument; // номер MIDI-інструмента (0 = піаніно)
    private final int durationMs; // тривалість нот

    public Sounder(int instrument, int durationMs) {
        this.instrument = instrument;
        this.durationMs = durationMs;
    }

    public void play(int note, int velocity) {
        try (Synthesizer synth = MidiSystem.getSynthesizer()) {
            synth.open();
            MidiChannel channel = synth.getChannels()[0];

            channel.programChange(instrument); // обираємо інструмент
            channel.noteOn(note, velocity);
            Thread.sleep(durationMs);
            channel.noteOff(note);

        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

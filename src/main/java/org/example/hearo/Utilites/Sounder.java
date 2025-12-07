package org.example.hearo.Utilites;

import javax.sound.midi.*;

public class Sounder {

    private final int instrument;
    private final int durationMs;

    public Sounder(int instrument, int durationMs) {
        this.instrument = instrument;
        this.durationMs = durationMs;
    }

    public void play(int note, int velocity) {
        try (Synthesizer synth = MidiSystem.getSynthesizer()) {
            synth.open();
            MidiChannel channel = synth.getChannels()[0];

            channel.programChange(instrument);
            channel.noteOn(note, velocity);
            Thread.sleep(durationMs);
            channel.noteOff(note);

        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getInstrument(){
        return instrument;
    }
}

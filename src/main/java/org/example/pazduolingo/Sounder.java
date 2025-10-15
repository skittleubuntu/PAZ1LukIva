package org.example.pazduolingo;

import javax.sound.midi.*;

public class Sounder {

    public void play(int note, int velocity) {
        try (Synthesizer synth = MidiSystem.getSynthesizer()) {
            synth.open();
            MidiChannel channel = synth.getChannels()[0];

            channel.noteOn(note, velocity);
            Thread.sleep(1000);
            channel.noteOff(note);

        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

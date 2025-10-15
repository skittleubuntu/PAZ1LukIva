package org.example.pazduolingo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.sound.midi.MidiUnavailableException;

public class Main {



    public static void main(String[] args){
        Sounder player = new Sounder();
        player.play(60, 100);
        player.play(65, 100);
        player.play(70, 100);

    }


}

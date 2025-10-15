package org.example.pazduolingo;

import java.util.Objects;

public class Note {

    private String name;
    private int octave;
    private int id;

    public Note(String name, int octave, int id) {
        this.name = name;
        this.octave = octave;
        this.id = id;
    }

    public void play(Sounder sounder){
        sounder.play(id, 100);
    }

    public String getName() {
        return name;
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
}

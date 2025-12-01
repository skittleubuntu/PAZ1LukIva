package org.example.pazduolingo.Utilities;

import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.QuizClass.InstrumentType;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Functions;
import org.example.pazduolingo.Utilites.Sounder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    @Test
    void testNewTypeSounder() {
        InstrumentType type = InstrumentType.GUITAR;

        //GUITAR = 24
        Sounder sounder = Factory.createSounder(type);
        Sounder sounder1 = new Sounder(24,1000);

        assertEquals(sounder1.getInstrument(),sounder.getInstrument());

    }



    @Test
    void testNewFloatNote(){

        //same Notes but difrent dialect
        Note n1 = new Note(2, 22 ,"A#" ,0);
        Note n1_float = new Note(2, 22, "B♭", 0);
        Note n2 = Factory.getFloatNote(n1, NoteDAO.getAllNotes());

        assertEquals(n2, n1_float);

        //must be equal cuz n3 -> A dont have #
        Note n3 = new Note(1,21,"A", 0);
        Note n4 = Factory.getFloatNote(n3,NoteDAO.getAllNotes());

        assertEquals(n3,n4);





    }


}

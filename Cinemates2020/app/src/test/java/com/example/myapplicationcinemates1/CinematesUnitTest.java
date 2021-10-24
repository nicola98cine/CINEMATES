package com.example.myapplicationcinemates1;

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import org.junit.After;
import org.junit.Before;
//import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

public class CinematesUnitTest {
    UtentiModel u;
    DAOFactory DAO;
    public TestRule rule;
    String userid;
    FilmModel f;
    String tipolista;
    FilmAPIGW fa;
    UtentiAPIGW ua;
    final int testFilmid=166426;

    @Before
    public void setUp() throws Exception {
        ua=new UtentiAPIGW(true);
        fa=new FilmAPIGW(true);
        fa.removeListaFilm("pincopallo@alice.it", Integer.toString(testFilmid), "LISTAPREFERITI");
    }

    @After
    public void reset() throws Exception {
        fa.removeListaFilm("pincopallo@alice.it", Integer.toString(testFilmid), "LISTAPREFERITI");
    }

    @Test
    public void getUtente_T001() {
        System.out.println("CINEMATES GETUTENTE JUNIT TEST T001 (utente presente) ");
        //UtentiAPIGW ua = new UtentiAPIGW(true);
        u = ua.getUtente("pincopallo@alice.it");
        assertNotNull(u);
        assertEquals("pinco", u.getnome());
    }

    @Test
    public void getUtente_T002() {
        System.out.println("CINEMATES GETUTENTE JUNIT TEST T002 (utente NON presente) ");
        //ua=new UtentiAPIGW(true);
        u=ua.getUtente("pinco.pallone@alice.it");
        assertNull(u);
    }

    @Test
    public void addPreferiti_T003() {
        System.out.println("CINEMATES ADDLISTAPREF JUNIT TEST T003 (FILM GIA presente) ");
        //FilmAPIGW fa=new FilmAPIGW(true);
        f=new FilmModel(testFilmid,"null","Pirati dei Caraibi", "Descrizione del FIlm per Unit Test", "Avventura");
        boolean b=  fa.addListaFilm("pincopallo@alice.it", f, "LISTAPREFERITI");
        assertFalse("Film Non inserito , gia presente", b);

    }
    @Test
    public void addPreferiti_T004() {
        System.out.println("CINEMATES ADDLISTAPREF JUNIT TEST T004 (FILM NON presente in LISTA) ");
        //FilmAPIGW fa=new FilmAPIGW(true);
        f=new FilmModel(testFilmid,"null","Pirati dei Caraibi", "Descrizione del FIlm per Unit Test", "Avventura");
        boolean b=  fa.addListaFilm("pincopallo@alice.it", f, "LISTAPREFERITI");
        assertTrue("Errore : Film inserito - Non presente (Risultato atteso presente", b);

    }


}

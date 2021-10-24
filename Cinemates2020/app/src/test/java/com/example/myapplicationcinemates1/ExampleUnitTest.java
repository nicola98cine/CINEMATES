package com.example.myapplicationcinemates1;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public UtentiAPIGW ua;
    UtentiModel u;
    DAOFactory DAO;
    public TestRule rule;

    public FilmAPIGW fa;
    String userid;
    FilmModel f;
    String tipolista;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void getUtente_T001() {
        System.out.println("CINEMATES GETUTENTE JUNIT TEST T001 (utente presente) ");
        UtentiAPIGW ua = new UtentiAPIGW();

        u = ua.getUtente("pincopallo@alice.it");
        assertNotNull(u);
        assertEquals("pinco", u.getnome());
    }

    @Test
    public void getUtente_T002() {
        System.out.println("CINEMATES GETUTENTE JUNIT TEST T002 (utente NON presente) ");
        UtentiAPIGW ua=new UtentiAPIGW();
        u=ua.getUtente("pinco.pallone@alice.it");
        assertNull(u);

    }

    @Test
    public void getFilm_T003() {
        System.out.println("CINEMATES ADDLISTAPREF JUNIT TEST T003 (FILM GIA presente) ");
       FilmAPIGW fa=new FilmAPIGW();
       f=new FilmModel(166426,"https","Pirati dei Caraibi", "Catapultato", "Avventura");
        //fa.addListaFilm("rosario.esposito.1@alice.it", f, "LISTAPREFERITI");
        boolean b=  fa.addListaFilm("rosario.esposito.1@alice.it", f, "LISTAPREFERITI");
        assertEquals(true, b);

    }
    @Test
    public void getFilm_T004() {
        System.out.println("CINEMATES ADDLISTAPREF JUNIT TEST T004 (FILM NON presente in LISTA) ");
        FilmAPIGW fa=new FilmAPIGW();
        f=new FilmModel(166426,"https","Pirati dei Caraibi", "Catapultato", "Avventura");
        //fa.addListaFilm("rosario.esposito.1@alice.it", f, "LISTAPREFERITI");
        boolean b=  fa.addListaFilm("pincopallo@alice.it", f, "LISTAPREFERITI");
        assertEquals(false, b);

    }

}
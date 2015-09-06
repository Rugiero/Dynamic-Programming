/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming.boxstacking.tietorakenteet;

import Sailiot.Laatikko;
import Tietorakenteet.JarjestettyLaatikkoLista;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilari
 */
public class ListaTest {

    public ListaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void lisaaListaan() {

        JarjestettyLaatikkoLista l = new JarjestettyLaatikkoLista();
        assertEquals(0, l.koko());
        l.lisaa(new Laatikko(1, 2, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));

        assertEquals(3, l.koko());

        assertEquals(new Laatikko(1, 2, 3), l.poimi(0));
        assertEquals(new Laatikko(1, 1, 3), l.poimi(l.koko() - 1));

        l = new JarjestettyLaatikkoLista();
        l.lisaa(new Laatikko(1, 2, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(5, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(5, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(3, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(2, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(3, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(1, new Laatikko(1, 1, 3));

        for (int i = 0; i < 15; i++) {
            assertFalse(l.poimi(i) == null);
        }

        assertEquals(15, l.koko());

        JarjestettyLaatikkoLista laatikot = new JarjestettyLaatikkoLista();
        laatikot.lisaa(new Laatikko(1, 1, 1));
        laatikot.lisaa(new Laatikko(1, 1, 1));
        laatikot.lisaa(new Laatikko(1, 1, 1));
        laatikot.lisaa(new Laatikko(1, 1, 1));
        laatikot.lisaa(new Laatikko(1, 1, 1));

        assertEquals(5, l.koko());

    }

    public void lisaaIndeksiin() {

        JarjestettyLaatikkoLista l = new JarjestettyLaatikkoLista();

        l.lisaa(new Laatikko(1, 2, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(1, new Laatikko(1, 1, 1));

        assertEquals(new Laatikko(1, 2, 3), l.poimi(0));
        assertEquals(new Laatikko(2, 1, 1), l.poimi(2));

        assertEquals(new Laatikko(1, 1, 1), l.poimi(1));

    }

    public void poistaListasta() {
        JarjestettyLaatikkoLista l = new JarjestettyLaatikkoLista();
        assertEquals(0, l.koko());
        l.lisaa(new Laatikko(1, 2, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));

        l.poista(0);
        assertEquals(2, l.koko());
        l.poista(2);
        assertEquals(1, l.koko());
        l.poista(2);
        assertEquals(0, l.koko());

    }


    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming.boxstacking;

import static dynamicprogramming.boxstacking.Main.laatikot;
import java.util.ArrayList;
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
public class PakkaaLaatikotTest {

    public PakkaaLaatikotTest() {
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

    @Test
    public void KontinMitat() {

        ArrayList<Laatikko> laatikot = new ArrayList<>();
        laatikot.add(new Laatikko(1, 1, 1));
        laatikot.add(new Laatikko(2, 2, 1));
        laatikot.add(new Laatikko(1, 1, 3));
        laatikot.add(new Laatikko(1, 4, 1));

        PakkaaLaatikot olio = new PakkaaLaatikot(laatikot);
        olio.MaaritaKontinMitat();
        assertEquals(0, olio.kontti.korkeus);
        assertEquals(4, olio.kontti.pituus);
        assertEquals(3, olio.kontti.leveys);

        laatikot = new ArrayList<>();
        laatikot.add(new Laatikko(1, 1, 1));
        laatikot.add(new Laatikko(2, 3, 1));
        laatikot.add(new Laatikko(1, 1, 3));
        laatikot.add(new Laatikko(1, 1, 1));

        olio = new PakkaaLaatikot(laatikot);
        olio.MaaritaKontinMitat();
        assertEquals(0, olio.kontti.korkeus);
        assertEquals(3, olio.kontti.pituus);
        assertEquals(3, olio.kontti.leveys);

    }

    @Test
    public void Jarjestys() {
//        ArrayList<Laatikko> laatikot = new ArrayList<>();
//
//        Laatikko laatikko1 = new Laatikko(1, 1, 1);
//        laatikot.add(laatikko1);
//        Laatikko laatikko9 = new Laatikko(2, 3, 1);
//        laatikot.add(laatikko9);
//        Laatikko laatikko2 = new Laatikko(1, 1, 3);
//        laatikot.add(laatikko2);
//        Laatikko laatikko3 = new Laatikko(1, 4, 1);
//        laatikot.add(laatikko3);
//        Laatikko laatikko4 = new Laatikko(4, 1, 1);
//        laatikot.add(laatikko4);
//
//        ArrayList<Laatikko> jarjestettyTilavuus = new ArrayList<>();
//
//        Laatikko laatikko5 = new Laatikko(1, 1, 1);
//        laatikot.add(laatikko1);
//        Laatikko laatikko6 = new Laatikko(1, 1, 3);
//        laatikot.add(laatikko2);
//        Laatikko laatikko7 = new Laatikko(1, 4, 1);
//        laatikot.add(laatikko3);
//        Laatikko laatikko8 = new Laatikko(4, 1, 1);
//        laatikot.add(laatikko4);
//        Laatikko laatikko10 = new Laatikko(2, 3, 1);
//        laatikot.add(laatikko10);
//
//        PakkaaLaatikot olio = new PakkaaLaatikot(laatikot);
//        olio.JarjestaTilavuudenMukaan();
//
//        assertTrue(olio.laatikot.equals(jarjestettyTilavuus));
//
//        laatikot = new ArrayList<>();
//
//        laatikko1 = new Laatikko(1, 1, 1);
//        laatikot.add(laatikko1);
//        laatikko9 = new Laatikko(2, 3, 1);
//        laatikot.add(laatikko9);
//        laatikko2 = new Laatikko(1, 1, 3);
//        laatikot.add(laatikko2);
//        laatikko3 = new Laatikko(1, 4, 1);
//        laatikot.add(laatikko3);
//        laatikko4 = new Laatikko(4, 1, 1);
//        laatikot.add(laatikko4);
//
//        ArrayList<Laatikko> jarjestettyAla = new ArrayList<>();
//
//        Laatikko laatikko20 = new Laatikko(1, 1, 1);
//        laatikot.add(laatikko20);
//        Laatikko laatikko21 = new Laatikko(1, 1, 3);
//        laatikot.add(laatikko21);
//        Laatikko laatikko22 = new Laatikko(1, 4, 1);
//        laatikot.add(laatikko22);
//        Laatikko laatikko23 = new Laatikko(4, 1, 1);
//        laatikot.add(laatikko23);
//        Laatikko laatikko24 = new Laatikko(2, 3, 1);
//        laatikot.add(laatikko24);
//
//        olio = new PakkaaLaatikot(laatikot);
//        olio.JarjestaTilavuudenMukaan();
//        assertTrue(olio.laatikot.equals(jarjestettyAla));

    }

}

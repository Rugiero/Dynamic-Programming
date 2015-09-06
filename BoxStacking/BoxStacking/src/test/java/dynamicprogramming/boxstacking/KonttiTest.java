/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming.boxstacking;

import Sailiot.Kontti;
import Sailiot.Laatikko;
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
public class KonttiTest {

    public KonttiTest() {
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

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjaTila() {
        Laatikko laatikko = new Laatikko(1, 1, 1);
        Kontti kontti = new Kontti(2, 2, 2);
        kontti.lisaaLaatikko(laatikko);
        kontti.lisaaLaatikko(laatikko);
        kontti.lisaaLaatikko(laatikko);
        kontti.lisaaLaatikko(laatikko);
        kontti.lisaaLaatikko(laatikko);

        assertEquals(3, kontti.LaskeTyhjaTila());

        kontti = new Kontti(2, 2, 2);
        assertEquals(8, kontti.LaskeTyhjaTila());
        kontti.lisaaLaatikko(new Laatikko(1, 1, 2));
        assertEquals(6, kontti.LaskeTyhjaTila());

    }

}

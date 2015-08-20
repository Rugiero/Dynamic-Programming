/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming.boxstacking;

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
public class LaatikkoTest {

    public LaatikkoTest() {
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
    public void OnkoSama() {
        Laatikko laatikko1 = new Laatikko(1, 2, 3);
        Laatikko laatikko2 = new Laatikko(1, 2, 3);
        assertTrue(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 2, 3);
        laatikko2 = new Laatikko(2, 1, 3);
        assertTrue(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 1, 1);
        laatikko2 = new Laatikko(1, 1, 1);
        assertTrue(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 1, 2);
        laatikko2 = new Laatikko(1, 2, 1);
        assertTrue(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 1, 2);
        laatikko2 = new Laatikko(2, 1, 1);
        assertTrue(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 1, 2);
        laatikko2 = new Laatikko(1, 2, 2);
        assertFalse(laatikko1.OnkoSama(laatikko2));

        laatikko1 = new Laatikko(1, 1, 1);
        laatikko2 = new Laatikko(1, 1, 2);
        assertFalse(laatikko1.OnkoSama(laatikko2));

    }

    @Test
    public void sivujenMittoja() {
        Laatikko laatikko1 = new Laatikko(1, 1, 1);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(1, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(1, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

        laatikko1 = new Laatikko(1, 2, 3);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(3, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

        laatikko1 = new Laatikko(2, 1, 3);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(3, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

        laatikko1 = new Laatikko(3, 2, 1);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(3, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

        laatikko1 = new Laatikko(1, 1, 2);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(1, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

        laatikko1 = new Laatikko(1, 2, 2);
        assertEquals(1, laatikko1.LaatikonLyhyimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonPisimmanSivunMitta());
        assertEquals(2, laatikko1.LaatikonToiseksiPisimmanSivunMitta());

    }

    @Test
    public void laatikonKokoja() {
        Laatikko laatikko1 = new Laatikko(1, 1, 1);
        assertEquals(1, laatikko1.LaatikonTilavuus());
        assertEquals(6, laatikko1.LaatikonAla());

        laatikko1 = new Laatikko(2, 1, 3);
        assertEquals(6, laatikko1.LaatikonTilavuus());
        assertEquals(22, laatikko1.LaatikonAla());

        laatikko1 = new Laatikko(1, 2, 3);
        assertEquals(6, laatikko1.LaatikonTilavuus());
        assertEquals(22, laatikko1.LaatikonAla());

        laatikko1 = new Laatikko(1, 3, 2);
        assertEquals(6, laatikko1.LaatikonTilavuus());
        assertEquals(22, laatikko1.LaatikonAla());

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

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
public class TestaaJarjestysta {

    private final JarjestettyLaatikkoLista tilavuusjarjestys = new JarjestettyLaatikkoLista() {
        @Override
        public int vertaa(Laatikko o, Laatikko o1) {
            if(o.LaatikonTilavuus() > o1.LaatikonTilavuus()){
                return 1;
            }
            else {
                return -1;
            }
        }
    };

    private JarjestettyLaatikkoLista alajarjestys = new JarjestettyLaatikkoLista() {
        @Override
        public int vertaa(Laatikko o, Laatikko o1) {
            if (o.LaatikonAla() >= o1.LaatikonAla()) {
                if (o.LaatikonAla() == o1.LaatikonAla()) {
                    //Jos laatikoiden ala on sama, niin ensisijainen laatikko on lyhyimmän sivun omaava.
                    if (o.LaatikonLyhyimmanSivunMitta() < o1.LaatikonLyhyimmanSivunMitta()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return 1;
                }
            }
            return -1;
        }
    };

    public TestaaJarjestysta() {

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

    @Test
    public void jarjestyksia() {
        this.tilavuusjarjestys.lisaa(new Laatikko(1, 1, 1));
        this.tilavuusjarjestys.lisaa(new Laatikko(0.5, 0.5, 3));
        this.tilavuusjarjestys.lisaa(new Laatikko(5, 1, 5));
        this.tilavuusjarjestys.lisaa(new Laatikko(10, 10, 2));

        tilavuusjarjestys.Jarjesta();

        assertTrue(tilavuusjarjestys.poimi(0).LaatikonTilavuus() >= tilavuusjarjestys.poimi(1).LaatikonTilavuus());
        assertTrue(tilavuusjarjestys.poimi(1).LaatikonTilavuus() >= tilavuusjarjestys.poimi(2).LaatikonTilavuus());
        assertTrue(tilavuusjarjestys.poimi(2).LaatikonTilavuus() >= tilavuusjarjestys.poimi(3).LaatikonTilavuus());

        this.alajarjestys.lisaa(new Laatikko(1, 1, 1));
        this.alajarjestys.lisaa(new Laatikko(0.5, 0.5, 3));
        this.alajarjestys.lisaa(new Laatikko(5, 1, 5));
        this.alajarjestys.lisaa(new Laatikko(20, 20, 2));
        alajarjestys.Jarjesta();

        assertTrue(this.alajarjestys.poimi(0).LaatikonAla() > this.alajarjestys.poimi(1).LaatikonAla());
        assertTrue(alajarjestys.poimi(1).LaatikonAla() > alajarjestys.poimi(2).LaatikonAla());
        assertTrue(alajarjestys.poimi(2).LaatikonAla() == alajarjestys.poimi(3).LaatikonAla());
        assertTrue(alajarjestys.poimi(2).LaatikonLyhyimmanSivunMitta() == 0.5);

    }
}

package dynamicprogramming.boxstacking;

import java.util.ArrayList;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public class JarjestaLaatikot {

    //laatikot annetaan listassa
    public ArrayList<int[]> laatikot = new ArrayList<>();
    //kontin annetaan taulukossa leveys, syvyys, korkeus
    public int[] kontti = {0, 0, 0};

    /**
     * Luodaan luokka. Laatikot annetaan Array Listinä taulukoita, joihin on
     * merkitty järjestyksessä laatikon leveys, syvyys ja korkeus,
     *
     * @param laatikot Lista laatikoista
     */
    public JarjestaLaatikot(ArrayList<int[]> laatikot) {
        this.laatikot = laatikot;

    }

    /**
     * Antaa annetuiden laatikoiden pakkausjärjestyksen.
     *
     * @return Laatikoiden indeksit taulukossa pakkausjärjestyksessä.
     */
    public int[] AnnaPakkausajarjestys() {
        int[] pakkausjarjestys = new int[laatikot.size()];
        for (int i = 0; i < this.laatikot.size(); i++) {
            pakkausjarjestys[i] = VAIHE1MaaritaSuurimmanPintaAlanIndeksi(this.laatikot);
            int[] nollataulukko = {0, 0, 0};
            this.laatikot.set(pakkausjarjestys[i], nollataulukko);
        }

        return pakkausjarjestys;
    }

    /**
     * Määrittää kontin mitat aluksi 'suurimman' laatikon mukaan.
     *
     */
    public void MaaritaKontinMitat() {
        //Leveydeksi määritellään pisin laatikoiden sivujen mitta.
        kontti[0] = EtsiPisinMitta(0);
        //Syvyydeksi määritellään toiseksi pisi laatikoiden sivujen mitta.
        kontti[1] = EtsiPisinMitta(1);
        //korkeudeksi määritellään nolla, sitä kasvatetaan pakkaamisen edetessä
        kontti[2] = 0;
    }

    /**
     * Etsii pisimmän sivun mitan laatikoiden joukosta.
     *
     *
     * @param sivu 1=leveys, 2=syvyys, 3=korkeus.
     * @return
     */
    public int EtsiPisinMitta(int sivu) {
        int pisin = 0;
        for (int[] temp : laatikot) {
            if (temp[sivu] > pisin) {
                pisin = temp[0];
            }
        }
        return pisin;
    }

    /**
     * Laskee monennellako laatikolla on suurin pinta-ala. Syöttää laatikon
     * indeksin, ja asettaa (toistaiseksi) laatikon tilavuudeksi nollan.
     * (nollalaatikko {0,0,0})
     *
     * @param laatikot Haluttu lista laatikoista
     * @return Palauttaa laatikoiden joukosta suurimman pinta-alan omaavaan
     * laatikon indeksin.
     */
    public int VAIHE1MaaritaSuurimmanPintaAlanIndeksi(ArrayList<int[]> laatikot) {
        int suurinpinta = 0;
        int suurimmanlaatikonindeksi = 0;
        for (int[] temp : laatikot) {
            //jos pinta-alat ovat samat, valitaan laatikko jolla on pienin korkeus:
            if (temp[0] * temp[1] * temp[2] == suurinpinta) {
                if (temp[2] > laatikot.get(suurimmanlaatikonindeksi)[2]) {
                    suurimmanlaatikonindeksi = this.laatikot.indexOf(temp);
                }
            }
            //jos pinta-ala on suurempi, valitaan uusi indeksi
            if (temp[0] * temp[1] * temp[2] > suurinpinta) {
                suurinpinta = temp[0] * temp[1] * temp[2];
                suurimmanlaatikonindeksi = laatikot.indexOf(temp);
            }
        }

        return suurimmanlaatikonindeksi;
    }

    /**
     * Kokeilee mahtuuko VAIHEESSA 1 valitun laatikon viereen vielä laatikoita.
     * Sijoittaa niin monta kuin mahtuu. (Tämän jälkeen palataan vaiheeseen 1.)
     *
     * @return
     */
    public int VAIHE2KokeileMahtuukoViereen() {
               
        
        
        
        return 0;

    }

}

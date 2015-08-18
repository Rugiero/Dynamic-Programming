package dynamicprogramming.boxstacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public final class JarjestaLaatikot {

    public ArrayList<Laatikko> laatikot;
    public Kontti kontti;
    public ArrayList<Laatikko> tilavuusjarjestys = new ArrayList<>();
    public ArrayList<Laatikko> alajarjestys = new ArrayList<>();

    /**
     * Luodaan luokka. Laatikot annetaan Array Listinä taulukoita, joihin on
     * merkitty järjestyksessä laatikon leveys, syvyys ja korkeus,
     *
     * @param laatikot Lista laatikoista
     */
    public JarjestaLaatikot(ArrayList<Laatikko> laatikot) {
        this.laatikot = laatikot;
        alajarjestys = JarjestaAlanMukaan(this.laatikot);
        tilavuusjarjestys = JarjestaTilavuudenMukaan(this.laatikot);
        MaaritaKontinMitat();

    }

    /**
     * Etsitään laatikoiden joukosta pisimmän sivun mitta, ja määritellään se
     * leveydeksi. Toiseksi pisin sivu määritellään syvyydeksi. Korkeudeksi
     * määritellään aluksi nolla.
     *
     */
    public void MaaritaKontinMitat() {
        int pituus = 0;
        int leveys = 0;
        int temp1;
        int temp2;

        for (Laatikko laatikko : laatikot) {
            Laatikko templaatikko = laatikko;
            temp1 = laatikko.LaatikonPisimmanSivunMitta();
            temp2 = laatikko.LaatikonToiseksiPisimmanSivunMitta();
            if (temp1 >= pituus) {
                pituus = temp1;
                if (temp2 > leveys) {
                    leveys = temp2;
                }
                continue;
            }
            if (temp1 > leveys) {
                leveys = temp1;
            }
        }
        kontti.pituus = pituus;
        kontti.leveys = leveys;
        kontti.korkeus = 0;

    }

    /**
     * Laskee millä laatikolla on suurin pinta-ala. Syöttää laatikon indeksin,
     * ja asettaa aatikon tilavuudeksi nollan.
     */
    public void VAIHE1() {

        while (!alajarjestys.isEmpty()) {

            kontti.korkeus = kontti.korkeus + alajarjestys.get(0).korkeus;
            AsetaLaatikkoVAIHEESSA1();
        }
        System.out.println("Järjestetty!");
    }

    /**
     * Järjestää laatikot alan mukaan.
     *
     */
    public void JarjestaAlanMukaan() {
        alajarjestys = laatikot;
        Comparator<Laatikko> Alajarjestys = new Comparator<Laatikko>() {
            @Override
            public int compare(Laatikko arg0, Laatikko arg1) {
                if (arg0.LaatikonAla() == arg1.LaatikonAla()) {
                    return arg0.korkeus - arg1.korkeus;
                }
                return arg0.LaatikonAla() - arg1.LaatikonAla();
            }
        };
        Collections.sort(alajarjestys, Alajarjestys);

    }

    /**
     * Jarjestää laatikot tilavuusjärjestykseen
     *
     */
    public void JarjestaTilavuudenMukaan() {
        tilavuusjarjestys = laatikot;
        Comparator<Laatikko> Tilavuusjarjestys = new Comparator<Laatikko>() {
            @Override
            public int compare(Laatikko arg0, Laatikko arg1) {
                if (arg0.LaatikonAla() == arg1.LaatikonAla()) {
                    return arg0.korkeus - arg1.korkeus;
                }
                return arg0.LaatikonTilavuus() - arg1.LaatikonTilavuus();
            }
        };
        Collections.sort(tilavuusjarjestys, Tilavuusjarjestys);

    }

    /**
     * Haluamme, että ensimmäisessä vaiheessa suurimman pinta-alan omaava sivu
     * on alaspäin.
     *
     */
    public void AsetaLaatikkoVAIHEESSA1() {
        int[] parhainpain = {0, 0};
        int[] vapaaalue = {0, 0};

        int[] pyoriva = alajarjestys.get(0);
        alajarjestys.remove(0);

        PoistaLaatikkoListasta(tilavuusjarjestys, pyoriva);
        laatikoitalaitettu++;

        //asetetaan laatikko niin, että korkeus on sen lyhyin sivu
        for (int i = 0; i < 3; i++) {
            if (LaatikonLyhyimmansivunMitta(pyoriva) == pyoriva[2]) {
                parhainpain = pyoriva;
            }

        }
        if (tilavuusjarjestys.isEmpty()) {
            return;
        }

        ArrayList<int[]> templaatikot = tilavuusjarjestys;
        vapaaalue[0] = kontti[0] - parhainpain[0];
        vapaaalue[1] = kontti[1] - parhainpain[1];
        tilavuusjarjestys = VAIHE2(templaatikot, vapaaalue);

    }

    /**
     * Asetetaan laatikoita niin kauan, kuin ne loppuvat, tai ylimääräiseen
     * tilaan ei enään mahdu. Aloitetaan suurimmasta laatikosta ja liikutaan
     * pienimpään. Katsotaan mahtuuko.
     *
     * @param templaatikot Pidetään kirjaa käsitellyistä laatikoisat.
     * @param vapaaalue
     * @return
     */
    public ArrayList<int[]> VAIHE2(ArrayList<int[]> templaatikot, int[] vapaaalue) {

        int[] pyoriva = templaatikot.get(0);

        for (int i = 0; i < 3; i++) {
            pyoriva = PyoritaLaatikkoa1(pyoriva);
            //Tyhjän tilan sivujen suuruudet:

            if (pyoriva[0] == vapaaalue[0] && pyoriva[1] == vapaaalue[1]) {
                templaatikot.remove(0);
                PoistaLaatikkoListasta(alajarjestys, pyoriva);
                laatikoitalaitettu++;
                return templaatikot;
            }

            if (pyoriva[0] < vapaaalue[0] && pyoriva[1] < vapaaalue[1] && pyoriva[2] <= kontti[2]) {
                templaatikot.remove(0);
                PoistaLaatikkoListasta(alajarjestys, pyoriva);
                laatikoitalaitettu++;
                //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                int[] vapaaalue1 = {vapaaalue[0], vapaaalue[1] - pyoriva[1]};
                int[] vapaaalue2 = {vapaaalue[0] - pyoriva[0], pyoriva[1]};

                VAIHE2(templaatikot, vapaaalue1);
                VAIHE2(templaatikot, vapaaalue2);

            }

        }
        pyoriva = templaatikot.get(0);

        for (int i = 0; i < 3; i++) {
            pyoriva = PyoritaLaatikkoa2(pyoriva);
            //Tyhjän tilan sivujen suuruudet:

            if (pyoriva[0] == vapaaalue[0] && pyoriva[1] == vapaaalue[1]) {
                templaatikot.remove(0);
                PoistaLaatikkoListasta(alajarjestys, pyoriva);
                laatikoitalaitettu++;
                return templaatikot;
            }

            if (pyoriva[0] < vapaaalue[0] && pyoriva[1] < vapaaalue[1] && pyoriva[2] <= kontti[2]) {

                templaatikot.remove(0);
                PoistaLaatikkoListasta(alajarjestys, pyoriva);
                laatikoitalaitettu++;
                int[] vapaaalue1 = {vapaaalue[0], vapaaalue[1] - pyoriva[1]};
                int[] vapaaalue2 = {vapaaalue[0] - pyoriva[0], pyoriva[1]};

                VAIHE2(templaatikot, vapaaalue1);
                VAIHE2(templaatikot, vapaaalue2);
            }

        }
        return templaatikot;
    }

    /**
     * Poistaa laatikon listasta, jos se löytyy jotenkin päin sieltä. Käytetään pitämään kokojärjestyslistat ajantasalla.
     *
     *
     * @param lista
     * @param poistettava
     */
    public void PoistaLaatikkoListasta(ArrayList<Laatikko> lista, Laatikko poistettava) {
        for (Laatikko laatikko : lista) {
            if (poistettava.equals(laatikko)) {
                lista.remove(laatikko);
            }

        }
    }
}

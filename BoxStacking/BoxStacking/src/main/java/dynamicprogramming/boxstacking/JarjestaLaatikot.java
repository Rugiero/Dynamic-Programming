package dynamicprogramming.boxstacking;

import java.util.ArrayList;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public final class JarjestaLaatikot {

    //laatikot annetaan listassa
    public ArrayList<int[]> laatikot = new ArrayList<>();
    //kontin annetaan taulukossa leveys, syvyys, korkeus
    public int[] kontti = {0, 0, 0};
    public int[] pakkausjarjestys = new int[laatikot.size()];
    public int laatikoitalaitettu = 0;
    public ArrayList<int[]> tilavuusjarjestys = new ArrayList<>();
    public ArrayList<int[]> alajarjestys = new ArrayList<>();

    /**
     * Luodaan luokka. Laatikot annetaan Array Listinä taulukoita, joihin on
     * merkitty järjestyksessä laatikon leveys, syvyys ja korkeus,
     *
     * @param laatikot Lista laatikoista
     */
    public JarjestaLaatikot(ArrayList<int[]> laatikot) {
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
        int leveys = 0;
        int syvyys = 0;
        for (int[] laatikko : laatikot) {
            for (int i = 0; i < 3; i++) {
                if (laatikko[i] > leveys) {
                    leveys = laatikko[i];
                }
            }
        }
        //toiseksi pisin:
        for (int[] laatikko : laatikot) {
            for (int i = 0; i < 3; i++) {
                if (laatikko[i] > syvyys && syvyys < leveys) {
                    syvyys = laatikko[i];
                }
            }
        }
        this.kontti[0] = leveys;
        this.kontti[1] = syvyys;
        this.kontti[2] = 0;

    }

    /**
     * Pyörittää laatikkoa niin, että leveys tulee syvyydeksi, syvyys
     * korkeudeksi, ja korkeus leveydeksi. Yhdessä PyöritäLaatikkoa2 kanssa
     * saadaan kaikki mahdolliset (6 kpl) asennot.
     *
     * @param laatikko Pyöritettevä laatikko.
     * @return
     */
    public int[] PyoritaLaatikkoa1(int[] laatikko) {
        int[] pyoritetty = {0, 0, 0};
        pyoritetty[1] = laatikko[0];
        pyoritetty[2] = laatikko[1];
        pyoritetty[0] = laatikko[2];

        return pyoritetty;

    }

    /**
     * Pyörittää laatikkoa niin, että leveyden ja syvyyden paikkaa vaihdetaan
     * alussa, muuten sama. Yhdessä PyöritäLaatikkoa1 kanssa saadaan kaikki
     * mahdolliset (6kpl) asennot.
     *
     *
     * @param laatikko Pyöritettävä laatikko.
     * @return
     */
    public int[] PyoritaLaatikkoa2(int[] laatikko) {
        int[] pyoritetty = {0, 0, 0};
        pyoritetty[2] = laatikko[0];
        pyoritetty[1] = laatikko[1];
        pyoritetty[0] = laatikko[2];

        return pyoritetty;

    }

    /**
     * Palauttaa lyhyimmän sivun mitan.Tarvitaan määriteltäessä korkeutta.
     *
     * @param laatikko
     * @return
     */
    public int LaatikonLyhyimmansivunMitta(int[] laatikko) {
        return Math.min(Math.min(laatikko[0], laatikko[1]), laatikko[2]);
    }

    /**
     * Laskee millä laatikolla on suurin pinta-ala. Syöttää laatikon indeksin,
     * ja asettaa aatikon tilavuudeksi nollan.
     */
    public void VAIHE1() {

        while (!alajarjestys.isEmpty()) {

            this.kontti[2] = this.kontti[2] + LaatikonLyhyimmansivunMitta(alajarjestys.get(0));
            AsetaLaatikkoVAIHEESSA1();
        }
        System.out.println("Järjestetty!");
    }

    /**
     * Järjestää laatikot alan mukaan.
     *
     * @param laatikot halutut laatikot
     * @return palauttaa laatikot järjestettynä
     */
    public ArrayList<int[]> JarjestaAlanMukaan(ArrayList<int[]> laatikot) {

        int suurinala = 0;
        ArrayList<int[]> apulista = laatikot;
        int[] suurinalalaatikko = {0, 0, 0};
        ArrayList<int[]> alajarjestyss = new ArrayList<>();
        while (!apulista.isEmpty()) {
            for (int[] temp : laatikot) {

                //jos pinta-alat ovat samat, valitaan laatikko jolla on lyhyin sivun mitta (joka asetetaan korkeudeksi).
                if ((2 * (temp[0] * temp[1]) + 2 * (temp[0] * temp[2]) + 2 * (temp[1] * temp[2])) == suurinala && LaatikonLyhyimmansivunMitta(temp) < LaatikonLyhyimmansivunMitta(suurinalalaatikko)) {

                    suurinalalaatikko = temp;

                }
                //jos pinta-ala on suurempi, valitaan uusi indeksi
                if ((2 * (temp[0] * temp[1]) + 2 * (temp[0] * temp[2]) + 2 * (temp[1] * temp[2])) > suurinala) {
                    suurinala = temp[0] * temp[1] * temp[2];
                    suurinalalaatikko = temp;
                }

            }
            alajarjestyss.add(suurinalalaatikko);
            apulista.remove(suurinalalaatikko);

        }
        return alajarjestyss;

    }

    /**
     * Jarjestää laatikot tilavuusjärjestykseen
     *
     * @param laatikot
     * @return
     * @para
     */
    public ArrayList<int[]> JarjestaTilavuudenMukaan(ArrayList<int[]> laatikot) {
        ArrayList<int[]> jarjestys = new ArrayList<>();
        int suurintilavuus = 0;
        ArrayList<int[]> apulista = laatikot;
        int[] suurintilavuuslaatikko = {0, 0, 0};

        while (!apulista.isEmpty()) {
            for (int[] temp : laatikot) {

                if (temp[0] * temp[1] * temp[2] > suurintilavuus) {
                    suurintilavuus = temp[0] * temp[1] * temp[2];
                    suurintilavuuslaatikko = temp;
                }

            }
            jarjestys.add(suurintilavuuslaatikko);
            apulista.remove(suurintilavuuslaatikko);

        }
        return jarjestys;

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
        if(tilavuusjarjestys.isEmpty()){
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

                templaatikot = VAIHE2(templaatikot, vapaaalue1);
                templaatikot = VAIHE2(templaatikot, vapaaalue2);

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

                templaatikot = VAIHE2(templaatikot, vapaaalue1);
                templaatikot = VAIHE2(templaatikot, vapaaalue2);
            }

        }
        return templaatikot;
    }

    /**
     * Poistaa laatikon listasta, jos se löytyy jotenkin päin sieltä.
     *
     *
     * @param lista
     * @param poistettava
     */
    public void PoistaLaatikkoListasta(ArrayList<int[]> lista, int[] poistettava) {
        int[] pyoriva = poistettava;
        for (int i = 0; i < 3; i++) {
            pyoriva = PyoritaLaatikkoa1(pyoriva);
            if (lista.contains(pyoriva)) {
                lista.remove(pyoriva);
            }
        }
        for (int i = 0; i < 3; i++) {
            pyoriva = PyoritaLaatikkoa2(pyoriva);
            if (lista.contains(pyoriva)) {
                lista.remove(pyoriva);
            }
        }

    }
}

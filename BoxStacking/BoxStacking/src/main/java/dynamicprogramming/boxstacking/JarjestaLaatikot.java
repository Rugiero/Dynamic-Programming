package dynamicprogramming.boxstacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public class JarjestaLaatikot {

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
        kontti = new Kontti();

    }

    public void Aloita() {
        JarjestaAlanMukaan();
        JarjestaTilavuudenMukaan();
        MaaritaKontinMitat();
        VAIHE1();
        System.out.println("Järjestetty!");
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

        if (!alajarjestys.isEmpty()) {

            kontti.korkeus = kontti.korkeus + alajarjestys.get(0).korkeus;
            AsetaLaatikkoVAIHEESSA1();
        }

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
                    return arg1.korkeus - arg0.korkeus;
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
        //Ensimmäiseksi otetaan suurimman alan omaava laatikko:
        Laatikko pyoriva = alajarjestys.get(0);
        Laatikko laatikko = pyoriva;
        alajarjestys.remove(0);
        PoistaLaatikkoListasta(tilavuusjarjestys, pyoriva);

        //asetetaan laatikko niin, että korkeus on sen lyhyin sivu
        for (int i = 0; i < 3; i++) {
            pyoriva.PyoritaLaatikkoa1();
            if (pyoriva.LaatikonLyhyimmanSivunMitta() == pyoriva.korkeus && pyoriva.leveys <= kontti.leveys && kontti.pituus <= kontti.pituus) {
                laatikko = pyoriva;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            pyoriva.PyoritaLaatikkoa2();
            if (pyoriva.LaatikonLyhyimmanSivunMitta() == pyoriva.korkeus && pyoriva.leveys <= kontti.leveys && pyoriva.pituus <= kontti.pituus) {
                laatikko = pyoriva;
                break;
            }
        }

        Laatikko vapaaalue = new Laatikko(kontti.pituus - laatikko.pituus, kontti.leveys - laatikko.leveys, kontti.korkeus);
        VAIHE2(vapaaalue);

    }

    /**
     * Asetetaan laatikoita niin kauan, kuin ne loppuvat, tai ylimääräiseen
     * tilaan ei enään mahdu. Aloitetaan suurimmasta laatikosta ja
     * liikutaanmplaatikot Pidetään kirjaa käsitellyistä laatikoisat.
     *
     * @param vapaaalue
     */
    public void VAIHE2(Laatikko vapaaalue) {
        //välttääksemme ConcurrentModification-virheen, alustamme temp- muuttujan.
        ArrayList<Laatikko> temp = tilavuusjarjestys;

        for (Laatikko laatikko : temp) {
            for (int i = 0; i < 3; i++) {

                //Jos laatikko menee yksiyhteen, palataan
                if (laatikko.pituus == vapaaalue.pituus && laatikko.leveys == vapaaalue.leveys && laatikko.korkeus <= vapaaalue.korkeus) {
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);
                    PoistaLaatikkoListasta(alajarjestys, laatikko);
                    return;

                }

                if (laatikko.pituus < vapaaalue.pituus && laatikko.leveys < vapaaalue.leveys && laatikko.korkeus <= vapaaalue.korkeus) {
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);
                    PoistaLaatikkoListasta(alajarjestys, laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue1 = new Laatikko(vapaaalue.pituus, vapaaalue.pituus - laatikko.pituus, vapaaalue.korkeus);
                    Laatikko vapaaalue2 = new Laatikko(vapaaalue.pituus - laatikko.pituus, laatikko.leveys, vapaaalue.korkeus);

                    VAIHE2(vapaaalue1);
                    VAIHE2(vapaaalue2);

                }
                laatikko.PyoritaLaatikkoa1();

            }

            for (int i = 0; i < 3; i++) {

                //Jos laatikko menee yksiyhteen, palataan
                if (laatikko.pituus == vapaaalue.pituus && laatikko.leveys == vapaaalue.leveys && laatikko.korkeus <= vapaaalue.korkeus) {
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);
                    PoistaLaatikkoListasta(alajarjestys, laatikko);
                    return;

                }

                if (laatikko.pituus < vapaaalue.pituus && laatikko.leveys < vapaaalue.leveys && laatikko.korkeus <= vapaaalue.korkeus) {
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);
                    PoistaLaatikkoListasta(alajarjestys, laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue1 = new Laatikko(vapaaalue.pituus, vapaaalue.pituus - laatikko.pituus, vapaaalue.korkeus);
                    Laatikko vapaaalue2 = new Laatikko(vapaaalue.pituus - laatikko.pituus, laatikko.leveys, vapaaalue.korkeus);

                    VAIHE2(vapaaalue1);
                    VAIHE2(vapaaalue2);

                }
                laatikko.PyoritaLaatikkoa1();

            }
        }
        VAIHE1();
    }

    /**
     * Poistaa laatikon listasta, jos se löytyy jotenkin päin sieltä. Käytetään
     * pitämään kokojärjestyslistat ajantasalla.
     *
     *
     * @param lista
     * @param poistettava
     */
    public void PoistaLaatikkoListasta(ArrayList<Laatikko> lista, Laatikko poistettava) {

        for (Laatikko laatikko : lista) {
            if (poistettava.OnkoSama(laatikko)) {
                poistettava = laatikko;
            }

        }
        lista.remove(poistettava);
    }
}

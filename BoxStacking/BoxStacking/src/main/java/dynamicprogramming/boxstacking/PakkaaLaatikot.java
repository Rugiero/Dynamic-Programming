package dynamicprogramming.boxstacking;

import dynamicprogramming.boxstacking.tietorakenteet.LaatikkoLista;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public final class PakkaaLaatikot {

    public Kontti kontti;
    private final LaatikkoLista tilavuusjarjestys = new LaatikkoLista();
    private final LaatikkoLista alajarjestys = new LaatikkoLista();

    /**
     * Luodaan luokka. Laatikot annetaan Array Listinä taulukoita, joihin on
     * merkitty järjestyksessä laatikon leveys, syvyys ja korkeus,
     *
     * @param laatikot
     */
    public PakkaaLaatikot(LaatikkoLista laatikot) {
        JarjestaLaatikot(laatikot);
        kontti = new Kontti(0, 0, 0);

    }

    /**
     * Jarjestaa laatikot tilavuus- ja alajärjestykseen. ja alajarjestykseen.
     * Lisäksi alajärjestyksessä on ehto että pienimmän sivun(asetetaan
     * korkeudeksi vaiheessa 1) omaava laatikko asetetaan ensimmäiseksi.
     *
     * @param laatikot
     */
    public void JarjestaLaatikot(LaatikkoLista laatikot) {
        for (int k = 0; k < Integer.MAX_VALUE; k++) {

            if (k >= laatikot.koko()) {
            
                break;
            }
            Laatikko laatikko = laatikot.poimi(k);

            if (tilavuusjarjestys.koko() == 0) {
                tilavuusjarjestys.lisaa(laatikko);
                alajarjestys.lisaa(laatikko);
                continue;
            }

            for (int i = 0; i <= tilavuusjarjestys.koko(); i++) {

                if (i == tilavuusjarjestys.koko()) {
                    tilavuusjarjestys.lisaa(laatikko);
//                    System.out.println("ulos" + i);

                    break;
                }

                Laatikko laatikko1 = tilavuusjarjestys.poimi(i);
//                   System.out.println(tilavuusjarjestys.koko());
//                   System.out.println(i);
//                   System.out.println(tilavuusjarjestys.poimi(i));
               
                if (laatikko.LaatikonTilavuus() >= laatikko1.LaatikonTilavuus()) {
                    
                    tilavuusjarjestys.lisaaIndeksiin(i, laatikko);
               
                    break;
                }

            }
            for (int j = 0; j <= alajarjestys.koko(); j++) {

                if (j == alajarjestys.koko()) {
                    alajarjestys.lisaa(laatikko);
//                    System.out.println("ulos" + j);

                    break;
                }

                Laatikko laatikko1 = alajarjestys.poimi(j);

                if (laatikko.LaatikonAla() >= laatikko1.LaatikonAla()) {
                    if (laatikko.LaatikonTilavuus() == laatikko1.LaatikonTilavuus()) {
                        if (laatikko.LaatikonLyhyimmanSivunMitta() < laatikko1.LaatikonLyhyimmanSivunMitta()) {
                            alajarjestys.lisaaIndeksiin(j, laatikko);
//                            System.out.println("alaanuusilaatikko");
                            break;
                        } else {
                            alajarjestys.lisaaIndeksiin(j + 1, laatikko);
//                            System.out.println("alaanuusilaatikko");
                            break;
                        }
                    } else {
                        alajarjestys.lisaaIndeksiin(j, laatikko);
//                        System.out.println("alaanuusilaatikko");
                        break;
                    }
                }

            }
        }

    }

    public void Aloita() {

        for (int i = 0; i < alajarjestys.koko(); i++) {

            System.out.println(alajarjestys.poimi(i).LaatikonAla());
        }
        System.out.println("______");
        for (int i = 0; i < tilavuusjarjestys.koko(); i++) {
            System.out.println(tilavuusjarjestys.poimi(i).LaatikonTilavuus());
        }
        MaaritaKontinMitat();

        VAIHE1();
        System.out.println("kontin leveys" + kontti.leveys);
        System.out.println("kontin korkeus" + kontti.korkeus);
        System.out.println("kontin pituus" + kontti.pituus);
        System.out.println(kontti.LaatikonTilavuus());
        System.out.println("tyhjatila:" + kontti.LaskeTyhjaTila());

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

        for (int i = 0; i < tilavuusjarjestys.koko(); i++) {

            if (tilavuusjarjestys.poimi(i).LaatikonPisimmanSivunMitta() >= pituus) {
                leveys = pituus;
                pituus = tilavuusjarjestys.poimi(i).LaatikonPisimmanSivunMitta();

                if (tilavuusjarjestys.poimi(i).LaatikonToiseksiPisimmanSivunMitta() > leveys) {
                    leveys = tilavuusjarjestys.poimi(i).LaatikonToiseksiPisimmanSivunMitta();
                    continue;
                }
                continue;

            }
            if (tilavuusjarjestys.poimi(i).LaatikonPisimmanSivunMitta() > leveys) {
                leveys = tilavuusjarjestys.poimi(i).LaatikonPisimmanSivunMitta();
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

        while (alajarjestys.koko() != 0) {

            kontti.korkeus = kontti.korkeus + alajarjestys.poimi(0).LaatikonLyhyimmanSivunMitta();
            AsetaLaatikkoVAIHEESSA1();
        }

    }

    /**
     * Haluamme, että ensimmäisessä vaiheessa suurimman pinta-alan omaava sivu
     * on alaspäin.
     *
     */
    public void AsetaLaatikkoVAIHEESSA1() {
        //Ensimmäiseksi otetaan suurimman alan omaava laatikko:
        int vapaaaluekorkeus = alajarjestys.poimi(0).LaatikonLyhyimmanSivunMitta();
        Laatikko pyoriva = alajarjestys.poimi(0);
        Laatikko laatikko = pyoriva;
        alajarjestys.poista(0);
        PoistaLaatikkoListasta(tilavuusjarjestys, pyoriva);

        //asetetaan laatikko niin, että korkeus on sen lyhyin sivu
        for (int i = 0; i < 3; i++) {
            pyoriva.PyoritaLaatikkoa();
            if (pyoriva.LaatikonLyhyimmanSivunMitta() == pyoriva.korkeus && pyoriva.leveys <= kontti.leveys && kontti.pituus <= kontti.pituus) {
                laatikko = pyoriva;
                break;
            }
        }
        pyoriva.KaannaLaatikko90astetta();
        for (int i = 0; i < 3; i++) {
            pyoriva.PyoritaLaatikkoa();
            if (pyoriva.LaatikonLyhyimmanSivunMitta() == pyoriva.korkeus && pyoriva.leveys <= kontti.leveys && pyoriva.pituus <= kontti.pituus) {
                laatikko = pyoriva;
                break;
            }
        }
        kontti.lisaaLaatikko(laatikko);
        Laatikko vapaaalue1 = new Laatikko(kontti.pituus, kontti.leveys - laatikko.leveys, vapaaaluekorkeus);
        Laatikko vapaaalue2 = new Laatikko(kontti.pituus - laatikko.pituus, kontti.leveys, vapaaaluekorkeus);

        VAIHE2(vapaaalue1, vapaaalue2);

    }

    /**
     * Asetetaan laatikoita niin kauan, kun ne loppuvat, tai ylimääräiseen
     * tilaan ei enään mahdu. Aloitetaan suurimmasta laatikosta. Pidetään kirjaa
     * käsitellyistä laatikoista.
     *
     * @param vapaaalue1
     * @param vapaaalue2
     */
    public void VAIHE2(Laatikko vapaaalue1, Laatikko vapaaalue2) {
        System.out.println("sisään" + vapaaalue1.leveys + " " + vapaaalue1.pituus + " " + vapaaalue2.leveys + "  " + vapaaalue2.pituus);

        outerloop:
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
            System.out.println("j:" + j);

            if (j >= tilavuusjarjestys.koko()) {
                break;
            }

            Laatikko laatikko = tilavuusjarjestys.poimi(j);

//liian pientä laatikkoa on turha yrittää asettaa mitenkään päin:
            if (laatikko.LaatikonTilavuus() > vapaaalue1.LaatikonTilavuus() && laatikko.LaatikonTilavuus() > vapaaalue2.LaatikonTilavuus()) {
                continue;
            }

            for (int i = 0; i < 6; i++) {

                if (i == 2) {
                    laatikko.KaannaLaatikko90astetta();
                }
                if (laatikko.pituus <= vapaaalue1.pituus && laatikko.leveys <= vapaaalue1.leveys && laatikko.korkeus <= vapaaalue1.korkeus) {
                    PoistaLaatikkoListasta(alajarjestys, laatikko);
                    kontti.lisaaLaatikko(laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue1.pituus, vapaaalue1.leveys - laatikko.leveys, vapaaalue1.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue1.pituus - laatikko.pituus, vapaaalue1.leveys, vapaaalue1.korkeus);
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);

                    System.out.println("yksi" + vapaaalue1.leveys + vapaaalue1.pituus + vapaaalue2.leveys + vapaaalue2.pituus);
                    System.out.println(vapaaalue11.leveys + " " + vapaaalue11.pituus + " " + vapaaalue22.leveys + "  " + vapaaalue22.pituus);
                    VAIHE2(vapaaalue11, vapaaalue22);
                    //Alueesta poistetaan vapaaalue1 kanssa leikkaava alue:
                    vapaaalue2 = new Laatikko(vapaaalue2.pituus, vapaaalue2.leveys - vapaaalue1.leveys, vapaaalue1.korkeus);
                    //alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                    vapaaalue1 = new Laatikko(0, 0, 0);
                    j--;
                    System.out.println("j:" + j);
                    continue outerloop;

                }
                if (laatikko.pituus <= vapaaalue2.pituus && laatikko.leveys <= vapaaalue2.leveys && laatikko.korkeus <= vapaaalue2.korkeus) {
                    PoistaLaatikkoListasta(alajarjestys, laatikko);
                    kontti.lisaaLaatikko(laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue2.pituus, vapaaalue2.leveys - laatikko.leveys, vapaaalue2.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue2.pituus - laatikko.pituus, vapaaalue2.leveys, vapaaalue2.korkeus);
                    PoistaLaatikkoListasta(tilavuusjarjestys, laatikko);

                    System.out.println("kaksi" + vapaaalue1.leveys + vapaaalue1.pituus + vapaaalue2.leveys + vapaaalue2.pituus);
                    System.out.println("kaksikakkaa" + vapaaalue11.leveys + " " + vapaaalue11.pituus + " " + vapaaalue22.leveys + "  " + vapaaalue22.pituus);
                    VAIHE2(vapaaalue11, vapaaalue22);
                    //Alueesta poistetaan vapaaalue2 kanssa leikkaava alue:
                    vapaaalue1 = new Laatikko(vapaaalue1.pituus - vapaaalue2.pituus, vapaaalue1.leveys, vapaaalue1.korkeus);
                    //Alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                    vapaaalue2 = new Laatikko(0, 0, 0);
                    j--;
                    System.out.println("j:" + j);
                    continue outerloop;

                }
                System.out.println("rullatirullaa");
                laatikko.PyoritaLaatikkoa();

            }

        }
        System.out.println("pois");

    }

    /**
     * Asettaa laatikon vapaalle alueelle.
     *
     * @param laatikko
     * @param vapaaalue
     */
//    public void AsetaLaatikko(Laatikko laatikko, Laatikko vapaaalue) {
//
//        PoistaLaatikkoListasta(alajarjestys, laatikko);
//        kontti.lisaaLaatikko(laatikko);
//
//        //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
//        Laatikko vapaaalue1 = new Laatikko(vapaaalue.pituus, vapaaalue.pituus - laatikko.pituus, vapaaalue.korkeus);
//        Laatikko vapaaalue2 = new Laatikko(vapaaalue.pituus - laatikko.pituus, laatikko.leveys, vapaaalue.korkeus);
//
//        VAIHE2(vapaaalue1, vapaaalue2);
//
//    }
    /**
     * Poistaa laatikon listasta, jos se löytyy jotenkin päin sieltä. Käytetään
     * pitämään kokojärjestyslistat ajantasalla.
     *
     * @param lista
     * @param poistettava
     */
    public void PoistaLaatikkoListasta(LaatikkoLista lista, Laatikko poistettava) {
        int poistoindeksi = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (poistettava.OnkoSama(lista.poimi(i))) {
                poistoindeksi = i;
                break;

            }

        }
        lista.poista(poistoindeksi);
    }
}

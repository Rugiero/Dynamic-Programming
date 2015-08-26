package dynamicprogramming.boxstacking;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Luokkaan tulee kaikki tarvittavat metodit laatikoiden järjestämiseksi
 * mahdollisimman pieneen tilaan. Luokka käyttää luokkamuuttujia.
 */
public final class PakkaaLaatikot {

    public Kontti kontti;
    public ArrayList<Laatikko> tilavuusjarjestys = new ArrayList<>();
    public ArrayList<Laatikko> alajarjestys = new ArrayList<>();

    /**
     * Luodaan luokka. Laatikot annetaan Array Listinä taulukoita, joihin on
     * merkitty järjestyksessä laatikon leveys, syvyys ja korkeus,
     *
     * @param laatikot
     */
    public PakkaaLaatikot(ArrayList<Laatikko> laatikot) {
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
    public void JarjestaLaatikot(ArrayList<Laatikko> laatikot) {
        for (Laatikko laatikko : laatikot) {

            if (tilavuusjarjestys.isEmpty()) {
                tilavuusjarjestys.add(laatikko);
                alajarjestys.add(laatikko);
                continue;
            }
            Iterator<Laatikko> iterator = tilavuusjarjestys.iterator();
            while (true) {
                Laatikko laatikko1 = iterator.next();
                if (laatikko.LaatikonTilavuus() >= laatikko1.LaatikonTilavuus()) {
                    tilavuusjarjestys.add(tilavuusjarjestys.indexOf(laatikko1), laatikko);
                    break;
                }
                if (!iterator.hasNext()) {
                    tilavuusjarjestys.add(laatikko);

                    break;
                }

            }
            iterator = alajarjestys.iterator();
            while (true) {
                Laatikko laatikko1 = iterator.next();
                if (laatikko.LaatikonAla() >= laatikko1.LaatikonAla()) {
                    if (laatikko.LaatikonTilavuus() == laatikko1.LaatikonTilavuus()) {
                        if (laatikko.LaatikonLyhyimmanSivunMitta() < laatikko1.LaatikonLyhyimmanSivunMitta()) {
                            alajarjestys.add(alajarjestys.indexOf(laatikko1), laatikko);
                            break;
                        } else {
                            alajarjestys.add(alajarjestys.indexOf(laatikko1) + 1, laatikko);
                            break;
                        }
                    } else {
                        alajarjestys.add(alajarjestys.indexOf(laatikko1), laatikko);
                        break;
                    }
                }
                if (!iterator.hasNext()) {
                    alajarjestys.add(laatikko);
                    break;
                }

            }
        }

    }

    public void Aloita() {

        for (Laatikko laatikko : alajarjestys) {
            System.out.println(laatikko.LaatikonAla());
        }
        System.out.println("______");
        for (Laatikko laatikko : tilavuusjarjestys) {
            System.out.println(laatikko.LaatikonTilavuus());
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

        for (Laatikko laatikko : tilavuusjarjestys) {

            if (laatikko.LaatikonPisimmanSivunMitta() >= pituus) {
                leveys = pituus;
                pituus = laatikko.LaatikonPisimmanSivunMitta();

                if (laatikko.LaatikonToiseksiPisimmanSivunMitta() > leveys) {
                    leveys = laatikko.LaatikonToiseksiPisimmanSivunMitta();
                    continue;
                }
                continue;

            }
            if (laatikko.LaatikonPisimmanSivunMitta() > leveys) {
                leveys = laatikko.LaatikonPisimmanSivunMitta();
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

            kontti.korkeus = kontti.korkeus + alajarjestys.get(0).LaatikonLyhyimmanSivunMitta();
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
        int vapaaaluekorkeus = alajarjestys.get(0).LaatikonLyhyimmanSivunMitta();
        Laatikko pyoriva = alajarjestys.get(0);
        Laatikko laatikko = pyoriva;
        alajarjestys.remove(0);
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
        Laatikko vapaaalue1 = new Laatikko(kontti.pituus, kontti.pituus - laatikko.pituus, vapaaaluekorkeus);
        Laatikko vapaaalue2 = new Laatikko(kontti.pituus - laatikko.pituus, laatikko.leveys, vapaaaluekorkeus);
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

        Iterator<Laatikko> iterator = tilavuusjarjestys.iterator();
        while (iterator.hasNext()) {
            Laatikko laatikko = iterator.next();
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
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue1.pituus, vapaaalue1.pituus - laatikko.pituus, vapaaalue1.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue1.pituus - laatikko.pituus, laatikko.leveys, vapaaalue1.korkeus);
                    iterator.remove();
                    VAIHE2(vapaaalue11, vapaaalue22);
                    //alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                    
                    return;
                }
                if (laatikko.pituus <= vapaaalue2.pituus && laatikko.leveys <= vapaaalue2.leveys && laatikko.korkeus <= vapaaalue2.korkeus) {
                    PoistaLaatikkoListasta(alajarjestys, laatikko);
                    kontti.lisaaLaatikko(laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue2.pituus, vapaaalue2.pituus - laatikko.pituus, vapaaalue2.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue2.pituus - laatikko.pituus, laatikko.leveys, vapaaalue2.korkeus);
                    iterator.remove();
                    VAIHE2(vapaaalue11, vapaaalue22);
                    //alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                 
                    return;
                }
                laatikko.PyoritaLaatikkoa();
            }

        } 

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

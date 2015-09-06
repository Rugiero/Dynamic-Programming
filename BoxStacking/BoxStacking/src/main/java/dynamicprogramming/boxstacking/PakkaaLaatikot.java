package dynamicprogramming.boxstacking;

import Sailiot.Kontti;
import Sailiot.Laatikko;
import Tietorakenteet.JarjestettyLaatikkoLista;

/**
 * Luokka järjestää laatikot LAFF 'Largest area first firt'- algoritmin
 * mukaisesti. Halutessaan saa luokasta ulos laatikoiden järjestyksen sekä
 * niiden asennon.
 *
 */
public final class PakkaaLaatikot {

    public Kontti kontti;
    private JarjestettyLaatikkoLista tilavuusjarjestys = new JarjestettyLaatikkoLista() {
        @Override
        public int vertaa(Laatikko o, Laatikko o1) {
            if (o.LaatikonTilavuus() < o1.LaatikonTilavuus()) {
                return 1;
            } else {
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
    /*
     *Konstruktori lähinnä testaamista varten.
     *
     *
     */

    public PakkaaLaatikot(JarjestettyLaatikkoLista laatikot) {
        System.out.println("laatikoiden koko" + laatikot.koko());
        for (int i = 0; i < laatikot.koko(); i++) {
            tilavuusjarjestys.lisaa(laatikot.poimi(i));
            alajarjestys.lisaa(laatikot.poimi(i));
        }

        tilavuusjarjestys.Jarjesta();
        alajarjestys.Jarjesta();
        kontti = new Kontti(0, 0, 0);

    }
    /*
     *Pakkaa annetun listan laatikoita.
     *
     *
     */

    public void aloita() {
        
        maaritaKontinMitat();
        vaihe1();
    }

    /**
     * Etsitään laatikoiden joukosta pisimmän sivun mitta, ja määritellään se
     * leveydeksi. Toiseksi pisin sivu määritellään syvyydeksi. Korkeudeksi
     * määritellään aluksi nolla.
     *
     */
    public void maaritaKontinMitat() {
        double pituus = 0;
        double leveys = 0;
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
    public void vaihe1() {

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
        double vapaaaluekorkeus = alajarjestys.poimi(0).LaatikonLyhyimmanSivunMitta();
        Laatikko pyoriva = alajarjestys.poimi(0);
        Laatikko laatikko = pyoriva;
        alajarjestys.poista(0);
        tilavuusjarjestys.poistaLaatikko(pyoriva);

        //asetetaan laatikko niin, että korkeus on sen lyhyin sivu
        for (int i = 0; i < 6; i++) {

            pyoriva.PyoritaLaatikkoa();
            if (pyoriva.LaatikonLyhyimmanSivunMitta() == pyoriva.korkeus && pyoriva.leveys <= kontti.leveys && kontti.pituus <= kontti.pituus) {
                laatikko = pyoriva;
                break;
            }

            if (i == 2) {
                laatikko.KaannaLaatikko90astetta();
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

        outerloop:
        for (int j = 0; j < Integer.MAX_VALUE; j++) {

            if (j >= tilavuusjarjestys.koko()) {
                break;
            }

            Laatikko laatikko = tilavuusjarjestys.poimi(j);

//Liian pientä laatikkoa on turha yrittää asettaa mitenkään päin:
            if (laatikko.LaatikonTilavuus() > vapaaalue1.LaatikonTilavuus() && laatikko.LaatikonTilavuus() > vapaaalue2.LaatikonTilavuus()) {
                continue;
            }

            for (int i = 0; i < 6; i++) {
                laatikko.PyoritaLaatikkoa();

                if (laatikko.pituus <= vapaaalue1.pituus && laatikko.leveys <= vapaaalue1.leveys && laatikko.korkeus <= vapaaalue1.korkeus) {
                    alajarjestys.poistaLaatikko(laatikko);
                    kontti.lisaaLaatikko(laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue1.pituus, vapaaalue1.leveys - laatikko.leveys, vapaaalue1.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue1.pituus - laatikko.pituus, vapaaalue1.leveys, vapaaalue1.korkeus);
                    tilavuusjarjestys.poistaLaatikko(laatikko);

                    VAIHE2(vapaaalue11, vapaaalue22);
                    //Alueesta poistetaan vapaaalue1 kanssa leikkaava alue:
                    vapaaalue2 = new Laatikko(vapaaalue2.pituus, vapaaalue2.leveys - vapaaalue1.leveys, vapaaalue1.korkeus);
                    //alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                    vapaaalue1 = new Laatikko(0, 0, 0);
                    j--;

                    continue outerloop;

                }
                if (laatikko.pituus <= vapaaalue2.pituus && laatikko.leveys <= vapaaalue2.leveys && laatikko.korkeus <= vapaaalue2.korkeus) {
                    alajarjestys.poistaLaatikko(laatikko);
                    kontti.lisaaLaatikko(laatikko);

                    //kun mahtuva laatikko sijoitetaan, vapaiden alueiden alat:
                    Laatikko vapaaalue11 = new Laatikko(vapaaalue2.pituus, vapaaalue2.leveys - laatikko.leveys, vapaaalue2.korkeus);
                    Laatikko vapaaalue22 = new Laatikko(vapaaalue2.pituus - laatikko.pituus, vapaaalue2.leveys, vapaaalue2.korkeus);
                    tilavuusjarjestys.poistaLaatikko(laatikko);

                    VAIHE2(vapaaalue11, vapaaalue22);
                    //Alueesta poistetaan vapaaalue2 kanssa leikkaava alue:
                    vapaaalue1 = new Laatikko(vapaaalue1.pituus - vapaaalue2.pituus, vapaaalue1.leveys, vapaaalue1.korkeus);
                    //Alue on rekursion jälkeen täytetty mahdollisimman täyteen:
                    vapaaalue2 = new Laatikko(0, 0, 0);
                    j--;

                    continue outerloop;

                }
                if (i == 2) {
                    laatikko.KaannaLaatikko90astetta();
                }

            }

        }
    }
}

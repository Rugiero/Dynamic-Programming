package dynamicprogramming.boxstacking;

/**
 * Laaitkolla on ominaisuudet : pituus, leveys, syvyys.
 *
 * @author iangervu@cs
 */
public class Laatikko {

    public int pituus;
    public int leveys;
    public int korkeus;

    public Laatikko(int pituus, int leveys, int korkeus) {
        this.pituus = pituus;
        this.leveys = leveys;
        this.korkeus = korkeus;

    }

    /**
     * Pyörittää laatikkoa. Yhdessä PyoritaLaatikkoa2 kanssa saadaan kaikki
     * mahdolliset kuusi asentoa.
     *
     * @author iangervu@cs
     */
    public void PyoritaLaatikkoa1() {
        this.pituus = this.leveys;
        this.leveys = this.korkeus;
        this.korkeus = this.pituus;

    }

    /**
     * Pyörittää laatikkoa. Yhdessä PyoritaLaatikkoa1 kanssa saadaan kaikki
     * mahdolliset kuusi asentoa. Leveys pysyy tässä samana, sillä aluksi
     * "käännetään" laatikkoa niin että pituus ja leveys vaihtaa paikkaa, sitten
     * pyöritetään vastaavasti kuin edellisessä.
     *
     * @author iangervu@cs
     */
    public void PyoritaLaatikkoa2() {
        this.pituus = this.korkeus;
        this.korkeus = this.pituus;

    }

    public int LaatikonLyhyimmanSivunMitta() {
        return Math.min(Math.min(this.pituus, this.leveys), this.korkeus);
    }

    public int LaatikonPisimmanSivunMitta() {
        return Math.max(Math.max(this.pituus, this.leveys), this.korkeus);

    }

    public int LaatikonToiseksiPisimmanSivunMitta() {
        int temppituus = this.pituus;
        int templeveys = this.leveys;
        int tempkorkeus = this.korkeus;
        boolean lyhyinmerkitty = false;
        boolean pisinmerkitty = false;

        int[] templaatikot = {temppituus, templeveys, tempkorkeus};
        for (int i = 0; i < 3; i++) {
            if (lyhyinmerkitty && pisinmerkitty) {
                break;
            }

            if (templaatikot[i] == LaatikonLyhyimmanSivunMitta() && lyhyinmerkitty == false) {
                templaatikot[i] = 0;
                lyhyinmerkitty = true;
            }
            if (templaatikot[i] == LaatikonPisimmanSivunMitta() && pisinmerkitty == false) {
                templaatikot[i] = 0;
                pisinmerkitty = true;
            }

        }
        return Math.max(Math.max(templaatikot[0], templaatikot[1]), templaatikot[2]);
    }

    public int LaatikonAla() {
        return 2 * (pituus * leveys) + 2 * (pituus * korkeus) + 2 * (korkeus * leveys);
    }

    public int LaatikonTilavuus() {
        return pituus * korkeus * leveys;
    }

    
    
    public boolean equals(Laatikko o) {
        if (o instanceof Laatikko) {
            Laatikko c = (Laatikko) o;
            boolean pisinmitta = (c.LaatikonPisimmanSivunMitta() == o.LaatikonPisimmanSivunMitta());
            boolean lyhyinmitta = (c.LaatikonLyhyimmanSivunMitta() == o.LaatikonLyhyimmanSivunMitta());
            boolean keskimitta = (c.LaatikonToiseksiPisimmanSivunMitta() == o.LaatikonToiseksiPisimmanSivunMitta());
            
            
            
            if (pisinmitta && lyhyinmitta && keskimitta) 
            {
                return true;
            }
        }
        return false;
    }

}

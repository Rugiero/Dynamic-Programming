package Sailiot;

/**
 * Laaitkolla on ominaisuudet : pituus, leveys, syvyys.
 *
 * @author iangervu@cs
 */
public class Laatikko {

    public double pituus;
    public double leveys;
    public double korkeus;

    public Laatikko(double pituus, double leveys, double korkeus) {
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
    public void PyoritaLaatikkoa() {
        double temppituus = this.pituus;
        this.pituus = this.leveys;
        this.leveys = this.korkeus;
        this.korkeus = temppituus;

    }

    /**
     * Pyörittää laatikkoa. Yhdessä PyoritaLaatikkoa1 kanssa saadaan kaikki
     * mahdolliset kuusi asentoa. Leveys pysyy tässä samana, sillä aluksi
     * "käännetään" laatikkoa niin että pituus ja leveys vaihtaa paikkaa, sitten
     * pyöritetään vastaavasti kuin edellisessä.
     *
     * @author iangervu@cs
     */
    public void KaannaLaatikko90astetta() {
        double temppituus = this.pituus;
        this.pituus = this.leveys;
        this.leveys = temppituus;

    }

    /**
     *
     * @author iangervu@cs
     * @return Lyhyimmän sivun mitta.
     */
    public double LaatikonLyhyimmanSivunMitta() {
        return Math.min(Math.min(this.pituus, this.leveys), this.korkeus);
    }

    /**
     *
     * @author iangervu@cs
     * @return Laatikon pisimmän sivun mitta
     */
    public double LaatikonPisimmanSivunMitta() {
        return Math.max(Math.max(this.pituus, this.leveys), this.korkeus);

    }

    /**
     *
     * @author iangervu@cs
     * @return Toiseksi pisimmän sivun mitta.
     */
    public double LaatikonToiseksiPisimmanSivunMitta() {
        double temppituus = this.pituus;
        double templeveys = this.leveys;
        double tempkorkeus = this.korkeus;
        boolean lyhyinmerkitty = false;
        boolean pisinmerkitty = false;

        double[] laatikko = {temppituus, templeveys, tempkorkeus};
        for (int i = 0; i < 3; i++) {
            if (lyhyinmerkitty && pisinmerkitty) {
                break;
            }

            if (laatikko[i] == LaatikonLyhyimmanSivunMitta() && lyhyinmerkitty == false) {
                laatikko[i] = 0;
                lyhyinmerkitty = true;
            }
            if (laatikko[i] == LaatikonPisimmanSivunMitta() && pisinmerkitty == false) {
                laatikko[i] = 0;
                pisinmerkitty = true;
            }

        }
        return Math.max(Math.max(laatikko[0], laatikko[1]), laatikko[2]);
    }

    /**
     *
     * @author iangervu@cs
     * @return Laatikon ala.
     */
    public double LaatikonAla() {
        return 2 * (pituus * leveys) + 2 * (pituus * korkeus) + 2 * (korkeus * leveys);
    }

    /**
     *
     * @author iangervu@cs
     * @return Laatikon tilavuus.
     */
    public double LaatikonTilavuus() {
        return pituus * korkeus * leveys;
    }

    /**
     * Palauttaa true jos laatikot ovat indenttiset.
     *
     * @author iangervu@cs
     * @param o
     * @return
     */
    public boolean OnkoSama(Laatikko o) {

        boolean pisinmitta = (LaatikonPisimmanSivunMitta() == o.LaatikonPisimmanSivunMitta());
        boolean lyhyinmitta = (LaatikonLyhyimmanSivunMitta() == o.LaatikonLyhyimmanSivunMitta());
        boolean keskimitta = (LaatikonToiseksiPisimmanSivunMitta() == o.LaatikonToiseksiPisimmanSivunMitta());
        return pisinmitta && lyhyinmitta && keskimitta;
    }

}

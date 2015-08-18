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
    public int LaatikonLyhyimmansivunMitta() {
        return Math.min(Math.min(this.pituus, this.leveys), this.korkeus);
    }

}

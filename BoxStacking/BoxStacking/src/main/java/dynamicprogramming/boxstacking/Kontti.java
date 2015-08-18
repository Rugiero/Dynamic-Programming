package dynamicprogramming.boxstacking;

import java.util.ArrayList;

/**
 * Kontilla on ominaisuudet leveys, syvyys pituus. Pidetään kirjaa myös tyhjästä
 * tilasta.
 *
 * @author iangervu@cs
 */
public class Kontti {

    public int pituus;
    public int leveys;
    public int korkeus;
    public int tyhjaatilaa;
    public ArrayList<Laatikko> laatikot;

    public Kontti(int pituus, int leveys, int korkeus) {
        this.pituus = pituus;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.tyhjaatilaa = 0;
        this.laatikot = new ArrayList<>();
    }

    public void LisaaLaatikko(Laatikko laatikko){
        this.laatikot.add(laatikko);
    }
    
    
    
    
    
    
    
}

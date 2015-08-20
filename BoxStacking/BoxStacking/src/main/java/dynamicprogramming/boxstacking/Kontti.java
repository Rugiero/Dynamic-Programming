package dynamicprogramming.boxstacking;

import java.util.ArrayList;

/**
 * Kontilla on ominaisuudet leveys, syvyys pituus. Pidetään kirjaa myös tyhjästä
 * tilasta.
 *
 * @author iangervu@cs
 */
public class Kontti extends Laatikko {


    private int tyhjaatilaa;
    private final ArrayList<Laatikko> laatikot;


    public Kontti(int pituus, int leveys, int korkeus) {
        super(pituus, leveys, korkeus);
        laatikot = new ArrayList<>();
    }
    public int LaskeTyhjaTila() {
        int laatikoidentilavuus = 0;
        for(Laatikko laatikko:laatikot) {
            laatikoidentilavuus += laatikko.LaatikonTilavuus();
        }
 
        return LaatikonTilavuus() - laatikoidentilavuus;
    }
    public void lisaaLaatikko(Laatikko laatikko) {
        
        laatikot.add(laatikko);
    }
   
    



}

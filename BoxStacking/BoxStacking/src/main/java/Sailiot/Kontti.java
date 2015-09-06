package Sailiot;

import Sailiot.Laatikko;
import java.util.ArrayList;

/**
 * Kontilla on ominaisuudet leveys, syvyys pituus. Pidetään kirjaa myös tyhjästä
 * tilasta.
 *
 * @author iangervu@cs
 */
public class Kontti extends Laatikko {



    public final ArrayList<Laatikko> laatikot;


    public Kontti(double pituus, double leveys, double korkeus) {
        super(pituus, leveys, korkeus);
        laatikot = new ArrayList<>();
    }
    public double LaskeTyhjaTila() {
        int laatikoidentilavuus = 0;
        for(Laatikko laatikko:laatikot) {
            laatikoidentilavuus += laatikko.LaatikonTilavuus();
        }
 
        return this.LaatikonTilavuus() - laatikoidentilavuus;
    }
    public void lisaaLaatikko(Laatikko laatikko) {
        
        laatikot.add(laatikko);
    }
   
    



}

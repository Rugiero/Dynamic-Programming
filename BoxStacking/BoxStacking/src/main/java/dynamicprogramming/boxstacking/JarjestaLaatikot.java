
package dynamicprogramming.boxstacking;


import java.util.ArrayList;

public class JarjestaLaatikot {

    //annetaan laatikot taulukossa leveys, syvyys, korkeus.

    public int[] laatikko = new int[3];
    //laatikot annetaan listassa
    public ArrayList<int[]> laatikot = new ArrayList<>();
    //kontin annetaan taulukossa leveys, syvyys, korkeus
    public int[] kontti = new int[3];

    public int[] AnnaPakkausajarjestys(ArrayList<int[]> laatikot) {
        int[] pakkausjarjestys = new int[laatikot.size()];
        this.laatikot = laatikot;
        for (int i = 0; i < this.laatikot.size(); i++) {
            pakkausjarjestys[i] = MaaritaSuurimmanPintaAlanIndeksi(this.laatikot);
        }
        return pakkausjarjestys;
    }

    public void MaaritaKontinMitat(int[] kontti, ArrayList<int[]> laatikot) {
        //Leveydeksi määritellään pisin laatikoiden sivujen mitta.
        kontti[0] = EtsiPisinMitta(0, laatikot);
        //Syvyydeksi määritellään toiseksi pisi laatikoiden sivujen mitta.
        kontti[1] = EtsiPisinMitta(1, laatikot);
        //korkeudeksi määritellään nolla, sitä kasvatetaan pakkaamisen edetessä
        kontti[2] = 0;
    }

    public int EtsiPisinMitta(int sivu, ArrayList<int[]> laatikot) {
        int pisin = 0;
        for (int[] temp : laatikot) {
            if (temp[sivu] > pisin) {
                pisin = temp[0];
            }
        }
        return pisin;
    }

    public int MaaritaSuurimmanPintaAlanIndeksi(ArrayList<int[]> laatikot) {
        int suurinpinta = 0;
        int suurimmanlaatikonindeksi = 0;
        for (int[] temp : laatikot) {
            //jos pinta-alat ovat samat, valitaan laatikko jolla on pienin korkeus:
            if (temp[0] * temp[1] * temp[2] == suurinpinta) {
                if (temp[2] > laatikot.get(suurimmanlaatikonindeksi)[2]) {
                    suurimmanlaatikonindeksi = this.laatikot.indexOf(temp);
                }
            }
            //jos pinta-ala on suurempi, valitaan uusi indeksi
            if (temp[0] * temp[1] * temp[2] > suurinpinta) {
                suurinpinta = temp[0] * temp[1] * temp[2];
                suurimmanlaatikonindeksi = laatikot.indexOf(temp);
            }
        }

        int[] nollataulukko = {0, 0, 0};
        this.laatikot.set(suurimmanlaatikonindeksi, nollataulukko);
        return suurimmanlaatikonindeksi;
    }

}

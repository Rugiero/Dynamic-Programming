/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import dynamicprogramming.boxstacking.tietorakenteet.JarjestettyLaatikkoLista;

public class Main {

    //private static LaatikkoLista laatikot = new LaatikkoLista();
    //laatikot annetaan listassa
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException {
        JarjestettyLaatikkoLista laatikot = new JarjestettyLaatikkoLista();
        laatikot.lisaa(new Laatikko(2, 2, 2));
        laatikot.lisaa(new Laatikko(1, 9, 1));
        laatikot.lisaa(new Laatikko(1, 3, 6));
        laatikot.lisaa(new Laatikko(4, 1, 2));
        laatikot.lisaa(new Laatikko(1, 1, 1));
             laatikot.lisaa(new Laatikko(2, 3, 1));
          laatikot.lisaa(new Laatikko(1, 2, 1));
       
        PakkaaLaatikot ads = new PakkaaLaatikot(laatikot);
        ads.Aloita();
        
        System.out.println(ads.kontti.LaskeTyhjaTila());
        
       

    }
}

//
//    public static void Laatikonlisays(LaatikkoLista laatikot) {
//        Scanner input = new Scanner(System.in);
//        int[] laatikonmitat = new int[3];
//        System.out.println("Anna mitat leveys, syvyys, korkeus");
//        for (int i = 0; i < 3; i++) {
//            int luku = input.nextInt();
//            laatikonmitat[i] = luku;
//        }
//
//        Laatikko laatikko = new Laatikko(laatikonmitat[0], laatikonmitat[1], laatikonmitat[2]);
//        laatikot.lisaa(laatikko);
//        input.reset();
//    }


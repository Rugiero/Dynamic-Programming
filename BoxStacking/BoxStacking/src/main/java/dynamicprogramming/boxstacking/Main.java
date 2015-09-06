/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import Sailiot.Laatikko;
import Tietorakenteet.JarjestettyLaatikkoLista;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static JarjestettyLaatikkoLista laatikot = new JarjestettyLaatikkoLista();
    //laatikot annetaan listassa
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException {
       // JarjestettyLaatikkoLista laatikot = new JarjestettyLaatikkoLista();
//        Random random = new Random();
//        for (int i = 0; i < 51; i++) {
//            laatikot.lisaa(new Laatikko(random.nextInt(10) + 1, random.nextInt(10) + 1, random.nextInt(10) + 1));
//            laatikot.lisaa(new Laatikko(1, 9, 1));
//            laatikot.lisaa(new Laatikko(1, 3, 6));
//            laatikot.lisaa(new Laatikko(4, 1, 2));
//            laatikot.lisaa(new Laatikko(1, 1, 1));

//        }
//            laatikot.lisaa(new Laatikko(1, 1, 1));
//      laatikot.lisaa(new Laatikko(0.5, 0.5, 3));
//  laatikot.lisaa(new Laatikko(5, 1, 5));
//        laatikot.lisaa(new Laatikko(10, 10, 2));
            
            
               Scanner input = new Scanner(System.in);
      while(true) {
          System.out.println("Lisätäänkö laatikko? K, E");
          if(input.nextLine().equals("K")) {
              Laatikonlisays(laatikot);
          }
          else {
              break;
          } 
      
      }
                
            long aikaAlussa = System.currentTimeMillis();
            PakkaaLaatikot ads = new PakkaaLaatikot(laatikot);
            ads.aloita();
            long aikaLopussa = System.currentTimeMillis();
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
            System.out.println("Tyhjää tilaa: " + ads.kontti.LaskeTyhjaTila());
            System.out.println("Kontin tilavuus: " + ads.kontti.LaatikonTilavuus());
            System.out.println("Tyhjän tilan osuus: " + ads.kontti.LaskeTyhjaTila() / ads.kontti.LaatikonTilavuus());
    }

    public static void Laatikonlisays(JarjestettyLaatikkoLista laatikot) {
        Scanner input = new Scanner(System.in);
        int[] laatikonmitat = new int[3];
        System.out.println("Anna mitat leveys, syvyys, korkeus");
        for (int i = 0; i < 3; i++) {
            int luku = input.nextInt();
            laatikonmitat[i] = luku;
        }

        Laatikko laatikko = new Laatikko(laatikonmitat[0], laatikonmitat[1], laatikonmitat[2]);
        laatikot.lisaa(laatikko);
        input.reset();
    }
}

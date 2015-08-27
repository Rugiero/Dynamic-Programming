/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import dynamicprogramming.boxstacking.tietorakenteet.LaatikkoLista;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static LaatikkoLista laatikot = new LaatikkoLista();

    //laatikot annetaan listassa
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        ///testausta
       LaatikkoLista  l = new LaatikkoLista();
        l.lisaa(new Laatikko(1, 2, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaa(new Laatikko(2, 1, 1));
        l.lisaa(new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(5, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(5, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(3, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(2, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(3, new Laatikko(1, 1, 3));
        l.lisaaIndeksiin(1, new Laatikko(1, 1, 3));
        
        
        for(int i =0; i< 15; i++) {
            System.out.println(l.poimi(i));
        }
        
        
        
        
        
        
        
        
        
        
        
        
        ////testausta
        
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Lisataanko laatikko, 'K'','E'");
            if (input.nextLine().equals("K")) {
                Laatikonlisays();
            } else {
                break;
            }

        }

        PakkaaLaatikot jarjestalaatikot = new PakkaaLaatikot(laatikot);
        long aikaAlussa = System.currentTimeMillis();
        jarjestalaatikot.Aloita();
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }

    /**
     *
     * Lisätään laatikko luokkamuuttujaan 'laatikot'.
     *
     */
    public static void Laatikonlisays() {
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

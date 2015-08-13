/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<int[]> laatikot = new ArrayList<>();

    //laatikot annetaan listassa
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Lisataanko laatikko, 'K'','E'");
            if (input.nextLine().equals("K")) {
                Laatikonlisays();
            } else {
                break;
            }

        }

        JarjestaLaatikot jarjestalaatikot = new JarjestaLaatikot(laatikot);
        jarjestalaatikot.VAIHE1();
      

    }

    /**
     *
     * Lisätään laatikko luokkamuuttujaan 'laatikot'.
     *
     */
    public static void Laatikonlisays() {
        Scanner input = new Scanner(System.in);
        int[] laatikko = new int[3];
        System.out.println("Anna mitat leveys, syvyys, korkeus");
        for (int i = 0; i < 3; i++) {
            int luku = input.nextInt();
            laatikko[i] = luku;
        }
        laatikot.add(laatikko);
        input.reset();
    }

}

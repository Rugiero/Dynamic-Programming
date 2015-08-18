/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Laatikko> laatikot = new ArrayList<>();

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
        jarjestalaatikot.Aloita();
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
        laatikot.add(laatikko);
        input.reset();
    }

}

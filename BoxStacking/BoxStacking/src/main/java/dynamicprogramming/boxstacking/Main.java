/**
 * Main luokka kysyy tällä hetkellä listan laatikoista, ja antaa algoritmin
 * mukaisen pakkausjärjestyksen.
 */
package dynamicprogramming.boxstacking;

import dynamicprogramming.boxstacking.tietorakenteet.LaatikkoLista;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //private static LaatikkoLista laatikot = new LaatikkoLista();
    //laatikot annetaan listassa
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException {
        LaatikkoLista laatikot = new LaatikkoLista();
        ////testausta
        Scanner input = new Scanner(System.in);
        double[] keskiarvot = new double[30];
        double[] xakseli = new double[30];
        for (int i = 1; i <= 10; i++) {
            keskiarvot[i - 1] = lisaaLaatikoitaPalautaKeskiarvo(i);
            xakseli[i - 1] = i;
        }

        ArrayList<double[]> arvot = new ArrayList<>();
        arvot.add(xakseli);
        arvot.add(keskiarvot);

        piirraKuvaaja plot = new piirraKuvaaja("otsikko", "y", "x", arvot);
        plot.Piirretaankuvaaja();
        plot.setVisible(true);

    }

    public static double lisaaLaatikoitaPalautaKeskiarvo(int laatikoita) throws InterruptedException {

        double ka = 0;
        for (int j = 0; j < 10; j++) {
            LaatikkoLista laatikot = new LaatikkoLista();
            for (int i = 1; i <= laatikoita; i++) {
                laatikot.lisaa(new Laatikko(3, 3, 3));
                laatikot.lisaa(new Laatikko(1, 2, 1));
                laatikot.lisaa(new Laatikko(1, 1, 4));

            }
            double aikaAlussa = System.currentTimeMillis();
            PakkaaLaatikot laatikonpakkaus = new PakkaaLaatikot(laatikot);
            laatikonpakkaus.Aloita();
            double aikaLopussa = System.currentTimeMillis();
            ka = (ka + (aikaLopussa - aikaAlussa));
            Thread.sleep(1);
        }
       
        return ka / (double) 10;

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


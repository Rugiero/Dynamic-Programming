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
    public static void main(String[] args) {
        LaatikkoLista laatikot = new LaatikkoLista();
        ////testausta
        Scanner input = new Scanner(System.in);

//        while (true) {
//            System.out.println("Lisataanko laatikko, 'K'','E'");
//            if (input.nextLine().equals("K")) {
//                Laatikonlisays(laatikot);
//            } else {
//                break;
//            }
//        Laatikko laatikko = new Laatikko(4, 3, 4);
//        laatikot.lisaa(laatikko);
////                    laatikot.lisaa(new Laatikko(3, 3, 3));
////                    laatikot.lisaa(new Laatikko(2, 2, 2));
//
//        for (int i = 0; i < 6; i++) {
//            laatikko.PyoritaLaatikkoa();
//
//            System.out.println(laatikko.pituus + " " + laatikko.leveys + " " + laatikko.korkeus);
//
//            if (i == 2) {
//                laatikko.KaannaLaatikko90astetta();
//            }
//        }
//
//        PakkaaLaatikot jarjestalaatikot = new PakkaaLaatikot(laatikot);
//        jarjestalaatikot.Aloita();
        double[] arvot = new double[50];
        double[] laatikoidenmaara = new double[50];
        double[] tyhjatila = new double[50];

        ArrayList<double[]> arvotjalaatikoidenmaara = new ArrayList<>();
        ArrayList<double[]> tyhjatilajalaatikoidenmaara = new ArrayList<>();

        Random random = new Random();
        outerloop:
        for (int i = 1; i < 100; i++) {
            laatikot = new LaatikkoLista();

            long ka = 0;
            for (int j = 0; j <  20; j++) {

                for (int k = 0; k < i; k++) {

                    laatikot.lisaa(new Laatikko(random.nextInt(5) + 1, random.nextInt(5) + 1, random.nextInt(5) + 1));

                }
                PakkaaLaatikot jarjestalaatikot = new PakkaaLaatikot(laatikot);

                long aikaAlussa = System.currentTimeMillis();
                jarjestalaatikot.Aloita();
                long aikaLopussa = System.currentTimeMillis();
                ka = (ka + (aikaLopussa - aikaAlussa));
                laatikot = new LaatikkoLista();
                tyhjatila[i] = jarjestalaatikot.kontti.LaskeTyhjaTila();

            }
            arvot[i] = ka / 50;
            laatikoidenmaara[i] = i;

        }

        arvotjalaatikoidenmaara.add(arvot);
        arvotjalaatikoidenmaara.add(laatikoidenmaara);

        tyhjatilajalaatikoidenmaara.add(tyhjatila);
        tyhjatilajalaatikoidenmaara.add(laatikoidenmaara);

        piirraKuvaaja kuvanpiirtaja = new piirraKuvaaja("holo", "arvot", "laatikoidenmaara", arvotjalaatikoidenmaara);
        kuvanpiirtaja.Piirretaankuvaaja();
        kuvanpiirtaja.setVisible(true);

        piirraKuvaaja kuvanpiirtaja2 = new piirraKuvaaja("holo", "tyhjatila", "laatikoidenmaara", tyhjatilajalaatikoidenmaara);
        kuvanpiirtaja2.Piirretaankuvaaja();
        kuvanpiirtaja2.setVisible(true);
//                    laatikot.lisaa(new Laatikko(4, 4, 4));
//                    laatikot.lisaa(new Laatikko(3, 3, 3));
//                    laatikot.lisaa(new Laatikko(2, 2, 2));
        // 
        PakkaaLaatikot jarjestalaatikot = new PakkaaLaatikot(laatikot);
        jarjestalaatikot.Aloita();

        /**
         *
         * Lisätään laatikko luokkamuuttujaan 'laatikot'.
         *
         */
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


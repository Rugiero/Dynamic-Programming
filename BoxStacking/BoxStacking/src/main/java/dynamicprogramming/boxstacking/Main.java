/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        JarjestaLaatikot jarjestalaatikot = new JarjestaLaatikot();

        while (true) {
            System.out.println("Lisataanko laatikko, 'K'','E'");
            if (input.nextLine().equals("K")) {
                Laatikonlisays();
            } else {
                break;
            }

        }
        int[] pakkausjarjestys = jarjestalaatikot.AnnaPakkausajarjestys(laatikot);
        for(int temp : pakkausjarjestys){
            System.out.println(temp);
        }
        
    }

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

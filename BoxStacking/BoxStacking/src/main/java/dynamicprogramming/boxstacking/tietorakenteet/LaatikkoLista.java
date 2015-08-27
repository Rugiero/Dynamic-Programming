/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming.boxstacking.tietorakenteet;

import dynamicprogramming.boxstacking.Laatikko;

/**
 * Arraylist- vastine.
 *
 * @author ilari
 */
public class LaatikkoLista {

    Laatikko[] lista;
    private final int kapasiteetti;
    private int num;

    public LaatikkoLista() {
        this.kapasiteetti = 100;
        lista = new Laatikko[kapasiteetti];
        num = 0;
    }

    public void lisaa(Laatikko a) {

        lista[num] = a;
        num++;

    }

    public Laatikko poimi(int index) {

        return lista[index];
    }

    public void poista(int index) {

        for (int j = index; j < num - 1; j++) {
            lista[index] = lista[index + 1];
        }
        lista[num - 1] = null;
        num--;
    }

    public int koko() {

        return num;

    }

    public void lisaaIndeksiin(int index, Laatikko a) {

        for (int j = index; j < num - 1; j++) {
            lista[j + 1] = lista[j];
        }
        lista[index] = a;
        num++;

    }

}

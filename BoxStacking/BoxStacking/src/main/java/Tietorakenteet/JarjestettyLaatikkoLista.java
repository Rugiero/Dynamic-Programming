/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Sailiot.Laatikko;

/**
 * Arraylist- vastine.
 *
 * @author ilari
 */
public class JarjestettyLaatikkoLista {

    Laatikko[] lista;
    private final int kapasiteetti;
    private int num;

    /**
     * Konstruktori.
     */
    public JarjestettyLaatikkoLista() {
        this.kapasiteetti = 1000;
        lista = new Laatikko[kapasiteetti];
        num = 0;
    }

    /**
     * Lisätään olio listan hännille.
     *
     * @param a Lisättävä olio.
     */
    public void lisaa(Laatikko a) {

        lista[num] = a;
        num++;

    }

    /**
     * Palauttaa olion halutusta kohdasta.
     *
     * @param index
     * @return
     */
    public Laatikko poimi(int index) {

        return lista[index];
    }

    /**
     * Poistaa olion halutusta kohtaa.
     *
     * @param index
     */
    public void poista(int index) {

        for (int j = index; j < num - 1; j++) {
            lista[j] = lista[j + 1];
        }
        lista[num - 1] = null;
        num--;
    }

    /**
     * Poistaa laatikon listasta jos se löytyy jotenkin päin sieltä.
     *
     * @param o Poistettava laatikko.
     */
    public void poistaLaatikko(Laatikko o) {
        int poistoindeksi = 0;
        for (Laatikko laatikko : lista) {
            if (o.OnkoSama(laatikko)) {
                break;
            }
            poistoindeksi++;
        }
        poista(poistoindeksi);
    }

    /**
     * Palauttaa listan koon.
     *
     * @return
     */
    public int koko() {

        return num;

    }

    /**
     * Lisää olion haluttuun kohtaan.
     *
     * @param index
     * @param a
     */
    public void lisaaIndeksiin(int index, Laatikko a) {

        for (int copyFrom = num - 1; copyFrom >= index; copyFrom--) {
            lista[copyFrom + 1] = lista[copyFrom];
        }

        // add the new element
        lista[index] = a;
        num++;
    }

    /**
     * Jarjestaa listan halutun ominaisuuden mukaan. Jos vertaa- metodia ei
     * ylitä, niin metodi ei tee mitään. Algoritmi on niin kutsuttu Bubble sort.
     *
     */
    public void Jarjesta() {
        int c, d;
        Laatikko swap;
        for (c = 0; c < (num - 1); c++) {
            for (d = 0; d < num - c - 1; d++) {
                if (vertaa(lista[d], lista[d + 1]) > 0) /* For descending order use < */ {
                    swap = lista[d];
                    lista[d] = lista[d + 1];
                    lista[d + 1] = swap;
                }
            }
        }

    }

    /**
     * Metodi kahden laatikon vertailua varten halutun ominaisuuden perusteella.
     * Oletuksena palauttaa 0.
     *
     * @param o Ensimmäinen olio.
     * @param o1 Toinen olio.
     * @return
     */
    public int vertaa(Laatikko o, Laatikko o1) {

        return 0;
    }

}

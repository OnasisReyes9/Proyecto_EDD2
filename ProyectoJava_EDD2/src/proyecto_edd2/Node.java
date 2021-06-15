/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

import java.util.ArrayList;

/**
 *
 * @author Monica
 */
public class Node {

    ArrayList<KeyPosition> llaves;
    ArrayList<Integer> hijos;
    int num_llaves;
    boolean hoja;

    public Node(int m) {
        llaves = new ArrayList<>();
        hijos = new ArrayList<>();
        for (int i = 0; i < m - 1; i++) {
            llaves.add(null);
            hijos.add(-1);
        } // Fin For
        hijos.add(-1);
        num_llaves = 0;
        hoja = true;
    } // Fin Constructor Node

    public Node(int m, String llave, long pos) {
        llaves = new ArrayList<>(m - 1);
        hijos = new ArrayList<>(m);
        llaves.set(0, new KeyPosition(llave, pos));
        num_llaves = 1;
        hoja = true;
    } // Fin Constructor Nodo

    public ArrayList<KeyPosition> getLlaves() {
        return llaves;
    } // Fin Get Llaves

    public void setLlaves(ArrayList<KeyPosition> llaves) {
        this.llaves = llaves;
    } // Fin Set Llaves

    public ArrayList<Integer> getHijos() {
        return hijos;
    } // Fin Get Hijos

    public void setHijos(ArrayList<Integer> hijos) {
        this.hijos = hijos;
    } // Fin Set Hijos

    public int getNum_llaves() {
        return num_llaves;
    } // Fin Get Num Llaves

    public void setNum_llaves(int num_llaves) {
        this.num_llaves = num_llaves;
    } // Fin Set Num Llaves

    public boolean isHoja() {
        return hoja;
    } // Fin Is Hoja

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    } // Fin Set Hoja

    @Override
    public String toString() {
        if (this.getNum_llaves() == 0) {
            return "";
        } // Fin If
        String nodo = "[";
        for (int i = 0; i < this.getNum_llaves() - 1; i++) {
            nodo += this.getLlaves().get(i) + ", ";
        } // Fin For
        nodo += this.getLlaves().get(this.getNum_llaves() - 1) + "]";
        return nodo;
    } // Fin toString

} // Fin Class Node

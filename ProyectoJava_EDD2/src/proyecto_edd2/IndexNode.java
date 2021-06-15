/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

/**
 *
 * @author Monica
 */
public class IndexNode {

    private Node nodo;
    private int indice;

    public IndexNode(Node nodo, int indice) {
        this.nodo = nodo;
        this.indice = indice;
    } // Fin Constructor Index Node

    public Node getNodo() {
        return nodo;
    } // Fin Get Nodo

    public void setNodo(Node nodo) {
        this.nodo = nodo;
    } // Fin Set Nodo

    public int getIndice() {
        return indice;
    } // Fin Get Indice

    public void setIndice(int indice) {
        this.indice = indice;
    } // Fin Set Indice
} // Fin Class Index Node

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Monica
 */
public class BTree implements Serializable {

    int orden;
    int raiz;
    ArrayList<Node> nodos;
    private static final long serialVersionUID = 777L;

    public BTree() {

    } // Fin Constructor BTree

    public BTree(int orden) {
        nodos = new ArrayList<Node>();
        this.orden = orden;
        nodos.add(new Node(orden));
        raiz = 0;
    } // Fin Constructor BTree

    public int getOrden() {
        return orden;
    } // Fin Get Orden

    public void setOrden(int order) {
        this.orden = order;
    } // Fin Set Orden

    public int getRaiz() {
        return raiz;
    } // Fin Get Raiz

    public void setRaiz(int raiz) {
        this.raiz = raiz;
    } // Fin Set Raiz

    public int llavesSuperiores() {
        return orden - 1;
    } // Fin Upper B Keys

    public int llavesInferiores() {
        return Math.max((orden / 2) - 1, 1);
    } // Fin Lower B Keys

    public IndexNode B_Tree_Search(int index_node, String llave_primaria) {
        int num = 0;
        Node temp = nodos.get((int) index_node);
        while (num < temp.getNum_llaves() && llave_primaria.compareTo(temp.getLlaves().get(num).getLlave()) > 0) {
            num++;
        } // Fin While
        if (num < temp.getNum_llaves() && llave_primaria.compareTo(temp.getLlaves().get(num).getLlave()) == 0) {
            return new IndexNode(temp, num);
        } // Fin If
        if (temp.isHoja()) {
            return null;
        } else {
            return B_Tree_Search(temp.getHijos().get(num), llave_primaria);
        } // Fin If
    } // Fin B Tree Search

    public void B_Tree_Insert(String llave_primaria, long RRN) {
        int index_raiz = raiz;
        Node nodoA = nodos.get(this.getRaiz());
        if (nodoA.getNum_llaves() == llavesSuperiores()) {
            int tam_nodo = nodos.size();
            Node nodoB = new Node(orden);
            nodos.add(nodoB);
            raiz = tam_nodo;
            nodoB.setHoja(false);
            nodoB.setNum_llaves(0);
            nodoB.getHijos().set(0, index_raiz);
            B_Tree_Split_Child(tam_nodo, 0, index_raiz);
            B_Tree_Insert_NonFull(tam_nodo, llave_primaria, RRN);
        } else {
            B_Tree_Insert_NonFull(index_raiz, llave_primaria, RRN);
        } // Fin If
    } // Fin B Tree Insert

    public void B_Tree_Split_Child(int ix, int i, int iy) {
        int iz = nodos.size();
        Node z = new Node(orden);
        nodos.add(z);
        Node y = nodos.get(iy);
        Node x = nodos.get(ix);
        z.setHoja(y.isHoja());
        z.setNum_llaves(this.llavesSuperiores() - this.llavesInferiores() - 1);
        for (int j = 0; j < z.getNum_llaves(); j++) {
            z.getLlaves().set(j, y.getLlaves().get(j + this.llavesInferiores() + 1));
        } // Fin For
        if (!y.isHoja()) {
            for (int j = 0; j < z.getNum_llaves() + 1; j++) {
                z.getHijos().set(j, y.getHijos().get(j + this.llavesInferiores() + 1));
            } // Fin For
        } // Fin If
        y.setNum_llaves(this.llavesInferiores());
        x.getHijos().add(i + 1, iz);
        x.getHijos().remove(orden);
        x.getLlaves().add(i, y.getLlaves().get(this.llavesInferiores()));
        x.getLlaves().remove(this.llavesSuperiores());
        x.setNum_llaves(x.getNum_llaves() + 1);
    } // Fin B Tree Split Child

    public void B_Tree_Insert_NonFull(int ix, String llave_primaria, long p) {
        Node x = nodos.get(ix);
        int i = x.getNum_llaves() - 1;
        if (x.isHoja()) {
            while (i >= 0 && llave_primaria.compareTo(x.getLlaves().get(i).getLlave()) < 0) {
                i--;
            } // Fin While
            x.getLlaves().add(i + 1, new KeyPosition(llave_primaria, p));
            x.getLlaves().remove(this.llavesSuperiores());
            x.setNum_llaves(x.getNum_llaves() + 1);
        } else {
            while (i >= 0 && llave_primaria.compareTo(x.getLlaves().get(i).getLlave()) < 0) {
                i--;
            } // Fin While
            i++;
            if (nodos.get(x.getHijos().get(i)).getNum_llaves() == this.llavesSuperiores()) {
                B_Tree_Split_Child(ix, i, x.getHijos().get(i));
                if (llave_primaria.compareTo(x.getLlaves().get(i).getLlave()) > 0) {
                    i++;
                } // Fin If
            } // Fin If
            B_Tree_Insert_NonFull(x.getHijos().get(i), llave_primaria, p);
        } // Fin If
    } // Fin B Tree Insert NonFull

    public void B_Tree_Print(int pos, int num) {
        Node actual_node = nodos.get(pos);
        String linea = new String(new char[1024]).replace('\0', ' ');
        for (int i = 0; i < actual_node.getNum_llaves(); i++) {
            if (actual_node.isHoja() == false && actual_node.getHijos().get(i) != null) {
                B_Tree_Print(actual_node.getHijos().get(i), num + 1);
            } // Fin If
            if (actual_node.getLlaves().get(i) != null) {
                System.out.println(linea.substring(0, num * 4) + actual_node.getLlaves().get(i) + ", Nivel en el arbol:" + num);
            } // Fin If
        } // Fin For
        if (actual_node.isHoja() == false) {
            B_Tree_Print(actual_node.getHijos().get(actual_node.getNum_llaves()), num + 1);
        } // Fin If
    } // Fin B Tree Print

    public void x(){
        
    }
    //search by affinity agrega el rrn deseado de la llave encontradas
     public void searchByAffinity(int ix, String k, ArrayList<Long> rrns) {
        int i = 0;
        Node x = nodos.get((int) ix);
        while (i < x.getNum_llaves()&& k.compareTo(x.getLlaves().get(i).getLlave()) > 0) {
            i++;
        }
        boolean flag = false;
        while (i < x.getNum_llaves()&& k.compareTo(x.getLlaves().get(i).getLlave()) == 0) {
            flag = true;
            rrns.add(x.getLlaves().get(i).getPos());
            System.out.println(x.getLlaves().get(i).toString());
            if (!x.isHoja()) {
                searchByAffinity(x.getHijos().get(i), k, rrns);
            }
            i++;
        }
        if (!x.isHoja()) {
            searchByAffinity(x.getHijos().get(i), k, rrns);
        }
    }//fin de search by affinity
} // Fin Class B Tree

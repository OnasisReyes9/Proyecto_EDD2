/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monica
 */
public class Archivo implements Serializable {

    private File archivo;
    private int ID;
    private ArrayList<Campo> campos = new ArrayList();
    private LinkedList availist = new LinkedList();
    private static final long SerialVersionUID = 777L;

    public Archivo() {
    } // Fin Constructor Archivo

    public Archivo(File archivo, int ID) {
        this.archivo = archivo;
        this.ID = ID;
    } // Fin Constructor Archivo

    public int getID() {
        return ID;
    } // Fin Get ID;

    public void setID(int ID) {
        this.ID = ID;
    } // Fin Set ID;

    public File getArchivo() {
        return archivo;
    } // Fin Get Archivo

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    } // Fin Set Archivo

    public ArrayList<Campo> getCampos() {
        return campos;
    } // Fin Get Campos

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    } // Fin Set Campos

    public void addCampo(Campo ca) {
        this.campos.add(ca);
    } // Fin Set Campo

    public void RemoveCampo(Campo ca) {
        this.campos.remove(ca);
    } // Fin Remove Campo

    public int sizeCampos() {
        return campos.size();
    }

    public int ubicacion_llavePrimaria(int i, int llave) {
        if (i == campos.size()) {
            return llave;
        } else {
            if (campos.get(i).isLlavePrimaria()) {
                llave = i;
                return llave;
            } else {
                return ubicacion_llavePrimaria(i + 1, -1);
            }
        }//fin else
    }//fin metodo

    public Campo getCampo(int i) {//si da error validar que i se < campos.size
        return campos.get(i);
    }

    public int recordSize() {
        int length = 1;
        for (int i = 0; i < this.campos.size(); i++) {
            length += campos.get(i).getLongitud();
        }
        return length + campos.size();
    }

    public int sizeMetadata(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        String metadata = "";
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            int cont = 0;
            while (br.readLine() != null && cont == 0) {
                metadata = br.readLine();
                cont++;
            }//fin while
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return metadata.length() + 1;
    }

    public LinkedList getAvailist() {
        return availist;
    }

    public void setAvailist(LinkedList availist) {
        this.availist = availist;
    }

} // Fin Clase Archivo

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_edd2;

import java.io.Serializable;

/**
 *
 * @author Monica
 */
public class KeyPosition implements Serializable{
    private String llave;
    private long pos;
    //private byte byte_pos;
    
    private static final long SerialVersionUID = 77777L;

    public KeyPosition(String llave, long pos) {
        this.llave = llave;
        this.pos = pos;
        //this.byte_pos = byte_pos;
    } // Fin Constructor Key Position

    public String getLlave() {
        return llave;
    } // Fin Get Llave

    public void setLlave(String llave) {
        this.llave = llave;
    } // Fin Set Llave

    public long getPos() {
        return pos;
    } // Fin Get Pos

    public void setPos(long pos) {
        this.pos = pos;
    } // Fin Set Pos

    @Override
    public String toString() {
        return "[Llave: " + llave + "; RRN: " + pos + ']';
    } // Fin toString
    
} // Fin Key Position

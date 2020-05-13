/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import LinkedList.LinkedList;
import LinkedList.LinkedList.LNodo;
import Objects.Data.Data;
import java.io.Serializable;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Block implements Serializable {
    private long INDEX;
    private String TIMESTAMP;
    private long NONCE;
    private LinkedList DATA;
    private String PREVIOUSHASH;
    private String HASH;

    public Block(long INDEX, String TIMESTAMP, long NONCE, String PREVIOUSHASH, String HASH) {
        this.INDEX = INDEX;
        this.TIMESTAMP = TIMESTAMP;
        this.NONCE = NONCE;
        this.DATA = new LinkedList();
        this.PREVIOUSHASH = PREVIOUSHASH;
        this.HASH = HASH;
    }
    
    public Block()
    {
        this.DATA = new LinkedList();
    }

    public long getINDEX() {
        return INDEX;
    }

    public String getTIMESTAMP() {
        return TIMESTAMP;
    }

    public long getNONCE() {
        return NONCE;
    }

    public LinkedList getDATA() {
        return DATA;
    }

    public String getPREVIOUSHASH() {
        return PREVIOUSHASH;
    }

    public String getHASH() {
        return HASH;
    }

    public void setINDEX(long INDEX) {
        this.INDEX = INDEX;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    public void setNONCE(long NONCE) {
        this.NONCE = NONCE;
    }

    public void setDATA(LinkedList DATA) {
        this.DATA = DATA;
    }

    public void setPREVIOUSHASH(String PREVIOUSHASH) {
        this.PREVIOUSHASH = PREVIOUSHASH;
    }

    public void setHASH(String HASH) {
        this.HASH = HASH;
    }
    
    public String getData()
    {
        LNodo tmp = this.DATA.leerPrimero();
        String res = "";
        while(tmp != null)
        {
            Data d = (Data) tmp.dato;
            res += d.toString() + ",";
            tmp = tmp.siguiente();
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }
    
    public void addData(Data d)
    {
        this.DATA.insertarCabezaLista(d);
    }
    
    @Override
    public String toString()
    {
        String res = "";
        res = "{"
            + "\"INDEX\": " + this.getINDEX() + ","
            + "\"TIMESTAMP\":\"" +this.getTIMESTAMP() + "\","
            + "\"NONCE\":" + this.getNONCE() + ","
            + "\"DATA\":[" + this.getData() + "],"
            + "\"PREVIOUSHASH\":\"" + this.getPREVIOUSHASH() + "\","
            + "\"HASH\":\"" + this.getHASH() + "\""
            + "}";
        return res;
    }
}

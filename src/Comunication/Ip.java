/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunication;

import java.io.Serializable;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Ip implements Serializable{

    private String ip;
    private long port;

    public Ip(String ip, long port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public long getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(long port) {
        this.port = port;
    }
}

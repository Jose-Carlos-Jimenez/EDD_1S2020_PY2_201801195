/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunication;

import DoublyLinkedList.DoublyLinkedList;
import LinkedList.LinkedList;
import LinkedList.LinkedList.LNodo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Server extends Observable implements Runnable {

    ServerSocket servidor = null;
    Socket sc = null;
    DataInputStream in;

    private int puerto;

    public Server(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("[SERVIDOR INICIADO]");
            while (true) {
                sc = servidor.accept();
                System.out.println("[CLIENTE CONECTADO]");
                in = new DataInputStream(sc.getInputStream());
                String instruccion = in.readUTF();
                System.out.println(instruccion);
                if (instruccion.equals("conectarse")) {
                    in = new DataInputStream(sc.getInputStream());
                    String hisIp = in.readUTF();
                    String hisPort = in.readUTF();
                    edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web.insertarCabezaLista(new Ip(hisIp, Integer.parseInt(hisPort)));
                    LNodo aux = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web.leerPrimero();
                    //out.writeObject(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web);
                    while (aux != null) {
                        Ip actual = (Ip) aux.dato;
                        String myPort = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getPort() + "";
                        String myIp = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getIp();
                        String actPort = actual.getPort() + "";
                        String actIp = actual.getIp();
                        boolean ipIgual = myIp.equals(actIp);
                        boolean portIgual = myPort.equals(actPort);
                        if (!ipIgual || !portIgual) {
                            Ip ipAux = (Ip) aux.dato;
                            Socket sender = new Socket(ipAux.getIp(), (int) ipAux.getPort());
                            DataOutputStream salida = new DataOutputStream(sender.getOutputStream());
                            salida.writeUTF("ip");
                            ObjectOutputStream salidaObjeto = new ObjectOutputStream(sender.getOutputStream());
                            salidaObjeto.writeObject(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web);
                            sender.close();
                            if (hisIp.equals(actIp) && hisPort.equals(actPort)) {
                                Socket enviar = new Socket(ipAux.getIp(), (int) ipAux.getPort());
                                DataOutputStream output = new DataOutputStream(enviar.getOutputStream());
                                output.writeUTF("datos");
                                ObjectOutputStream outputObjeto = new ObjectOutputStream(enviar.getOutputStream());
                                outputObjeto.writeObject(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.blockchain);
                                enviar.close();
                            }
                        }
                        aux = aux.siguiente();
                    }
                } else if (instruccion.equals("datos")) {
                    ObjectInputStream entrada = new ObjectInputStream(sc.getInputStream());
                    try {
                        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.blockchain = (DoublyLinkedList) entrada.readObject();
                        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.getJsonData();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (instruccion.equals("actualizar")) {

                } else if (instruccion.equals("ip")) {
                    try {
                        ObjectInputStream entrada = new ObjectInputStream(sc.getInputStream());
                        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web = (LinkedList) entrada.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.setChanged();
                this.notifyObservers();
                this.clearChanged();
                System.out.println("<<<<<<<<<<< [iteración terminada ] >>>>>>>>>>>>");
                sc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

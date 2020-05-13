/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunication;

import LinkedList.LinkedList.LNodo;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Client implements Runnable {

    String host;
    int puerto;
    String instruccion;

    public Client(String host, int puerto, String instruccion) {
        this.host = host;
        this.puerto = puerto;
        this.instruccion = instruccion;
    }

    public Client(String instruccion) {
        this.instruccion = instruccion;
    }

    @Override
    public void run() {
        DataOutputStream out;
        try {

            if (instruccion.equals("conectarse")) {
                Socket sc = new Socket(host, puerto);
                //ObjectInputStream in = new ObjectInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF("conectarse");
                out.writeUTF(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getIp());
                out.writeUTF(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getPort() + "");
                //edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web = (LinkedList) in.readObject();
                sc.close();
            } else if (instruccion.equals("distribuir")) {
                LNodo i = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web.leerPrimero();
                while (i != null) {
                    Ip actual = (Ip) i.dato;
                    String myPort = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getPort() + "";
                    String myIp = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getIp();
                    String actPort = actual.getPort() + "";
                    String actIp = actual.getIp();
                    boolean ipIgual = myIp.equals(actIp);
                    boolean portIgual = myPort.equals(actPort);
                    if (!ipIgual || !portIgual) {
                        Socket enviar = new Socket(actual.getIp(), (int) actual.getPort());
                        DataOutputStream output = new DataOutputStream(enviar.getOutputStream());
                        output.writeUTF("datos");
                        ObjectOutputStream outputObjeto = new ObjectOutputStream(enviar.getOutputStream());
                        outputObjeto.writeObject(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.blockchain);
                        outputObjeto.writeObject(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.users);
                    }
                    i = i.siguiente();
                }
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}

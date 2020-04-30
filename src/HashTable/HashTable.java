/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

import HashTable.LinkedList.LNodo;
import Objects.Student;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class HashTable {

    LinkedList[] tabla;

    public HashTable() {
        this.tabla = new LinkedList[45];
        for (int i = 0; i < 45; i++) {
            tabla[i] = new LinkedList();
        }
    }

    public void insertar(Student s) {
        int key = Integer.parseInt(s.getCarnet()) % 45;
        this.tabla[key].insertarCabezaLista(s);
    }

    public void eliminar(Student s) {
        int key = Integer.parseInt(s.getCarnet()) % 45;
        this.tabla[key].eliminar(s);
    }

    public Student find(Student s) {
        int key = Integer.parseInt(s.getCarnet()) % 45;
        return (Student) this.tabla[key].buscarLista(s).dato;
    }

    public boolean contains(Student s) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (tabla[i].contains(s)) {
                System.out.println("[SE HA ENCONTRADO EL USUARIO]");
                return true;
            }
        }
        System.out.println("[EL USUARIO NO EXISTE]");
        return false;
    }

    public String graph() {
        return getDOT();
    }

    private String getDOT() {
        String dot = "digraph{\nrankdir= TB\nnode [shape = record,height=.1];\n";
        for (int i = 0; i < this.tabla.length; i++) {
            dot += "node" + i + "[label =\"" + i + "\"; group = 0];\n";
            LNodo n = tabla[i].leerPrimero();
            while (n != null) {
                dot += ((Student) n.dato).getCarnet() + "[label=\"" + n.dato.toString() + "\"];\n";
                n = n.enlace;
            }
        }

        for (int i = 0; i < this.tabla.length; i++) {
            if (i + 1 < this.tabla.length) {
                dot += "\"node" + i + "\" -> " + "\"node" + (i + 1) + "\";\n";
            }
            dot += "{rank=same;node" + i;
            LNodo n = tabla[i].leerPrimero();
            while (n != null) {
                dot += ";" + ((Student) n.dato).getCarnet();
                n = n.enlace;
            }
            dot += "};\n";

        }
        dot += "}\n";
        return dot;
    }
}

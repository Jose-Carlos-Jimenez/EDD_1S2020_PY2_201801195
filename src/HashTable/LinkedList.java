/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class LinkedList {

    public class LNodo {

        Object dato;
        LNodo enlace;

        public LNodo(Object x) {
            dato = x;
            enlace = null;
        }

        public LNodo(Object x, LNodo n) {
            dato = x;
            enlace = n;

        }

        public Object leerDato() {
            return dato;
        }

        public LNodo siguiente() {
            return enlace;
        }
    }

    public class LIterator {

        private LNodo prm, actual;

        public LIterator(LinkedList list) {
            prm = actual = list.leerPrimero();
        }

        public Object siguiente() {
            Object elemento = null;
            if (actual != null) {
                elemento = actual.leerDato();
                actual = actual.siguiente();
            }
            return elemento;
        }

        public void inicIter() {
            actual = prm;
        }
    }

    private LNodo primero;

    public LinkedList() {
        primero = null;
    }

    public LNodo leerPrimero() {
        return primero;
    }

    public LinkedList insertarCabezaLista(Object entrada) {
        LNodo nuevo;
        nuevo = new LNodo(entrada);
        nuevo.enlace = primero;
        primero = nuevo;
        return this;
    }
    // inserta un elemento a partir de anterior

    public LinkedList insertarLista(LNodo anterior, Object entrada) {
        LNodo nuevo;
        nuevo = new LNodo(entrada);
        nuevo.enlace = anterior.enlace;
        anterior.enlace = nuevo;
        return this;
    }
    // elimina el elemento entrada

    public void eliminar(Object entrada) {
        LNodo actual, anterior;
        boolean encontrado;
        actual = primero;
        anterior = null;
        encontrado = false;
        // Bucle de búsqueda
        while ((actual != null) && !actual.dato.equals(entrada)) {
            if (!actual.dato.equals(entrada)) {
                anterior = actual;
                actual = actual.enlace;
            }
        }
        if (actual != null) {
            // Se distingue entre que el nodo sea el cabecera
            // o del resto de la lista
            if (actual == primero) {
                primero = actual.enlace;
            } else {
                anterior.enlace = actual.enlace;
            }
            actual = null;
        }
    }
    // busca el elemento destino

    public LNodo buscarLista(Object destino) {
        LNodo indice;
        for (indice = primero; indice != null; indice = indice.enlace) {
            if (indice.dato.equals(destino)) {
                return indice;
            }
        }
        return null;
    }
    // recorre la lista y muestra cada dato

    public void visualizar() {
        LNodo n;
        n = primero;
        while (n != null) {
            System.out.print(n.dato + " ");
            n = n.enlace;
        }
    }
}

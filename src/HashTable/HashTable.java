/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashTable;

import Objects.Student;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class HashTable {
    LinkedList[] tabla;

    public HashTable() {
        this.tabla = new LinkedList[45];
        for(int i = 0;i < 45; i++)
        {
            tabla[i] = new LinkedList();
        }
    }
   
    public void insertar(Student s)
    {
        int key = Integer.parseInt(s.getCarnet()) % 45;
        this.tabla[key].insertarCabezaLista(s);
    }
    
    public void eliminar(Student s)
    {
        int key = Integer.parseInt(s.getCarnet()) % s.getCarnet().length();
        this.tabla[key].eliminar(s);
    }
    
    public Student find(Student s)
    {
        int key = Integer.parseInt(s.getCarnet()) % s.getCarnet().length();
        return (Student)this.tabla[key].buscarLista(s).dato;
    }
}

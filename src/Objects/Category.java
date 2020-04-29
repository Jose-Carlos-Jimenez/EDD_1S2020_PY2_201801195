/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import BTree.BTree;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Category implements Comparable<Category> {

    String name;
    BTree books;

    public Category(String name) {
        this.name = name;
        this.books = new BTree();
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name + "\\nTamaño: " + this.books.size();
    }
    
    @Override
    public boolean equals(Object o)
    {
        Category n = (Category)o;
        return n.name == name;
    }
}

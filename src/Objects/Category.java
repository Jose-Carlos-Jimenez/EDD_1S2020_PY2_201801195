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

    private String name;
    private String creator;
    public BTree books;

    public Category(String name, String creator) {
        this.name = name;
        this.creator = creator;
        this.books = new BTree();
    }

    @Override
    public int compareTo(Category o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return this.getName() + "\\nTamaño: " + this.getBooks().size();
    }
    
    @Override
    public boolean equals(Object o)
    {
        Category n = (Category)o;
        return (n.getName() == null ? getName() == null : n.getName().equals(getName()));
    }
    
    public String getName() {
        return name;
    }

    public BTree getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(BTree books) {
        this.books = books;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

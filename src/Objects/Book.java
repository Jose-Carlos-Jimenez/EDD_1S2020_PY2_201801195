/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Book implements Comparable<Book>{

    private long isbn;
    private String title;
    private String author;
    private String editorial;
    private long year;
    private long edition;
    private String category;
    private String language;
    private String adedBy;

    public Book(long isbn, String title, String author, String editorial, long year, long edition, String category, String language, String adedBy) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.year = year;
        this.edition = edition;
        this.category = category;
        this.language = language;
        this.adedBy = adedBy;
    }
        
    public Book(String isbn)
    {
        this.isbn = Long.parseLong(isbn);
    }
    
    public long getIsbn() {
        return isbn;
    }
    
    public String getIsbnS()
    {
        return this.isbn + "";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditorial() {
        return editorial;
    }

    public long getYear() {
        return year;
    }

    public long getEdition() {
        return edition;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getAdedBy() {
        return adedBy;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setEdition(long edition) {
        this.edition = edition;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setAdedBy(String adedBy) {
        this.adedBy = adedBy;
    }

    @Override
    public int compareTo(Book o) {
        int resultado=0;
        if (this.isbn<o.isbn) {resultado = -1;}
        else if (this.isbn>o.isbn){resultado = 1;}
        return resultado;
    }
    
    @Override
    public String toString()
    {
        return this.title + "\\nISBN:" + this.isbn;
    }
    
    @Override
    public boolean equals(Object o)
    {
        Book n = (Book)o;
        return n.isbn == isbn;
    }
}

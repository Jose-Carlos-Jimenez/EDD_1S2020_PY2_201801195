/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects.Data;

import java.io.Serializable;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Create_book extends Data implements Serializable {

    private long isbn;
    private long year;
    private String idioma;
    private String titulo;
    private String editorial;
    private String autor;
    private long edicion;
    private String categoria;
    private String addedBy;

    public Create_book(long isbn, long year, String idioma, String titulo, String editorial, String autor, long edicion, String categoria, String addedBy) {
        super("CREAR_LIBRO");
        this.isbn = isbn;
        this.year = year;
        this.idioma = idioma;
        this.titulo = titulo;
        this.editorial = editorial;
        this.autor = autor;
        this.edicion = edicion;
        this.categoria = categoria;
        this.addedBy = addedBy;
    }

    public long getIsbn() {
        return isbn;
    }

    public long getYear() {
        return year;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public long getEdicion() {
        return edicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEdicion(long edicion) {
        this.edicion = edicion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    @Override
    public String toString()
    {
        String res;
        res = "{\"" + this.getName() + "\":[{"
                +"\"ISBN\":" +this.getIsbn() + ","
                + "\"Año\":" + this.getYear()+ ","
                + "\"Idioma\":\"" + this.getIdioma() + "\","
                + "\"Titulo\":\"" + this.getTitulo() + "\","
                + "\"Editorial\":\"" + this.getEditorial() + "\","
                + "\"Autor\":\"" + this.getAutor() + "\","
                + "\"Edicion\":" + this.getEdicion() + ","
                + "\"Categoria\": \"" + this.getCategoria() + "\","
                + "\"Propietario\": \"" + this.addedBy + "\""
                + "}]}";
        return res;
    }
    
}

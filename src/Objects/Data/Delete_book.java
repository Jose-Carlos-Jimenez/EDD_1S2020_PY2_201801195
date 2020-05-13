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
public class Delete_book extends Data implements Serializable {
    
    private long isbn;
    private String titulo;
    private String categoria;

    public Delete_book(long isbn, String titulo, String categoria) {
        super("ELIMINAR_LIBRO");
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString()
    {
        String res = null;
        res = "{\"" + this.getName() + "\":[{"
                + "\"ISBN\":" + this.getIsbn() + ","
                + "\"Titulo\":\"" + this.getTitulo() + "\","
                + "\"Categoria\":\"" +this.getCategoria()
                + "}]}";
        return res;
    }
}

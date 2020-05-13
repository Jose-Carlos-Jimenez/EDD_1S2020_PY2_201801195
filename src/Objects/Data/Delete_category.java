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
public class Delete_category extends Data implements Serializable{
    
    private String nombre;

    public Delete_category(String nombre) {
        super("ELIMINAR_CATEGORIA");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString()
    {
        String res = null;
        res = "{\"" + this.getName() + "\":[{"
                + "\"NOMBRE\":\"" + this.getNombre() + "\""
                + "}]}";
        return res;
    }
}

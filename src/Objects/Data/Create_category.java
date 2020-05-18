/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects.Data;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Create_category  extends Data{

    private String nombre;
    private String creator;

    public Create_category(String nombre, String creator) {
        super("CREAR_CATEGORIA");
        this.nombre = nombre;
        this.creator = creator;
    }
    
        public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
                + "\"NOMBRE\":\"" + this.getNombre() + "\","
                + "\"CREADOR\":\"" + this.getCreator() + "\""
                + "}]}";
        return res;
    }
}

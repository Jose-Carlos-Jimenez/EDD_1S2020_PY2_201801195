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
public class Edit_user extends Data implements Serializable{
    private long carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;

    public Edit_user(long carnet, String nombre, String apellido, String carrera, String password) {
        super("EDITAR_USUARIO");
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
    }

    public long getCarnet() {
        return carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setCarnet(long carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString()
    {
        String res = null;
        res = "{\"" + this.getName() + "\":[{"
                +"\"Carnet\":\"" + this.getCarnet() + "\"," 
                +"\"Nombre\":\"" + this.getNombre() + "\","
                +"\"Apellido\":\"" + this.getApellido() + "\","
                +"\"Carrera\":\"" + this.getCarrera() + "\","
                +"\"Password\":\"" + this.getPassword() + "\""
                + "}]}";
        return res;
    }
}

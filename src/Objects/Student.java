/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author Jose Carlos Jimenez
 */
public class Student {
    
    private String id;
    private String firstName;
    private String lastName;
    private String career;
    private String password;

    public Student(String carnet, String nombre, String apellido, String carrera, String password) throws NoSuchAlgorithmException {
        this.id = carnet;
        this.firstName = nombre;
        this.lastName = apellido;
        this.career = carrera;
        this.password = this.hashPassword(password);
    }
    
    public String getCarnet() {
        return id;
    }

    public String getNombre() {
        return firstName;
    }

    public String getApellido() {
        return lastName;
    }

    public String getCarrera() {
        return career;
    }

    public String getPassword() {
        return password;
    }

    public void setCarnet(String carnet) {
        this.id = carnet;
    }

    public void setNombre(String nombre) {
        this.firstName = nombre;
    }

    public void setApellido(String apellido) {
        this.lastName = apellido;
    }

    public void setCarrera(String carrera) {
        this.career = carrera;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String hashPassword(String pass) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b1: b)
        {
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object s)
    {
        Student stu = (Student)s;
        return (stu.id == null ? this.id == null : stu.id.equals(this.id));
    }
}

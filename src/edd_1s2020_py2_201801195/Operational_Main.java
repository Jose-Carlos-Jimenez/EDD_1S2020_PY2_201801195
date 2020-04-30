/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_1s2020_py2_201801195;

import AvlTree.AvlTree;
import HashTable.HashTable;
import Objects.Book;
import Objects.Category;
import Objects.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Operational_Main {

    public HashTable users;
    public AvlTree categories;
    public Student user;

    public Operational_Main() {
        users = new HashTable();
        categories = new AvlTree();
    }

    public void readUsers() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JDialog());
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                readUsers(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(Operational_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readUsers(String path) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) obj;

            // loop array
            JSONArray tags = (JSONArray) jsonObject.get("Usuarios");
            var iterator = tags.iterator();
            while (iterator.hasNext()) {
                JSONObject usuario = (JSONObject) iterator.next();
                long carnet = (long) usuario.get("Carnet");
                String nombre = (String) usuario.get("Nombre");
                String apellido = (String) usuario.get("Apellido");
                String carrera = (String) usuario.get("Carrera");
                String pass = (String) usuario.get("Password");
                try {
                    Student estudiante = new Student(carnet + "", nombre, apellido, carrera, pass);
                    this.users.insertar(estudiante);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Operational_Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(Operational_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readBooks() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JDialog());
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                readBooks(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (org.json.simple.parser.ParseException ex) {
                Logger.getLogger(Operational_Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readBooks(String path) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) obj;

            // loop array
            JSONArray tags = (JSONArray) jsonObject.get("libros");
            var iterator = tags.iterator();
            while (iterator.hasNext()) {
                JSONObject usuario = (JSONObject) iterator.next();
                long isbn = (long) usuario.get("ISBN");
                long year = (long) usuario.get("Año");
                String language = (String) usuario.get("Idioma");
                String title = (String) usuario.get("Titulo");
                String editorial = (String) usuario.get("Editorial");
                String author = (String) usuario.get("Autor");
                long edition = (long) usuario.get("Edicion");
                String category = (String) usuario.get("Categoria");
                Category library = new Category(category, this.user.getCarnet());
                if (!categories.contains(library)) {
                    this.categories.insert(library);
                }
                Book book = new Book(isbn,title,author,editorial,year, (int) edition,category,language, this.user.getCarnet());
                Category lib = (Category) categories.search(library);
                lib.getBooks().add(book);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(Operational_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void graficarPrueba() {
        System.out.println("<<<<<<---GRAFICANDO ARBOL AVL---->>>>>>>");
        System.out.println(this.categories.graph());
        System.out.println("<<<<<<---GRAFICANDO ARBOLES B---->>>>>>>");
        Iterator i = this.categories.iterator();
        while (i.hasNext()) {
            System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(((Category) i.next()).getBooks().graph());
            System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        System.out.println("<<<<<<---GRAFICANDO TABLA HASH---->>>>>>>");
        System.out.println(this.users.graph());
    }
}

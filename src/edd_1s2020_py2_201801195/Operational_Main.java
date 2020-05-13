/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_1s2020_py2_201801195;

import AvlTree.AvlTree;
import Comunication.Client;
import Comunication.Ip;
import Comunication.Server;
import DoublyLinkedList.DoublyLinkedList;
import HashTable.HashTable;
import LinkedList.LinkedList;
import Objects.Block;
import Objects.Book;
import Objects.Category;
import Objects.Data.Create_book;
import Objects.Data.Create_category;
import Objects.Data.Create_user;
import Objects.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.sql.Timestamp;
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
public class Operational_Main implements Serializable{

    public HashTable users;
    public AvlTree categories;
    public Student user;
    public DoublyLinkedList blockchain;
    public LinkedList web;
    public Block actualBlock;
    public Ip thisMachine;
    public Server server;
    public Client client;
    public String miCarpeta;

    public Operational_Main() {
        users = new HashTable();
        categories = new AvlTree();
        blockchain = new DoublyLinkedList();
        actualBlock = new Block();
        web = new LinkedList();
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
                    this.actualBlock.addData(new Create_user (carnet, nombre, apellido, carrera, pass));
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
                    this.actualBlock.addData(new Create_category(category));
                }
                Book book = new Book(isbn, title, author, editorial, year, (int) edition, category, language, this.user.getCarnet());
                Category lib = (Category) categories.search(library);
                if (!lib.getBooks().contains(book)) {
                    lib.getBooks().add(book);
                    this.actualBlock.addData(new Create_book(isbn, year, language, title, editorial, author, edition, category));
                }
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

    public void genAndRunImg(String dot) {
        try {
            //File myObj = new File("filename.txt");
            JFileChooser fileChooser = new JFileChooser();
            int sel = fileChooser.showDialog(null, "Seleccionar ruta");
            if (sel == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getCurrentDirectory().toString();
                FileWriter myWriter = new FileWriter(path + "\\img.dot");
                myWriter.write(dot);
                myWriter.close();
                FileWriter batFile = new FileWriter(path + "\\img.bat");
                String batCommands = "cd " + path + "\ndot -Tpng img.dot -o img.png\nimg.png";
                batFile.write(batCommands);
                batFile.close();
                Process child = Runtime.getRuntime().exec(path + "\\img.bat");
                child = Runtime.getRuntime().exec(path + "\\img.bat");
            } else {

            }

        } catch (Exception e) {
        }
    }

    public void addBlock() throws NoSuchAlgorithmException {
       
        // Calculating index
        if (this.blockchain.size() == 0) {
            this.actualBlock.setINDEX(0);
        } else {
            this.actualBlock.setINDEX(this.blockchain.size());
        }

        // TIMESTAMP
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.actualBlock.setTIMESTAMP(ts.toString());

        // PREVIOUSHASH
        if (this.blockchain.size() == 0) {
            this.actualBlock.setPREVIOUSHASH("0000");
        } else {
            this.actualBlock.setPREVIOUSHASH(((Block) this.blockchain.peekLast()).getHASH());
        }

        // NONCE
        // This will be start in zero and if the condition doesnt get a valid
        // hash it will be changed por the next major number.
        this.actualBlock.setNONCE(0);

        // Area to generate a valid HASH
        // I need INDEX, TIMESTAMP, PREVIOUSHASH, DATA and NONCE
        String hash = this.SHA256(actualBlock.getINDEX() + actualBlock.getTIMESTAMP()
                + actualBlock.getPREVIOUSHASH() + actualBlock.getDATA() + actualBlock.getNONCE());
        while (true) {
            //Geting a iterative NONCE
            this.actualBlock.setNONCE(this.actualBlock.getNONCE() + 1);
            // Get a new Timestamp
            ts = new Timestamp(System.currentTimeMillis());
            this.actualBlock.setTIMESTAMP(ts.toString());
            hash = this.SHA256(actualBlock.getINDEX() + actualBlock.getTIMESTAMP()
                    + actualBlock.getPREVIOUSHASH() + actualBlock.getData() + actualBlock.getNONCE());
            if (hash.charAt(0) == '0' && hash.charAt(1) == '0' && hash.charAt(2) == '0') {
                this.actualBlock.setHASH(hash);
                break;
            }
            System.out.println("HASH NO." + this.actualBlock.getNONCE() + ": " + hash);
        }
        //<-------------------------------------------------------------->
        System.out.println("[HASH NO." +this.actualBlock.getNONCE() + " CORRECTO: " + this.actualBlock.getHASH() + "]");
        this.blockchain.add(this.actualBlock);
        System.out.println(this.actualBlock.toString());
        this.actualBlock = new Block();
        
    }
    
    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     *
     * @param data
     * @return
     */
    public String SHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    
    public void getJsonData()
    {
        Iterator i = this.blockchain.iterator();
        while(i.hasNext())
        {
            Block b = (Block) i.next();
            System.out.println(b.toString());
        }
    }
    
    public void writeJsonFile()
    {
        try (FileWriter file = new FileWriter(this.miCarpeta + "\\bloques.json")) {
 
            file.write(getJsonFile());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getJsonFile()
    {
        String answer = "[";
        Iterator i = this.blockchain.iterator();
        while(i.hasNext())
        {
            Block b = (Block) i.next();
            answer += b.toString() + ",";
        }
        answer = answer.substring(0,answer.length() - 1);
        answer += "]";
        return answer;
    }
    
}

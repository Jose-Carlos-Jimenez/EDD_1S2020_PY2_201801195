/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BTree.BTree;
import Comunication.Client;
import Comunication.Server;
import Objects.Book;
import Objects.Category;
import Objects.Data.Create_book;
import Objects.Data.Create_category;
import Objects.Data.Delete_book;
import Objects.Data.Delete_category;
import Objects.Data.Edit_user;
import Objects.Student;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jose Carlos Jimenez
 */
public class Layout extends javax.swing.JFrame implements Observer {

    Book actual;
    Server s;

    /**
     * Creates new form Layout
     */
    public Layout() {
        initComponents();
        this.setTable();
        this.setLocationRelativeTo(null);
        this.getData();
        this.modificar.setEnabled(false);
    }

    public Layout(Server s) {
        initComponents();
        this.setTable();
        this.setLocationRelativeTo(null);
        this.getData();
        this.modificar.setEnabled(false);
        this.ip.setText(ip.getText() + " " + edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getIp());
        this.puerto.setText(puerto.getText() + " " + edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.thisMachine.getPort());
        s.addObserver(this);
    }

    private void setTable() {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        Iterator i = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.iterator();
        while (i.hasNext()) {
            Category cat = (Category) i.next();
            String[] row = {cat.getName()};
            model.addRow(row);
        }
    }

    private void getData() {
        this.nombre.setText(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getNombre());
        this.apellido.setText(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getApellido());
        this.carrera.setText(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarrera());
    }

    private void addBook() {
        if (!"".equals(this.title.getText()) && !"".equals(this.isbn.getText())) {
            long isb = Long.parseLong(this.isbn.getText());
            long año = Long.parseLong(this.year.getText());
            String lenguaje = this.language.getText();
            String titulo = this.title.getText();
            String editor = this.editorial.getText();
            String autor = this.author.getText();
            long edicion = Long.parseLong(this.year.getText());
            String categoria = this.category.getText();
            Category library = new Category(categoria, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet());
            if (!edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.contains(library)) {
                edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.insert(library);
                edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Create_category(categoria, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()));
            }
            Book book = new Book(isb, titulo, autor, editor, año, (int) edicion, categoria, lenguaje, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet());
            Category lib = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(library);
            if (!lib.books.contains(book)) {
                lib.getBooks().add(book);
                edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Create_book(isb, año, lenguaje, titulo, editor, autor, edicion, categoria, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()));
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                DefaultTableModel mod = (DefaultTableModel) libros.getModel();
                model.setRowCount(0);
                Iterator i = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.iterator();
                while (i.hasNext()) {
                    Category cat = (Category) i.next();
                    String[] row = {cat.getName()};
                    model.addRow(row);
                }
                mod.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(this, "El libro que deseas insertar\nya pertenece a la librería.");
            }
        }
    }

    private void addCategory() {
        String categoria = this.categ.getText();
        Category library = new Category(categoria, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet());
        if (!edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.contains(library)) {
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.insert(library);
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Create_category(categoria, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()));
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);
            Iterator i = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.iterator();
            while (i.hasNext()) {
                Category cat = (Category) i.next();
                String[] row = {cat.getName()};
                model.addRow(row);
            }
            System.out.println("[SE HA AGREGADO UNA NUEVA CATEGORIA]");
        } else {
            System.out.println("[LA CATEGORIA YA EXISTE]");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        biblioteca = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        buscar = new javax.swing.JTextField();
        title = new javax.swing.JTextField();
        author = new javax.swing.JTextField();
        isbn = new javax.swing.JTextField();
        editorial = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        edition = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        category = new javax.swing.JTextField();
        language = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        libros = new javax.swing.JTable();
        categ = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        usuario = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        carrera = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        puerto = new javax.swing.JLabel();
        ip = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplicacion");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        biblioteca.setBackground(new java.awt.Color(255, 255, 255));
        biblioteca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla.setBackground(new java.awt.Color(255, 255, 255));
        tabla.setForeground(new java.awt.Color(0, 0, 0));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoría"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tablaMouseDragged(evt);
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaPropertyChange(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        buscar.setBackground(new java.awt.Color(204, 204, 204));
        buscar.setForeground(new java.awt.Color(0, 0, 0));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        title.setBackground(new java.awt.Color(204, 204, 204));
        title.setForeground(new java.awt.Color(0, 0, 0));

        author.setBackground(new java.awt.Color(204, 204, 204));
        author.setForeground(new java.awt.Color(0, 0, 0));

        isbn.setBackground(new java.awt.Color(204, 204, 204));
        isbn.setForeground(new java.awt.Color(0, 0, 0));
        isbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isbnActionPerformed(evt);
            }
        });

        editorial.setBackground(new java.awt.Color(204, 204, 204));
        editorial.setForeground(new java.awt.Color(0, 0, 0));

        year.setBackground(new java.awt.Color(204, 204, 204));
        year.setForeground(new java.awt.Color(0, 0, 0));
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });

        edition.setBackground(new java.awt.Color(204, 204, 204));
        edition.setForeground(new java.awt.Color(0, 0, 0));
        edition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editionActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ISBN:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Título:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Autor:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Editorial:");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Año:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Edición:");

        category.setBackground(new java.awt.Color(204, 204, 204));
        category.setForeground(new java.awt.Color(0, 0, 0));
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });

        language.setBackground(new java.awt.Color(204, 204, 204));
        language.setForeground(new java.awt.Color(0, 0, 0));
        language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Categoría:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Idioma:");

        agregar.setBackground(new java.awt.Color(0, 0, 0));
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("Agregar libro");
        agregar.setBorder(null);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        libros.setBackground(new java.awt.Color(255, 255, 255));
        libros.setForeground(new java.awt.Color(0, 0, 0));
        libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "ISBN", "Propietario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        libros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                librosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(libros);

        categ.setBackground(new java.awt.Color(204, 204, 204));
        categ.setForeground(new java.awt.Color(0, 0, 0));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Agregar categoría");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Categoria:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("LIBRERIA");

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("Ver solo mis libros.");
        jCheckBox1.setToolTipText("");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Borrar Cat");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        modificar.setBackground(new java.awt.Color(0, 0, 0));
        modificar.setForeground(new java.awt.Color(255, 255, 255));
        modificar.setText("Modificar libro");
        modificar.setBorder(null);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Actualizar");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Desconectarse");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bibliotecaLayout = new javax.swing.GroupLayout(biblioteca);
        biblioteca.setLayout(bibliotecaLayout);
        bibliotecaLayout.setHorizontalGroup(
            bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bibliotecaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(bibliotecaLayout.createSequentialGroup()
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(buscar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bibliotecaLayout.createSequentialGroup()
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(bibliotecaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(categ))
                            .addGroup(bibliotecaLayout.createSequentialGroup()
                                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                    .addComponent(author)
                                    .addComponent(editorial)
                                    .addComponent(year)
                                    .addComponent(edition)
                                    .addComponent(category)
                                    .addComponent(language)
                                    .addComponent(isbn)
                                    .addGroup(bibliotecaLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(modificar)
                                        .addGap(18, 18, 18)
                                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(100, 100, 100))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))))))
        );
        bibliotecaLayout.setVerticalGroup(
            bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bibliotecaLayout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bibliotecaLayout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(bibliotecaLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bibliotecaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(isbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(language, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(biblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 570));

        usuario.setBackground(new java.awt.Color(255, 255, 255));
        usuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("USUARIO");

        nombre.setBackground(new java.awt.Color(204, 204, 204));
        nombre.setForeground(new java.awt.Color(0, 0, 0));

        apellido.setBackground(new java.awt.Color(204, 204, 204));
        apellido.setForeground(new java.awt.Color(0, 0, 0));

        carrera.setBackground(new java.awt.Color(204, 204, 204));
        carrera.setForeground(new java.awt.Color(255, 51, 51));

        pass.setBackground(new java.awt.Color(204, 204, 204));
        pass.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nombre:");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Apellido:");

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Carrera: ");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Contraseña:");

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar cambio");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Darme de baja");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        puerto.setBackground(new java.awt.Color(255, 255, 255));
        puerto.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        puerto.setForeground(new java.awt.Color(0, 0, 0));
        puerto.setText("PUERTO: ");

        ip.setBackground(new java.awt.Color(255, 255, 255));
        ip.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        ip.setForeground(new java.awt.Color(0, 0, 0));
        ip.setText("IP: ");

        javax.swing.GroupLayout usuarioLayout = new javax.swing.GroupLayout(usuario);
        usuario.setLayout(usuarioLayout);
        usuarioLayout.setHorizontalGroup(
            usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuarioLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(118, 118, 118))
            .addGroup(usuarioLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuarioLayout.createSequentialGroup()
                        .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ip)
                        .addComponent(puerto)
                        .addGroup(usuarioLayout.createSequentialGroup()
                            .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        usuarioLayout.setVerticalGroup(
            usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuarioLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(ip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(puerto)
                .addGap(128, 128, 128))
        );

        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 410, 570));

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Reportes");

        jMenuItem1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setText("Tabla Hash");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setText("Árbol AVL");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem4.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setText("Blockchain");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setText("PreOrden");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem5.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem5.setText("PosOrden");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem6.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem6.setText("EnOrden");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem7.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem7.setText("Nodos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(0, 0, 0));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Cargar libros");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(0, 0, 0));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Generar bloque");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearActionPerformed

    private void editionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editionActionPerformed

    private void isbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isbnActionPerformed

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed

    private void languageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_languageActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        this.addBook();
    }//GEN-LAST:event_agregarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int selection = this.tabla.getSelectedRow();
        if (SwingUtilities.isRightMouseButton(evt)) {
            DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
            if (selection > this.tabla.getModel().getRowCount()) {

            } else {
                String nombre = (String) this.tabla.getValueAt(selection, 0);
                Category c = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(nombre, ""));
                edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(c.books.graph());
                System.out.println("[REPORTE ABIERTO]");
            }
        } else {

            DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
            if (selection > this.tabla.getModel().getRowCount()) {

            } else {
                String nombre = (String) this.tabla.getValueAt(selection, 0);
                Collection d = ((Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(nombre, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()))).getBooks().toCollection();
                Iterator i = d.iterator();
                m.setRowCount(0);
                while (i.hasNext()) {
                    Book book = (Book) i.next();
                    String[] row = {book.getTitle(), book.getIsbnS(), book.getAdedBy()};
                    m.addRow(row);
                }
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaPropertyChange

    }//GEN-LAST:event_tablaPropertyChange

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        int selection = this.tabla.getSelectedRow();
        if (evt.getKeyCode() == 38) {
            if (selection != 0) {
                selection--;
            }
        } else if (evt.getKeyCode() == 40) {
            if (selection != this.tabla.getRowCount() - 1) {
                selection++;
            }
        }
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
            if (selection > this.tabla.getModel().getRowCount()) {

            } else {
                String nombre = (String) this.tabla.getValueAt(selection, 0);
                Collection d = ((Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(nombre, edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()))).getBooks().toCollection();
                Iterator i = d.iterator();
                m.setRowCount(0);
                while (i.hasNext()) {
                    Book book = (Book) i.next();
                    String[] row = {book.getTitle(), book.getIsbnS(), book.getAdedBy()};
                    m.addRow(row);
                }
            }
        } else if (evt.getKeyCode() == 13) {
            DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
            if (selection > this.tabla.getModel().getRowCount()) {

            } else {
                String nombre = (String) this.tabla.getValueAt(selection, 0);
                Category c = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(nombre, ""));
                System.out.println(c.books.graph());
            }
        }
    }//GEN-LAST:event_tablaKeyPressed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) this.libros.getModel());
        this.libros.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(this.buscar.getText()));
    }//GEN-LAST:event_buscarKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int sel = JOptionPane.showConfirmDialog(null, "¿Seguro que desea darse de baja?");
        if (sel == 0) {
            Iterator m = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.iterator();
            while (m.hasNext()) {
                Category cat = ((Category) m.next());
                BTree tree = cat.books;
                Iterator it = tree.toCollection().iterator();
                while (it.hasNext()) {
                    Book b = (Book) it.next();
                    try {
                        if (b.getAdedBy() == edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()) {
                            Category auxCat = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(cat.getName(), ""));
                            auxCat.books.remove(new Book(b.getIsbnS()));
                            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Delete_book(b.getIsbn(), b.getTitle(), b.getCategory()));
                            it = tree.toCollection().iterator();
                        }
                    } catch (Exception e) {
                        System.out.println("<<<<<<<<<<<<<<<DEFECADO>>>>>>>>>>>>>>>>>>>>");
                    }
                }
            }
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.users.eliminar(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user);
            System.out.println("[USUARIO DADO DE BAJA]");
            this.dispose();
        } else {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.setNombre(this.nombre.getText());
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.setApellido(this.apellido.getText());
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.setPassword(this.pass.getText());
            Student aux = edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user;
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Edit_user(Long.parseLong(aux.getCarnet()), aux.getNombre(), aux.getApellido(), aux.getCarrera(), aux.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.addCategory();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.users.graph());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if (this.jCheckBox1.isSelected()) {
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) this.libros.getModel());
            this.libros.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()));
            JOptionPane.showMessageDialog(null, "Biblioteca personal activada.");
        } else {
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>((DefaultTableModel) this.libros.getModel());
            this.libros.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(this.buscar.getText()));
            JOptionPane.showMessageDialog(null, "Biblioteca global activada.");
        }
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.getBlockChainString());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.graph());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.readBooks();
        this.setTable();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void tablaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseDragged
    }//GEN-LAST:event_tablaMouseDragged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String op = JOptionPane.showInputDialog("¿Que desea realizar?\n"
                + "1. Eliminar una categoría.\n"
                + "2. Cancelar\n");
        int opcion = Integer.parseInt(op);
        switch (opcion) {
            case 1:
                System.out.println("[ELIMINANDO CATEGORÍA]");
                String catName = JOptionPane.showInputDialog("Ingrese el nombre de la categoría: ");
                System.out.println("[ELIMINANDO LA CATEGORIA: " + catName + "]");
                if (edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.contains(new Category(catName, ""))) {
                    System.out.println("[¡EXISTE!]");
                    Category aux = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(catName, ""));
                    if (aux.getCreator().equals(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet())) {
                        System.out.println("[ACCESO CONCEDIDO]");
                        int dec = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas borrar " + catName + "?");
                        if (dec == 0) {
                            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Delete_category(catName));
                            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.remove(aux);
                            this.setTable();
                            DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
                            m.setRowCount(0);

                        } else if (dec == 1) {
                            System.out.println("[DAR DE BAJA CANCELADA]");
                        }
                    } else {
                        System.out.println("[ACCESO DENEGADO]");
                        JOptionPane.showMessageDialog(null, "La categoría que intentas borrar no es de tu propiedad.");
                    }
                } else {
                    System.out.println("[NO EXISTE LA CATEGORIA]");
                }
                break;
            case 2:
                System.out.println("[OPERACIÓN CANCELADA]");
                break;
            default:
                System.out.println("[OPCIÓN INVALIDA]");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void librosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_librosMouseClicked
        // Getting the name of the book.
        int selectionBook = this.libros.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) this.libros.getModel();
        String bookName = (String) model.getValueAt(selectionBook, 1);

        // Getting the name of the category.
        int selectedCat = this.tabla.getSelectedRow();
        DefaultTableModel mod = (DefaultTableModel) this.tabla.getModel();
        String categoryName = (String) mod.getValueAt(selectedCat, 0);

        // Path to find this book and get data and deleting that
        Category cat = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(categoryName, ""));

        String op = JOptionPane.showInputDialog("¿Que desea realizar?\n"
                + "1. Eliminar el libro.\n"
                + "2. Modificar el libro\n"
                + "3. Ver ficha de datos\n"
                + "4. Cancelar");
        int opcion = Integer.parseInt(op);
        switch (opcion) {
            case 1:
                // Deleting
                // Path to find this book and get data and deleting that
                if (edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.contains(new Category(categoryName, ""))) {

                    Category aux = (Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(categoryName, ""));
                    Book b = (Book) cat.getBooks().search(new Book(bookName));
                    if (b.getAdedBy().equals(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet())) {
                        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.actualBlock.addData(new Delete_book(b.getIsbn(), b.getTitle(), b.getCategory()));
                        aux.books.remove(new Book(bookName));
                        model.setRowCount(0);
                        System.out.println("[SE HA ELIMINADO EL LIBRIO CON ÉXITO]");
                        JOptionPane.showInputDialog("Ingrese la razón por la cual se eliminó:");
                    } else {
                        System.out.println("[SE HA ENVIADO LA PETICIÓN DE CAMBIOS AL DUEÑO]");
                    }
                }
                break;
            case 2:
                // Puting off the use of add button and puting on the button to modify
                this.modificar.setEnabled(true);
                this.agregar.setEnabled(false);
                //Adding to a form in GUI
                Book b = (Book) cat.getBooks().search(new Book(bookName));
                this.actual = b;
                this.isbn.setText(b.getIsbnS());
                this.title.setText(b.getTitle());
                this.author.setText(b.getAuthor());
                this.editorial.setText(b.getEditorial());
                this.year.setText(b.getYear() + "");
                this.edition.setText(b.getEdition() + "");
                this.category.setText(b.getCategory());
                this.language.setText(b.getLanguage());
                break;
            case 3:
                Book book = (Book) cat.books.search(new Book(bookName));
                JOptionPane.showMessageDialog(null,
                        "Subido por: " + book.getAdedBy() + "\n"
                        + "Titulo: " + book.getTitle() + "\n"
                        + "Autor: " + book.getAuthor() + "\n"
                        + "Categoría: " + book.getCategory() + "\n"
                        + "Editorial: " + book.getEditorial() + "\n"
                        + "ISBN: " + book.getIsbnS() + "\n"
                        + "Lenguaje: " + book.getLanguage() + "\n"
                        + "Año: " + book.getYear() + "\n");
                break;
            case 4:
                System.out.println("[OPERACIÓN CANCELADA]");
                break;
            default:
                System.out.println("[OPCIÓN INVALIDA]");
        }
    }//GEN-LAST:event_librosMouseClicked

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        this.modificar.setEnabled(false);
        this.agregar.setEnabled(true);
        if (this.actual.getAdedBy() == edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.user.getCarnet()) {
            String categ = this.actual.getCategory();
            //Setting the book data to the new data.
            this.actual.setTitle(this.title.getText());
            this.actual.setAuthor(this.author.getText());
            this.actual.setEditorial(this.editorial.getText());
            this.actual.setYear(Long.parseLong(this.year.getText()));
            this.actual.setEdition(Long.parseLong(this.edition.getText()));
            this.actual.setCategory(this.category.getText());
            this.actual.setLanguage(this.language.getText());
            ((Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(categ, ""))).books.remove(actual);
            ((Category) edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.search(new Category(actual.getCategory(), ""))).books.add(actual);
            //Cleaning the textbox
            this.isbn.setText("");
            this.title.setText("");
            this.author.setText("");
            this.editorial.setText("");
            this.year.setText("");
            this.edition.setText("");
            this.category.setText("");
            this.language.setText("");
            System.out.println("[SE HA MODIFICADO EL LIBRO]");
            this.setTable();
            ((DefaultTableModel) this.libros.getModel()).setRowCount(0);
        } else {
            //Cleaning the textbox
            this.isbn.setText("");
            this.title.setText("");
            this.author.setText("");
            this.editorial.setText("");
            this.year.setText("");
            this.edition.setText("");
            this.category.setText("");
            this.language.setText("");
            JOptionPane.showMessageDialog(null, "Se ha enviado la petición de modificación al propietario del libro.");
            System.out.println("[SE HA ENVIADO LA PETICIÓN DE MODIFICACIÓN AL DUEÑO]");
        }
    }//GEN-LAST:event_modificarActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.users.graph());
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.graph());
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.getPreOrderDot());
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.getPostOrderDot());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.categories.getInOrderDot());
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        try {
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.addBlock();
            edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.writeJsonFile();
            Client c = new Client("distribuir");
            Thread t = new Thread(c);
            t.start();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.genAndRunImg(edd_1s2020_py2_201801195.EDD_1S2020_PY2_201801195.main.web.dot());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setTable();
        DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
        m.setRowCount(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Client c = new Client("out");
        Thread t = new Thread(c);
        t.start();
        try {
            Thread.sleep(4000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Layout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Layout().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField author;
    private javax.swing.JPanel biblioteca;
    private javax.swing.JTextField buscar;
    private javax.swing.JTextField carrera;
    private javax.swing.JTextField categ;
    private javax.swing.JTextField category;
    private javax.swing.JTextField edition;
    private javax.swing.JTextField editorial;
    private javax.swing.JLabel ip;
    private javax.swing.JTextField isbn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField language;
    private javax.swing.JTable libros;
    private javax.swing.JButton modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField pass;
    private javax.swing.JLabel puerto;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField title;
    private javax.swing.JPanel usuario;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable G, Object arg) {
        this.setTable();
        DefaultTableModel m = (DefaultTableModel) this.libros.getModel();
        m.setRowCount(0);
    }
}

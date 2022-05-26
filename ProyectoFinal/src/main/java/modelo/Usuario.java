package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Usuario {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String pass = "";
    private final static String rutaA = "C:\\Users\\jesug\\OneDrive\\Documentos\\Clase\\1º DAM (2º Curso)\\Prog\\ProyectoFinal\\src\\main\\java\\modelo\\usuarios.txt";

    private File f;

    private FileWriter fw;

    private PrintWriter pw;

    private Statement state;

    private ResultSet rs;

    private String id;
    private String username;
    private String password;
    private String email;
    private String genero;
    private String fNacimiento;
    private String pais;
    private String cp;

    private Button seguir;

    public Usuario() {



    }

    public Usuario(String id, String username, String password, String email, String genero, String fNacimiento, String pais, String cp) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.fNacimiento = fNacimiento;
        this.pais = pais;
        this.cp = cp;

        this.seguir = new Button();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Button getSeguir() {
        return seguir;
    }

    public void setSeguir(Button seguir) {
        this.seguir = seguir;
    }

    public ObservableList<Usuario> consultas() {

        ObservableList<Usuario> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM usuario";

        try {

            conex = conexion();

            if (conex != null) {

                state = conex.createStatement();

                rs = state.executeQuery(

                        nombreBD

                );

                while (rs.next()) {

                    id = rs.getString("id");
                    username = rs.getString("username");
                    password = rs.getString("password");
                    email = rs.getString("email");
                    genero = rs.getString("genero");
                    fNacimiento = rs.getString("fecha_nacimiento");
                    pais = rs.getString("pais");
                    cp = rs.getString("codigo_postal");

                    Usuario u = new Usuario(id, username, password, email, genero, fNacimiento, pais, cp);

                    ol.add(u);

                }

            }

            return ol;

        } catch (SQLException sqle) {

            System.out.println(sqle.getMessage());

        }

        return null;

    }

    private Connection conexion() throws SQLException {

        Connection conex = null;

        try {

            conex = DriverManager.getConnection(url, user, pass);

        } catch (SQLException sqle) {

            System.out.println(sqle.getMessage());

        }

        return conex;

    }

}

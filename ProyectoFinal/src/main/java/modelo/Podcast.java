package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Podcast {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String pass = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String titulo;
    private String descripcion;
    private String anyo;

    public Podcast() {



    }

    public Podcast(String id, String titulo, String descripcion, String anyo) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.anyo = anyo;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public ObservableList<Podcast> consultas() {

        ObservableList<Podcast> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM podcast";

        try {

            conex = conexion();

            if (conex != null) {

                state = conex.createStatement();

                rs = state.executeQuery(

                        nombreBD

                );

                while (rs.next()) {

                    id = rs.getString("id");
                    titulo = rs.getString("titulo");
                    descripcion = rs.getString("descripcion");
                    anyo = rs.getString("anyo");

                    Podcast p = new Podcast(id, titulo, descripcion, anyo);

                    ol.add(p);

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

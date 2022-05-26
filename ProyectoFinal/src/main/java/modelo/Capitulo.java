package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Capitulo {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String fecha;
    private String nReproducciones;
    private String idPodcast;

    private String nombreBD;

    public Capitulo() {



    }

    public Capitulo(String id, String titulo, String descripcion, String duracion, String fecha, String nReproducciones, String idPodcast) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.fecha = fecha;
        this.nReproducciones = nReproducciones;
        this.idPodcast = idPodcast;

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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNReproducciones() {
        return nReproducciones;
    }

    public void setNReproducciones(String nReproducciones) {
        this.nReproducciones = nReproducciones;
    }

    public String getIdPodcast() {
        return idPodcast;
    }

    public void setIdPodcast(String idPodcast) {
        this.idPodcast = idPodcast;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public ObservableList<Capitulo> consultas() {

        ObservableList<Capitulo> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM capitulo";

        try {

            conex = conexion();

            if (conex != null) {

                state = conex.createStatement();

                if (getNombreBD() == null) {

                    setNombreBD(nombreBD);

                    rs = state.executeQuery(

                            getNombreBD()

                    );

                    while (rs.next()) {

                        id = rs.getString("id");
                        titulo = rs.getString("titulo");
                        descripcion = rs.getString("descripcion");
                        duracion = rs.getString("duracion");
                        fecha = rs.getString("fecha");
                        nReproducciones = rs.getString("numero_reproducciones");
                        idPodcast = rs.getString("podcast_id");

                        Capitulo c = new Capitulo(id, titulo, descripcion, duracion, fecha, nReproducciones, idPodcast);

                        ol.add(c);

                    }

                } else {

                    rs = state.executeQuery(

                            getNombreBD()

                    );

                    while (rs.next()) {

                        id = rs.getString("id");
                        titulo = rs.getString("titulo");
                        descripcion = rs.getString("descripcion");
                        duracion = rs.getString("duracion");
                        fecha = rs.getString("fecha");
                        nReproducciones = rs.getString("numero_reproducciones");
                        idPodcast = rs.getString("podcast_id");

                        Capitulo c = new Capitulo(id, titulo, descripcion, duracion, fecha, nReproducciones, idPodcast);

                        ol.add(c);

                    }

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

            conex = DriverManager.getConnection(url, user, password);

        } catch (SQLException sqle) {

            System.out.println(sqle.getMessage());

        }

        return conex;

    }

}

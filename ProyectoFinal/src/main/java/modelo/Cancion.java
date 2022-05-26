package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import java.sql.*;

public class Cancion {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String titulo;
    private String duracion;
    private String ruta;
    private String nReproducciones;
    private String idAlbum;

    private CheckBox like;

    public Cancion() {



    }

    public Cancion(String id, String titulo, String duracion, String ruta, String nReproducciones, String idAlbum) {

        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.ruta = ruta;
        this.nReproducciones = nReproducciones;
        this.idAlbum = idAlbum;

        this.like = new CheckBox();

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

    public String getDuracion() {

        return duracion;

    }

    public void setDuracion(String duracion) {

        this.duracion = duracion;

    }

    public String getRuta() {

        return ruta;

    }

    public void setRuta(String ruta) {

        this.ruta = ruta;

    }

    public String getNReproducciones() {

        return nReproducciones;

    }

    public void setNReproducciones(String nReproducciones) {

        this.nReproducciones = nReproducciones;

    }

    public String getIdAlbum() {

        return idAlbum;

    }

    public void setIdAlbum(String idAlbum) {

        this.idAlbum = idAlbum;

    }

    public CheckBox getLike() {

        return like;

    }

    public void setLike(CheckBox like) {

        this.like = like;

    }

    public ObservableList<Cancion> consultas() {

        ObservableList<Cancion> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM cancion";

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
                    duracion = rs.getString("duracion");
                    ruta = rs.getString("ruta");
                    nReproducciones = rs.getString("numero_reproducciones");
                    idAlbum = rs.getString("album_id");

                    Cancion c = new Cancion(id, titulo, duracion, ruta, nReproducciones, idAlbum);

                    ol.add(c);

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

    @Override
    public String toString() {

        return "Id: " + id + ", Titulo: " + titulo + ", Duracion: " + duracion + ", Ruta: " + ruta + ", Numero de Reproducciones: " +
                nReproducciones + "Id Album: " + idAlbum + ", Like: " + like.isSelected();

    }

}

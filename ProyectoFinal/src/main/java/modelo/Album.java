package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Album {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String anyo;
    private String titulo;
    private String imagen;
    private String patrocinado;
    private String fIPatrocinio;
    private String fFPatrocinio;
    private String idArtista;

    private String nombreBD;

    public Album() {



    }

    public Album(String id, String anyo, String titulo, String imagen, String patrocinado, String fIPatrocinio, String fFPatrocinio, String idArtista) {

        this.id = id;
        this.anyo = anyo;
        this.titulo = titulo;
        this.imagen = imagen;
        this.patrocinado = patrocinado;
        this.fIPatrocinio = fIPatrocinio;
        this.fFPatrocinio = fFPatrocinio;
        this.idArtista = idArtista;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPatrocinado() {
        return patrocinado;
    }

    public void setPatrocinado(String patrocinado) {
        this.patrocinado = patrocinado;
    }

    public String getFIPatrocinio() {
        return fIPatrocinio;
    }

    public void setFIPatrocinio(String fIPatrocinio) {
        this.fIPatrocinio = fIPatrocinio;
    }

    public String getFFPatrocinio() {
        return fFPatrocinio;
    }

    public void setFFPatrocinio(String fFPatrocinio) {
        this.fFPatrocinio = fFPatrocinio;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public ObservableList<Album> consultas() {

        ObservableList<Album> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM album";

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
                        anyo = rs.getString("anyo");
                        titulo = rs.getString("titulo");
                        imagen = rs.getString("imagen");
                        patrocinado = rs.getString("patrocinado");
                        fIPatrocinio = rs.getString("fecha_inicio_patrocinio");
                        fFPatrocinio = rs.getString("fecha_fin_patrocinio");
                        idArtista = rs.getString("artista_id");

                        Album a = new Album(id, anyo, titulo, imagen, patrocinado, fIPatrocinio, fFPatrocinio, idArtista);

                        ol.add(a);

                    }

                } else {

                    rs = state.executeQuery(

                            getNombreBD()

                    );

                    while (rs.next()) {

                        id = rs.getString("id");
                        anyo = rs.getString("anyo");
                        titulo = rs.getString("titulo");
                        imagen = rs.getString("imagen");
                        patrocinado = rs.getString("patrocinado");
                        fIPatrocinio = rs.getString("fecha_inicio_patrocinio");
                        fFPatrocinio = rs.getString("fecha_fin_patrocinio");
                        idArtista = rs.getString("artista_id");

                        Album a = new Album(id, anyo, titulo, imagen, patrocinado, fIPatrocinio, fFPatrocinio, idArtista);

                        ol.add(a);

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

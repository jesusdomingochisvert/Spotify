package modelo;

import com.example.proyectofinal.ISController;
import com.example.proyectofinal.SpotifyApp;
import com.example.proyectofinal.SpotifyController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class PlayList {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String titulo;
    private String nCanciones;
    private String fCreacion;
    private String idUsuario;

    private ISController isc;

    private String nick;

    public PlayList() {



    }

    public PlayList(String id, String titulo, String nCanciones, String fCreacion, String idUsuario) {

        this.id = id;
        this.titulo = titulo;
        this.nCanciones = nCanciones;
        this.fCreacion = fCreacion;
        this.idUsuario = idUsuario;

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

    public String getNCanciones() {
        return nCanciones;
    }

    public void setNCanciones(String nCanciones) {
        this.nCanciones = nCanciones;
    }

    public String getFCreacion() {
        return fCreacion;
    }

    public void setFCreacion(String fCreacion) {
        this.fCreacion = fCreacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {

        this.nick = nick;

    }

    public ObservableList<PlayList> consultas() {

        isc = new ISController();

        ObservableList<PlayList> ol = isc.consultasIS();

        return ol;

    }

}

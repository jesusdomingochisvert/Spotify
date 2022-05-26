package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Artista {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String nombre;
    private String imagen;

    private String nombreBD;

    public Artista() {



    }

    public Artista(String id, String nombre, String imagen) {

        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public ObservableList<Artista> consultas() {

        ObservableList<Artista> ol = FXCollections.observableArrayList();

        Connection conex = null;

        String nombreBD = "SELECT * FROM artista";

        try {

            conex = conexion();

            if (conex != null) {

                state = conex.createStatement();

                if (getNombreBD() == null) {

                    setNombreBD(nombreBD);

                } else {

                    rs = state.executeQuery(

                            getNombreBD()

                    );

                    while (rs.next()) {

                        id = rs.getString("id");
                        nombre = rs.getString("nombre");
                        imagen = rs.getString("imagen");

                        Artista a = new Artista(id, nombre, imagen);

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

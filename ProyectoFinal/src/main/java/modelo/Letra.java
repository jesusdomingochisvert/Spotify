package modelo;

import java.io.*;
import java.sql.*;

public class Letra {

    private final static String url = "jdbc:mysql://localhost:3306/spotify";
    private final static String user = "root";
    private final static String password = "";
    private final static String rutaA = "C:\\Users\\jesug\\OneDrive\\Documentos\\Clase\\1º DAM (2º Curso)\\Prog\\ProyectoFinal\\src\\main\\java\\modelo\\letra.txt";

    private Statement state;

    private ResultSet rs;

    private String id;
    private String ruta;

    public Letra() {



    }

    public Letra(String id, String ruta) {

        this.id = id;
        this.ruta = ruta;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void consultas() throws IOException {

        Connection conex = null;

        String nombreBD = "SELECT * FROM letra";

        try {

            File f = new File(rutaA);

            FileWriter fw = new FileWriter(f);

            PrintWriter pw = new PrintWriter(fw);

            conex = conexion();

            if (conex != null) {

                state = conex.createStatement();

                rs = state.executeQuery(

                        nombreBD

                );

                while (rs.next()) {

                    id = rs.getString("id");
                    ruta = rs.getString("ruta");

                    pw.print(id + ";");
                    pw.print(ruta + ";");
                    pw.println();

                }

            }

        } catch (SQLException | FileNotFoundException sqle) {

            System.out.println(sqle.getMessage());

        }

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

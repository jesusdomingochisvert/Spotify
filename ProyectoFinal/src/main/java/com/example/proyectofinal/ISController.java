package com.example.proyectofinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.PlayList;
import modelo.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ISController implements Initializable {

    @FXML
    public TextField tfUsuario;

    @FXML
    private PasswordField pfContraseña;

    @FXML
    private Button bInicarSesion;

    @FXML
    private Button bRegistro;

    @FXML
    private Button bSalir;

    private Stage stage;

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

    public ISController() {



    }

    @FXML
    void inicarSesion(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApp.class.getResource("SpotifyView.fxml"));

        Parent root = fxmlLoader.load();

        SpotifyController sc = fxmlLoader.getController();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        Usuario u = new Usuario();

        sc.olSeguir = FXCollections.observableArrayList();

        sc.olSeguir = u.consultas();

        for (int i = 0; i < sc.olSeguir.size(); i++) {

            if (sc.olSeguir != null) {

                if (sc.olSeguir.get(i).getUsername().equals(tfUsuario.getText()) &&
                        sc.olSeguir.get(i).getPassword().equals(pfContraseña.getText())) {

                    sc.init(tfUsuario.getText(), stage, this);

                    consultasIS();

                    stage.show();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setHeaderText(null);

                    alert.setTitle("Informacion");

                    alert.setContentText("Inicio de Sesion Correcto.");

                    alert.show();

                    Button button;

                    button = (Button) event.getSource();

                    this.stage = (Stage) button.getScene().getWindow();

                    this.stage.close();

                    break;

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText(null);

                    alert.setTitle("Error");

                    alert.setContentText("Inicio de Sesion Incorrecto\nUsuario no Registrado.");

                    alert.show();

                }

            }

        }

    }

    public ObservableList<PlayList> consultasIS() {

        ObservableList<PlayList> ol = FXCollections.observableArrayList();

        Connection conex = null;

        Usuario u = new Usuario();

        SpotifyController sc = new SpotifyController();

        sc.olSeguir = u.consultas();

        for (int i = 0; i < sc.olSeguir.size(); i++) {

            String nombreBD = "SELECT * FROM playlist WHERE usuario_id = " + sc.olSeguir.get(i).getId();

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
                        nCanciones = rs.getString("numero_canciones");
                        fCreacion = rs.getString("fecha_creacion");
                        idUsuario = rs.getString("usuario_id");

                        PlayList pl = new PlayList(id, titulo, nCanciones, fCreacion, idUsuario);

                        ol.add(pl);

                    }

                }

                return ol;

            } catch (SQLException sqle) {

                System.out.println(sqle.getMessage());

            }

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

    @FXML
    void registrarUsuario(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApp.class.getResource("RegistroView.fxml"));

        Parent root = fxmlLoader.load();

        RegistroController rc = fxmlLoader.getController();

        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();

        Button button;

        button = (Button) event.getSource();

        this.stage = (Stage) button.getScene().getWindow();

        this.stage.close();

    }

    @FXML
    void salir(ActionEvent event) {

        Button button;

        Stage stage;

        button = (Button) event.getSource();

        stage = (Stage) button.getScene().getWindow();

        stage.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


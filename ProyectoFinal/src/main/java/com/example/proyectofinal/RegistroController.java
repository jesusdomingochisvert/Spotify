package com.example.proyectofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class RegistroController {

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfPassWord;

    @FXML
    private TextField tfEMail;

    @FXML
    private TextField tfGenero;

    @FXML
    private TextField tfFechaNacimiento;

    @FXML
    private TextField tfPais;

    @FXML
    private TextField tfCodigoPostal;

    @FXML
    private CheckBox chbPremium;

    @FXML
    private CheckBox chbFree;

    @FXML
    private CheckBox chbTarjetaCredito;

    @FXML
    private CheckBox chbPayPal;

    @FXML
    private Button bGuardarRegistro;

    @FXML
    private Button bSalirRegistro;

    @FXML
    void free(ActionEvent event) {



    }

    @FXML
    void guardarRegistro(ActionEvent event) {

        Connection conexion;

        Statement statement;

        ResultSet rs;

        String url = "jdbc:mysql://localhost:3306/spotify";
        String root = "root";
        String pass = "";
        String insert;
        String username = tfUserName.getText();
        String password = tfPassWord.getText();
        String email = tfEMail.getText();
        String genero = tfGenero.getText();
        String fNacimiento = tfFechaNacimiento.getText();
        String pais = tfPais.getText();
        String cp = tfCodigoPostal.getText();

        try {

            conexion = DriverManager.getConnection(url, root, pass);

            statement = conexion.createStatement();

            insert = "INSERT INTO usuario (username, password, email, genero, fecha_nacimiento, pais, codigo_postal) VALUES ('" +
                    username +
                    "', '" + password +
                    "', '" + email +
                    "', '" + genero +
                    "', '" + fNacimiento +
                    "', '" + pais +
                    "', '" + cp + "')";

            statement.executeUpdate(insert);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText(null);

            alert.setTitle("Confirmado");

            alert.setContentText("Usuario Registrado Correctamente.");

            alert.show();

        } catch (SQLException e) {

            System.out.println("Problema con la conexion.");

            System.out.println(e.getMessage());

        }

    }

    @FXML
    void premium(ActionEvent event) {



    }

    @FXML
    void salirRegistro(ActionEvent event) {

        Button button;

        Stage stage;

        button = (Button) event.getSource();

        stage = (Stage) button.getScene().getWindow();

        stage.close();

    }

}

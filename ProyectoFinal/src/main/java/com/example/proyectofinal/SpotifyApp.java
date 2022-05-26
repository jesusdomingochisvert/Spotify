package com.example.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SpotifyApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader;

        Scene scene;

        fxmlLoader = new FXMLLoader(SpotifyApp.class.getResource("InicioSesionView.fxml"));

        //scene = new Scene(fxmlLoader.load(), 1300, 800);
        scene = new Scene(fxmlLoader.load(), 475, 585);

        stage.setTitle("Inicio Sesion");

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {

        launch();

    }
}
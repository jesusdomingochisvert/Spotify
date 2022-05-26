package com.example.proyectofinal;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SpotifyController implements Initializable {

    //Border Pane
    @FXML
    private BorderPane bp;

    //Botones
    @FXML
    private Button bInicio;
    @FXML
    private Button bBuscar;
    @FXML
    private Button bBiblioteca;
    @FXML
    private Button bCrearLista;
    @FXML
    private Button bCancionesFavoritas;
    @FXML
    private Button bAmigo;
    @FXML
    private Button bRandom;
    @FXML
    private Button bAnterior;
    @FXML
    private Button bPlay;
    @FXML
    private Button bSiguiente;
    @FXML
    private Button bRepeticion;
    @FXML
    private Button bCerrarSesion;
    @FXML
    private Button bPodcast;

    //List View
    @FXML
    private ListView<String> lvSeguidores;
    @FXML
    private ListView<String> lvListas;

    //Check Box
    @FXML
    private CheckBox chbLike;

    //Etiquetas
    @FXML
    private Label lTitulo;
    @FXML
    private Label lArtista;
    @FXML
    private Label lTiempoInicio;
    @FXML
    private Label lTiempoFin;
    @FXML
    private Label lInicio;
    @FXML
    private Label lIniciarSesion;
    @FXML
    public Label lNick;

    //Slider
    @FXML
    private Slider sDuracion;

    //**********PROPIEDADES**********

    private ObservableList<Cancion> olBuscar;
    private ObservableList<Cancion> olLike;
    public ObservableList<PlayList> olPlayList;
    private ObservableList<Podcast> olPodcast;
    public ObservableList<Usuario> olSeguir;
    public ObservableList<Album> olAlbum;
    public ObservableList<Artista> olArtista;
    public ObservableList<Capitulo> olCapitulo;
    public ObservableList<Letra> olLetra;

    private FilteredList<Cancion> flBuscar;
    private FilteredList<Usuario> flSeguir;
    private FilteredList<Podcast> flPodcast;
    private FilteredList<PlayList> flPlayList;
    private FilteredList<Album> flAlbum;
    private FilteredList<Artista> flArtista;
    private FilteredList<Capitulo> flCapitulo;

    private SortedList<Cancion> slBuscar;
    private SortedList<Usuario> slSeguir;
    private SortedList<Podcast> slPodcast;
    private SortedList<PlayList> slPlayList;
    private SortedList<Album> slAlbum;
    private SortedList<Artista> slArtista;
    private SortedList<Capitulo> slCapitulo;

    private TableView<Cancion> tvBuscar;
    private TableView<Cancion> tvLike;
    private TableView<PlayList> tvBiblio;
    private TableView<Usuario> tvSeguir;
    private TableView<Podcast> tvPodcast;
    private TableView<Album> tvAlbum;
    private TableView<Artista> tvArtista;
    private TableView<Capitulo> tvCapitulo;

    private TableColumn<Cancion, String> tcId;
    private TableColumn<Cancion, String> tcTitulo;
    private TableColumn<Cancion, String> tcDuracion;
    private TableColumn<Cancion, String> tcRuta;
    private TableColumn<Cancion, String> tcNReproducciones;
    private TableColumn<Cancion, String> tcIdAlbum;
    private TableColumn<Cancion, String> tcLike;
    private TableColumn<String, String> tcListaRep;
    private TableColumn<Usuario, String> tcIdU;
    private TableColumn<Usuario, String> tcUserName;
    private TableColumn<Usuario, String> tcPassWord;
    private TableColumn<Usuario, String> tcEmail;
    private TableColumn<Usuario, String> tcGenero;
    private TableColumn<Usuario, String> tcFechaNacimiento;
    private TableColumn<Usuario, String> tcPais;
    private TableColumn<Usuario, String> tcCodigoPostal;
    private TableColumn<Usuario, String> tcSeguir;
    private TableColumn<Podcast, String> tcIdP;
    private TableColumn<Podcast, String> tcTituloP;
    private TableColumn<Podcast, String> tcDescripcion;
    private TableColumn<Podcast, String> tcAnyo;
    private TableColumn<PlayList, String> tcIdPL;
    private TableColumn<PlayList, String> tcTituloPL;
    private TableColumn<PlayList, String> tcNCanciones;
    private TableColumn<PlayList, String> tcFCreacion;
    private TableColumn<PlayList, String> tcIdUsuario;
    private TableColumn<Album, String> tcIdAL;
    private TableColumn<Album, String> tcAnyoAL;
    private TableColumn<Album, String> tcTituloAL;
    private TableColumn<Album, String> tcImagen;
    private TableColumn<Album, String> tcPatrocinado;
    private TableColumn<Album, String> tcFechaInicioPatrocinio;
    private TableColumn<Album, String> tcFechaFinPatrocinio;
    private TableColumn<Album, String> tcIdArtista;
    private TableColumn<Artista, String> tcIdAR;
    private TableColumn<Artista, String> tcNombre;
    private TableColumn<Artista, String> tcImagenAR;
    private TableColumn<Capitulo, String> tcIdCP;
    private TableColumn<Capitulo, String> tcTituloCP;
    private TableColumn<Capitulo, String> tcDescripcionCP;
    private TableColumn<Capitulo, String> tcDuracionCP;
    private TableColumn<Capitulo, String> tcFecha;
    private TableColumn<Capitulo, String> tcNReproduccionesCP;
    private TableColumn<Capitulo, String> tcIdPodcast;

    private Stage stage;

    private ISController isc;

    private Cancion c;
    private Usuario u;
    private Podcast p;
    private PlayList pl;
    private Album al;
    private Artista ar;
    private Capitulo cp;
    private Letra l;

    //**********METODOS AUXILIARES**********

    private Alert alertaInformacion() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setTitle("Informacion");

        alert.show();

        return alert;

    }

    private Alert alertaError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);

        alert.setTitle("Error");

        alert.show();

        return alert;

    }

    private void album() {

        Pane pane = new Pane();

        tvAlbum = crearTablaAL();

        TextField tf = crearTextField();

        flAlbum = new FilteredList<>(olAlbum, b -> true);

        slAlbum = new SortedList<>(flAlbum);

        lInicio.setText("Album");

        tf.textProperty().addListener((prop, old, text) -> {

            flAlbum.setPredicate(album -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = album.getTitulo().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        tvAlbum.setItems(olAlbum);

        tvAlbum.setItems(flAlbum);

        slAlbum.comparatorProperty().bind(tvAlbum.comparatorProperty());

        tvAlbum.setItems(flAlbum);

        pane.getChildren().addAll(tf, tvAlbum);

        tvAlbum.getSelectionModel().setCellSelectionEnabled(true);

        ObservableList selectedCells = tvAlbum.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {

            @Override
            public void onChanged(Change change) {

                TablePosition tablePosition = (TablePosition) selectedCells.get(0);

                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());

                String nombreBD;

                al = new Album();

                olAlbum = al.consultas();

                for (int i = 0; i < olAlbum.size(); i++) {

                    if (olAlbum != null) {

                        if (olAlbum.get(i).getIdArtista().equals(val)) {

                            nombreBD = "SELECT * FROM artista WHERE id = " + olAlbum.get(i).getIdArtista();

                            ar = new Artista();

                            ar.setNombreBD(nombreBD);

                            olArtista = ar.consultas();

                            artista();

                            break;

                        }

                    }

                }

            }

        });

        bp.setCenter(pane);

    }

    private void artista() {

        Pane pane = new Pane();

        tvArtista = crearTablaAR();

        TextField tf = crearTextField();

        flArtista = new FilteredList<>(olArtista, b -> true);

        slArtista = new SortedList<>(flArtista);

        lInicio.setText("Artista");

        tf.textProperty().addListener((prop, old, text) -> {

            flArtista.setPredicate(artista -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = artista.getNombre().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        tvArtista.setItems(olArtista);

        tvArtista.setItems(flArtista);

        slArtista.comparatorProperty().bind(tvArtista.comparatorProperty());

        tvArtista.setItems(flArtista);

        pane.getChildren().addAll(tf, tvArtista);

        bp.setCenter(pane);

    }

    private void capitulo() {

        Pane pane = new Pane();

        tvCapitulo = crearTablaCP();

        TextField tf = crearTextField();

        flCapitulo = new FilteredList<>(olCapitulo, b -> true);

        slCapitulo = new SortedList<>(flCapitulo);

        lInicio.setText("Capitulo");

        tf.textProperty().addListener((prop, old, text) -> {

            flCapitulo.setPredicate(capitulo -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = capitulo.getTitulo().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        tvCapitulo.setItems(olCapitulo);

        tvCapitulo.setItems(flCapitulo);

        slCapitulo.comparatorProperty().bind(tvCapitulo.comparatorProperty());

        tvCapitulo.setItems(flCapitulo);

        pane.getChildren().addAll(tf, tvCapitulo);

        bp.setCenter(pane);

    }

    private ObservableList<Letra> leerFichero() throws IOException {

        l = new Letra();

        l.consultas();

        olLetra = FXCollections.observableArrayList();

        String[] array;

        String id;
        String ruta;
        String linea;
        String rutaA = "C:\\Users\\jesug\\OneDrive\\Documentos\\Clase\\1º DAM (2º Curso)\\Prog\\ProyectoFinal\\src\\main\\java\\modelo\\letra.txt";

        try {

            File f = new File(rutaA);

            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);

            for (int i = 0; i < f.length(); i++) {

                linea = br.readLine();

                if (linea != null) {

                    array = linea.split(";");

                    id = array[0];
                    ruta = array[1];

                    l = new Letra(id, ruta);

                    if (!olLetra.contains(l)) {

                        olLetra.add(l);

                    }

                }

            }

            return olLetra;

        } catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }

        return null;

    }

    private void letra() {

        try {
            olLetra = leerFichero();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(olLetra);

    }

    private void seguir() {

        u.getSeguir().setText("Seguir");

        u.getSeguir().setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                for (int i = 0; i < olSeguir.size(); i++) {

                    if (tvSeguir.getItems().get(i).getSeguir().isPressed()) {

                        ObservableList<String> ol = FXCollections.observableArrayList();

                        ol.add(olSeguir.get(i).getUsername());

                        lvSeguidores.setItems(ol);

                        Alert alert = alertaInformacion();

                        alert.setContentText("Se ha añadido el Usuario a Seguidores.");

                    }

                }

            }

        });

    }

    private TableView<Cancion> crearTabla() {

        TableView<Cancion> tv = new TableView<>();

        tcId = new TableColumn<>();
        tcTitulo = new TableColumn<>();
        tcDuracion = new TableColumn<>();
        tcRuta = new TableColumn<>();
        tcNReproducciones = new TableColumn<>();
        tcIdAlbum = new TableColumn<>();
        tcLike = new TableColumn<>();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcId.setText("Id");
        tcTitulo.setText("Titulo");
        tcDuracion.setText("Duracion");
        tcRuta.setText("Ruta");
        tcNReproducciones.setText("Numero de Reproducciones");
        tcIdAlbum.setText("Id Album");
        tcLike.setText("Like");

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tcRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        tcNReproducciones.setCellValueFactory(new PropertyValueFactory<>("nReproducciones"));
        tcIdAlbum.setCellValueFactory(new PropertyValueFactory<>("idAlbum"));
        tcLike.setCellValueFactory(new PropertyValueFactory<>("like"));

        tv.getColumns().addAll(tcId, tcTitulo, tcDuracion, tcRuta, tcNReproducciones, tcIdAlbum, tcLike);

        return tv;

    }

    private TableView<Usuario> crearTablaU() {

        TableView<Usuario> tv = new TableView<>();

        tcIdU = new TableColumn<>();
        tcUserName = new TableColumn<>();
        tcPassWord = new TableColumn<>();
        tcEmail = new TableColumn<>();
        tcGenero = new TableColumn<>();
        tcFechaNacimiento = new TableColumn<>();
        tcPais = new TableColumn<>();
        tcCodigoPostal = new TableColumn<>();
        tcSeguir = new TableColumn<>();

        ObservableList<Usuario> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdU.setText("Id");
        tcUserName.setText("Username");
        tcPassWord.setText("Password");
        tcEmail.setText("Email");
        tcGenero.setText("Genero");
        tcFechaNacimiento.setText("Fecha Nacimiento");
        tcPais.setText("Pais");
        tcCodigoPostal.setText("CP");
        tcSeguir.setText("Seguir");

        tcIdU.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcPassWord.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tcFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fNacimiento"));
        tcPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tcCodigoPostal.setCellValueFactory(new PropertyValueFactory<>("cp"));
        tcSeguir.setCellValueFactory(new PropertyValueFactory<>("seguir"));

        tv.getColumns().addAll(tcIdU, tcUserName, tcPassWord, tcEmail, tcGenero, tcFechaNacimiento, tcPais, tcCodigoPostal, tcSeguir);

        return tv;

    }

    private TableView<Podcast> crearTablaP() {

        TableView<Podcast> tv = new TableView<>();

        tcIdP = new TableColumn<>();
        tcTituloP = new TableColumn<>();
        tcDescripcion = new TableColumn<>();
        tcAnyo = new TableColumn<>();

        ObservableList<Podcast> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdP.setText("Id");
        tcTituloP.setText("Titulo");
        tcDescripcion.setText("Descripcion");
        tcAnyo.setText("Anyo");

        tcIdP.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTituloP.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcAnyo.setCellValueFactory(new PropertyValueFactory<>("anyo"));

        tv.getColumns().addAll(tcIdP, tcTituloP, tcDescripcion, tcAnyo);

        return tv;

    }

    private TableView<PlayList> crearTablaPL() {

        TableView<PlayList> tv = new TableView<>();

        tcIdPL = new TableColumn<>();
        tcTituloPL = new TableColumn<>();
        tcNCanciones = new TableColumn<>();
        tcFCreacion = new TableColumn<>();
        tcIdUsuario = new TableColumn<>();

        ObservableList<PlayList> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdPL.setText("Id");
        tcTituloPL.setText("Titulo");
        tcNCanciones.setText("Numero Canciones");
        tcFCreacion.setText("Fecha Creacion");
        tcIdUsuario.setText("Id Usuario");

        tcIdPL.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTituloPL.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcNCanciones.setCellValueFactory(new PropertyValueFactory<>("nCanciones"));
        tcFCreacion.setCellValueFactory(new PropertyValueFactory<>("fCreacion"));
        tcIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));

        tv.getColumns().addAll(tcIdPL, tcTituloPL, tcNCanciones, tcFCreacion, tcIdUsuario);

        return tv;

    }

    private TableView<Album> crearTablaAL() {

        TableView<Album> tv = new TableView<>();

        tcIdAL = new TableColumn<>();
        tcAnyoAL = new TableColumn<>();
        tcTituloAL = new TableColumn<>();
        tcImagen = new TableColumn<>();
        tcPatrocinado = new TableColumn<>();
        tcFechaInicioPatrocinio = new TableColumn<>();
        tcFechaFinPatrocinio = new TableColumn<>();
        tcIdArtista = new TableColumn<>();

        ObservableList<Usuario> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdAL.setText("Id");
        tcAnyoAL.setText("Anyo");
        tcTituloAL.setText("Titulo");
        tcImagen.setText("Imagen");
        tcPatrocinado.setText("Patrocinado");
        tcFechaInicioPatrocinio.setText("Fecha Inicio Patrocinio");
        tcFechaFinPatrocinio.setText("Fecha Fin Patrocinio");
        tcIdArtista.setText("Artista Id");

        tcIdAL.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcAnyoAL.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        tcTituloAL.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        tcPatrocinado.setCellValueFactory(new PropertyValueFactory<>("patrocinado"));
        tcFechaInicioPatrocinio.setCellValueFactory(new PropertyValueFactory<>("fIPatrocinio"));
        tcFechaFinPatrocinio.setCellValueFactory(new PropertyValueFactory<>("fFPatrocinio"));
        tcIdArtista.setCellValueFactory(new PropertyValueFactory<>("idArtista"));

        tv.getColumns().addAll(tcIdAL, tcAnyoAL, tcTituloAL, tcImagen, tcPatrocinado, tcFechaInicioPatrocinio, tcFechaFinPatrocinio, tcIdArtista);

        return tv;

    }

    private TableView<Artista> crearTablaAR() {

        TableView<Artista> tv = new TableView<>();

        tcIdAR = new TableColumn<>();
        tcNombre = new TableColumn<>();
        tcImagenAR = new TableColumn<>();

        ObservableList<Artista> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdAR.setText("Id");
        tcNombre.setText("Nombre");
        tcImagenAR.setText("Imagen");

        tcIdAR.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcImagenAR.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        tv.getColumns().addAll(tcIdAR, tcNombre, tcImagenAR);

        return tv;

    }

    private TableView<Capitulo> crearTablaCP() {

        TableView<Capitulo> tv = new TableView<>();

        tcIdCP = new TableColumn<>();
        tcTituloCP = new TableColumn<>();
        tcDescripcionCP = new TableColumn<>();
        tcDuracionCP = new TableColumn<>();
        tcFecha = new TableColumn<>();
        tcNReproduccionesCP = new TableColumn<>();
        tcIdPodcast = new TableColumn<>();

        ObservableList<Capitulo> ol = FXCollections.observableArrayList();

        tv.setLayoutY(100);
        tv.setLayoutX(0);

        tv.setPrefWidth(740);
        tv.setPrefHeight(500);

        tcIdCP.setText("Id");
        tcTituloCP.setText("Titulo");
        tcDescripcionCP.setText("Descripcion");
        tcDuracionCP.setText("Duracion");
        tcFecha.setText("Fecha");
        tcNReproduccionesCP.setText("Numero Reproducciones");
        tcIdPodcast.setText("Podcast Id");

        tcIdCP.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTituloCP.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcDescripcionCP.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcDuracionCP.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcNReproduccionesCP.setCellValueFactory(new PropertyValueFactory<>("nReproducciones"));
        tcIdPodcast.setCellValueFactory(new PropertyValueFactory<>("idPodcast"));

        tv.getColumns().addAll(tcIdCP, tcTituloCP, tcDescripcionCP, tcDuracionCP, tcFecha, tcNReproduccionesCP, tcIdPodcast);

        return tv;

    }

    private TextField crearTextField() {

        TextField tf = new TextField();

        tf.setPromptText("Buscar...");

        tf.setLayoutY(50);
        tf.setLayoutX(300);

        return tf;

    }

    //**********METODOS DE BOTONES (PARTE IZQUIERDA)**********

    @FXML
    void irVentanaInicio(ActionEvent event) {

        Pane pane = new Pane();

        lInicio.setText("Inicio");

        bp.setCenter(pane);

    }

    @FXML
    void buscar(ActionEvent event) {

        Pane pane = new Pane();

        tvBuscar = crearTabla();

        TextField tf = crearTextField();

        c = new Cancion();

        olBuscar = c.consultas();

        flBuscar = new FilteredList<>(olBuscar, b -> true);

        slBuscar = new SortedList<>(flBuscar);

        lInicio.setText("Buscar");

        tf.textProperty().addListener((prop, old, text) -> {

            flBuscar.setPredicate(cancion -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = cancion.getTitulo().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        letra();

        tvBuscar.setItems(olBuscar);

        tvBuscar.setItems(flBuscar);

        slBuscar.comparatorProperty().bind(tvBuscar.comparatorProperty());

        tvBuscar.setItems(flBuscar);

        pane.getChildren().addAll(tf, tvBuscar);

        tvBuscar.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                String titulo = tvBuscar.getSelectionModel().getSelectedItem().getTitulo();

                lTitulo.setText(titulo);

            }

        });

        tvBuscar.getSelectionModel().setCellSelectionEnabled(true);

        ObservableList selectedCells = tvBuscar.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {

            @Override
            public void onChanged(Change change) {

                TablePosition tablePosition = (TablePosition) selectedCells.get(0);

                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());

                String nombreBD;

                c = new Cancion();

                olBuscar = c.consultas();

                for (int i = 0; i < olBuscar.size(); i++) {

                    if (olBuscar != null) {

                        if (olBuscar.get(i).getIdAlbum().equals(val)) {

                            nombreBD = "SELECT * FROM album WHERE id = " + olBuscar.get(i).getIdAlbum();

                            al = new Album();

                            al.setNombreBD(nombreBD);

                            olAlbum = al.consultas();

                            album();

                            break;

                        }

                    }

                }

            }

        });

        bp.setCenter(pane);

    }

    @FXML
    void irVentanaBiblioteca(ActionEvent event) {

        Pane pane = new Pane();

        tvBiblio = crearTablaPL();

        TextField tf = crearTextField();

        pl = new PlayList();

        olPlayList = pl.consultas();

        try {

            flPlayList = new FilteredList<>(olPlayList, b -> true);

            slPlayList = new SortedList<>(flPlayList);

            lInicio.setText("Biblioteca");

            tf.textProperty().addListener((prop, old, text) -> {

                flPlayList.setPredicate(playlist -> {

                    if (text == null || text.isEmpty()) {

                        return true;

                    }

                    String lowerCase = playlist.getTitulo().toLowerCase();

                    return lowerCase.contains(text.toLowerCase());

                });

            });

            tvBiblio.setItems(olPlayList);

            tvBiblio.setItems(flPlayList);

            slPlayList.comparatorProperty().bind(tvBiblio.comparatorProperty());

            tvBiblio.setItems(flPlayList);

            pane.getChildren().addAll(tf, tvBiblio);

            bp.setCenter(pane);

        } catch (NullPointerException npe) {

            System.out.println(npe.getMessage());

        }

    }

    @FXML
    void crearLista(ActionEvent event) {

        lInicio.setText("Crear Listas");

        TextField tfNombreLista = crearTextField();

        olPlayList = FXCollections.observableArrayList();

        Pane pane = new Pane();

        Button bAñadir = new Button();

        bAñadir.setText("Guardar");

        pane.getChildren().addAll(tfNombreLista, bAñadir);

        bAñadir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (!olPlayList.contains(tfNombreLista.getText())) {

                    //olPlayList.add(tfNombreLista.getText());

                    //lvListas.setItems(olPlayList);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setHeaderText(null);

                    alert.setTitle("Informacion");

                    alert.setContentText("Se ha añadido la lista de reproduccion.");

                    alert.show();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText(null);

                    alert.setTitle("Error");

                    alert.setContentText("No se ha añadido la lista de reproduccion.");

                    alert.show();

                }

            }

        });

        lvListas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lvListas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                TableView tv = crearTabla();

                Pane pane = new Pane();

                tvBuscar.setItems(olBuscar);

                pane.getChildren().addAll(tv);

                bp.setCenter(pane);

            }

        });

        bp.setCenter(pane);

    }

    @FXML
    void irListaCancionesFavoritas(ActionEvent event) {

        tvLike = crearTabla();

        Pane pane = new Pane();

        tvLike.setItems(olLike);

        pane.getChildren().addAll(tvLike);

        bp.setCenter(pane);

    }

    //**********METODOS DE BOTONES (PARTE INFERIOR)**********

    @FXML
    void like(ActionEvent event) {

        tvLike = crearTabla();

        olLike = FXCollections.observableArrayList();

        int check;

        check = chbLike.isSelected() ? 1 : 0;

        Cancion c = tvBuscar.getSelectionModel().getSelectedItem();

        try {

            if (olLike != null) {

                if (check == 1) {

                    olLike.add(c);

                    tvLike.setItems(olLike);

                    for (int i = 0; i < olLike.size(); i++) {

                        tvLike.getItems().get(i).getLike().setSelected(true);

                    }

                    Alert alert = alertaInformacion();

                    alert.setContentText("Se ha añadido la cancion a Like.");

                } else {

                    olLike.remove(tvBuscar.getSelectionModel().getSelectedItem());

                    Alert alert = alertaError();

                    alert.setContentText("La cancion se ha eliminado de Like.");

                }

            }

        } catch (NullPointerException npe) {

            Alert alert = alertaError();

            alert.setContentText(npe.getMessage());

        }

    }

    @FXML
    void random(ActionEvent event) {

        Random random = new Random();

        Cancion c = olBuscar.get(random.nextInt(4));

        String titulo = c.getTitulo();

        lTitulo.setText(titulo);

    }

    @FXML
    void anterior(ActionEvent event) {

        Stack<Cancion> pila = new Stack<>();

        for (int i = 0; i < olBuscar.size(); i++) {

            pila.push(olBuscar.get(i));

        }

        String titulo = pila.get(tvBuscar.getSelectionModel().getSelectedIndex() - 1).getTitulo();

        lTitulo.setText(titulo);

    }

    @FXML
    void play(ActionEvent event) {

        sDuracion.increment();

        Free f = new Free();

        Premium pm = new Premium();

        if (!u.equals(pm)) {

            c = new Cancion();

            olBuscar.add(c);

            if (olBuscar.contains(c)) {

                if (sDuracion.getValue() == 20) {

                    Alert alert = alertaInformacion();

                    alert.setContentText("Ha saltado un anuncio.");

                }

            }

        }

    }

    @FXML
    void repetir(ActionEvent event) {

        for (int i = 0; i < sDuracion.getValue(); i++) {

            sDuracion.decrement();

        }

    }

    @FXML
    void siguiente(ActionEvent event) {

        Stack<Cancion> pila = new Stack<>();

        for (int i = 0; i < olBuscar.size(); i++) {

            pila.push(olBuscar.get(i));

        }

        String titulo = pila.get(tvBuscar.getSelectionModel().getSelectedIndex() + 1).getTitulo();

        lTitulo.setText(titulo);

    }

    //**********METODOS DE BOTONES (PARTE DERECHA)**********

    @FXML
    void agregarAmigo(ActionEvent event) {

        Pane pane = new Pane();

        tvSeguir = crearTablaU();

        TextField tf = crearTextField();

        u = new Usuario();

        olSeguir = u.consultas();

        flSeguir = new FilteredList<>(olSeguir, b -> true);

        slSeguir = new SortedList<>(flSeguir);

        lInicio.setText("Agregar Amigo");

        tf.textProperty().addListener((prop, old, text) -> {

            flSeguir.setPredicate(usuario -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = usuario.getUsername().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        tvSeguir.setItems(olSeguir);

        tvSeguir.setItems(flSeguir);

        slSeguir.comparatorProperty().bind(tvSeguir.comparatorProperty());

        tvSeguir.setItems(flSeguir);

        pane.getChildren().addAll(tf, tvSeguir);

        bp.setCenter(pane);

    }

    @FXML
    void cerrarSesion(ActionEvent event) {



    }

    @FXML
    void podcast(ActionEvent event) {

        Pane pane = new Pane();

        tvPodcast = crearTablaP();

        TextField tf = crearTextField();

        p = new Podcast();

        olPodcast = p.consultas();

        flPodcast = new FilteredList<>(olPodcast, b -> true);

        slPodcast = new SortedList<>(flPodcast);

        lInicio.setText("Podcast");

        tf.textProperty().addListener((prop, old, text) -> {

            flPodcast.setPredicate(podcast -> {

                if (text == null || text.isEmpty()) {

                    return true;

                }

                String lowerCase = podcast.getTitulo().toLowerCase();

                return lowerCase.contains(text.toLowerCase());

            });

        });

        tvPodcast.setItems(olPodcast);

        tvPodcast.setItems(flPodcast);

        slPodcast.comparatorProperty().bind(tvPodcast.comparatorProperty());

        tvPodcast.setItems(flPodcast);

        pane.getChildren().addAll(tf, tvPodcast);

        tvPodcast.getSelectionModel().setCellSelectionEnabled(true);

        ObservableList selectedCells = tvPodcast.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {

            @Override
            public void onChanged(Change change) {

                TablePosition tablePosition = (TablePosition) selectedCells.get(0);

                Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());

                String nombreBD;

                p = new Podcast();

                olPodcast = p.consultas();

                for (int i = 0; i < olPodcast.size(); i++) {

                    if (olPodcast != null) {

                        if (olPodcast.get(i).getId().equals(val)) {

                            nombreBD = "SELECT * FROM capitulo WHERE id = " + olPodcast.get(i).getId();

                            cp = new Capitulo();

                            cp.setNombreBD(nombreBD);

                            olCapitulo = cp.consultas();

                            capitulo();

                            break;

                        }

                    }

                }

            }

        });

        bp.setCenter(pane);

    }

    //**********METODO DE INICIO**********

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bPlay.setBackground(new Background(new BackgroundFill(Color.CORAL, new CornerRadii(10), new Insets(3))));

    }

    public void init(String s, Stage stage, ISController isc) {

        lNick.setText(s);

        this.isc = isc;

        this.stage = stage;

    }

}

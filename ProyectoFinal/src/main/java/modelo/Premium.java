package modelo;

public class Premium extends Usuario {

    boolean anuncios;
    boolean descargas;

    public Premium() {



    }

    public Premium(String id, String username, String password, String email, String genero, String fNacimiento, String pais, String cp,
                String user, String pass) {

        super(id, username, password, email, genero, fNacimiento, pais, cp);

        this.anuncios = false;
        this.descargas = true;

    }

    public boolean isAnuncios() {

        return anuncios;

    }

    public void setAnuncios(boolean anuncios) {

        this.anuncios = anuncios;

    }

    public boolean isDescargas() {

        return descargas;

    }

    public void setDescargas(boolean descargas) {

        this.descargas = descargas;

    }

}

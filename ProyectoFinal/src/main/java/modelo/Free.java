package modelo;

public class Free extends Usuario {

    boolean anuncios;
    boolean descargas;

    public Free() {



    }

    public Free(String id, String username, String password, String email, String genero, String fNacimiento, String pais, String cp,
                String user, String pass) {

        super(id, username, password, email, genero, fNacimiento, pais, cp);

        this.anuncios = true;
        this.descargas = false;

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

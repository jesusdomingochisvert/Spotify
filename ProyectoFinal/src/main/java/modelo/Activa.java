package modelo;

public class Activa extends PlayList {

    private boolean es_compartida;

    public Activa(String id, String titulo, String nCanciones, String fCreacion, String idUsuario, boolean es_compartida) {

        super(id, titulo, nCanciones, fCreacion, idUsuario);

        this.es_compartida = es_compartida;

    }

    public boolean isEs_compartida() {
        return es_compartida;
    }

    public void setEs_compartida(boolean es_compartida) {
        this.es_compartida = es_compartida;
    }
}

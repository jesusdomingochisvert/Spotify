package modelo;

public class Patrocinada extends PlayList {

    private String fInicio;
    private String fFin;

    private boolean patrocinada;

    public Patrocinada(String id, String titulo, String nCanciones, String fCreacion, String idUsuario, String fInicio, String fFin, boolean patrocinada) {

        super(id, titulo, nCanciones, fCreacion, idUsuario);

        this.fInicio = fInicio;
        this.fFin = fFin;

        this.patrocinada = patrocinada;

    }

    public String getfInicio() {
        return fInicio;
    }

    public void setfInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getfFin() {
        return fFin;
    }

    public void setfFin(String fFin) {
        this.fFin = fFin;
    }

    public boolean isPatrocinada() {
        return patrocinada;
    }

    public void setPatrocinada(boolean patrocinada) {
        this.patrocinada = patrocinada;
    }
}

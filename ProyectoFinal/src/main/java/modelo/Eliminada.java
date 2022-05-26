package modelo;

public class Eliminada extends PlayList {

    private String fecha_eliminacion;

    public Eliminada(String id, String titulo, String nCanciones, String fCreacion, String idUsuario, String fecha_eliminacion) {

        super(id, titulo, nCanciones, fCreacion, idUsuario);

        this.fecha_eliminacion =fecha_eliminacion;

    }

    public String getFecha_eliminacion() {
        return fecha_eliminacion;
    }

    public void setFecha_eliminacion(String fecha_eliminacion) {
        this.fecha_eliminacion = fecha_eliminacion;
    }
}

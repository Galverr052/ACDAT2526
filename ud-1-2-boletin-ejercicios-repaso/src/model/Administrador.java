package model;
import java.io.Serializable;
import java.util.Date;
public class Administrador extends Usuario implements Serializable {
    private int nivel;
    public Administrador(String username, String password, String descripcion,
                         Date fechaAlta, Date fechaBaja, int nivel) {
        super(username, password, descripcion, fechaAlta, fechaBaja);
        this.nivel = nivel;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
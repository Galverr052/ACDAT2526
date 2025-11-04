package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Usuario implements Serializable {
    private String username, password, descripcion;
    private Date fechaAlta, fechaBaja;
    private List<Conexion> conexiones;
    public Usuario(String username, String password, String descripcion, Date
            fechaAlta, Date fechaBaja) {
        this.username = username;
        this.password = password;
        this.descripcion = descripcion;
        this.conexiones = new ArrayList<Conexion>();
    }
    public Conexion[] getConexiones() {
        Conexion c[] = new Conexion[conexiones.size()];
        return this.conexiones.toArray(c);
    }
    public void newConexion(Conexion conexion) {
        this.conexiones.add(conexion);
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(Date fechaAlta) {

        this.fechaAlta = fechaAlta;
    }
    public Date getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
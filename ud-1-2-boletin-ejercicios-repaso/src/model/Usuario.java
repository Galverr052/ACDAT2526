package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario implements Serializable {

    private String username;
    private String password;
    private String descripcion;
    private Date fechaAlta;
    private Date fechaBaja;

    private List<Conexion> conexiones;

    public Usuario(String username, String password, String descripcion,
                   Date fechaAlta, Date fechaBaja) {

        this.username = username;
        this.password = password;
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;

        this.conexiones = new ArrayList<>();
    }

    public void newConexion(Conexion c) { conexiones.add(c); }

    public Conexion[] getConexiones() {
        return conexiones.toArray(new Conexion[0]);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaAlta() { return fechaAlta; }
    public Date getFechaBaja() { return fechaBaja; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFechaAlta(Date fechaAlta) { this.fechaAlta = fechaAlta; }
    public void setFechaBaja(Date fechaBaja) { this.fechaBaja = fechaBaja; }
}

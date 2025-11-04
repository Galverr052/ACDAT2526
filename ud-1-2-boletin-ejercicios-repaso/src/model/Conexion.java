package model;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Conexion implements Serializable {
    private String IP;
    private Date fecha;
    private Time hinicio, hfin;
    private List<Conexion> conexiones;
    public Conexion(String IP, Date fecha, Time hinicio, Time hfin) {
        this.IP = IP;
        this.fecha = fecha;
        this.hinicio = hinicio;
        this.hfin = hfin;
        this.conexiones = new ArrayList<Conexion>();
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Time getHinicio() {
        return hinicio;
    }
    public void setHinicio(Time hinicio) {
        this.hinicio = hinicio;
    }
    public Time getHfin() {
        return hfin;
    }
    public void setHfin(Time hfin) {
        this.hfin = hfin;
    }
}
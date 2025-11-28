package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Conexion implements Serializable {

    private String IP;
    private Date fecha;
    private Time hinicio;
    private Time hfin;

    public Conexion(String IP, Date fecha, Time hinicio, Time hfin) {
        this.IP = IP;
        this.fecha = fecha;
        this.hinicio = hinicio;
        this.hfin = hfin;
    }

    public String getIP() { return IP; }
    public Date getFecha() { return fecha; }
    public Time getHinicio() { return hinicio; }
    public Time getHfin() { return hfin; }

    public void setIP(String IP) { this.IP = IP; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setHinicio(Time hinicio) { this.hinicio = hinicio; }
    public void setHfin(Time hfin) { this.hfin = hfin; }
}

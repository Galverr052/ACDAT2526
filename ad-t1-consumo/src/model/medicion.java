package model;

import java.io.Serializable;
import java.util.Date;

public class medicion implements Serializable {
    private Date fecha;
    private int hora;
    private float kwh;

    public medicion(Date fecha, int hora, float kwh) {
        this.fecha = fecha;
        this.hora = hora;
        this.kwh = kwh;
    }
    public Date getFecha() {
        return fecha;
    }
    public int getHora(){
        return hora;
    }
    public float getKwh(){
        return kwh;
    }
}

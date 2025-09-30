package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class contador implements Serializable {
    private String cups;
    private float potenciaContratada;
    private String direccionSuministro;
    private float tarifaKWh;
    private float descuento;
    private Date fechaAlta;
    private List<medicion> mediciones;

    public contador(String cups, float potenciaContratada, String direccionSuministro, float tarifaKWh, float descuento, Date fechaAlta){
        this.cups = cups;
        this.potenciaContratada = potenciaContratada;
        this.direccionSuministro = direccionSuministro;
        this.tarifaKWh = tarifaKWh;
        this.descuento = descuento;
        this.fechaAlta = fechaAlta;
        this.mediciones = new ArrayList<>();
    }

    public float getConsumo(int mes){
        float total = 0;
        for (medicion m: mediciones){
            if (m.getFecha().getMonth()==mes){
                total += m.getKwh();
            }
        }
        return total;
    }
    public void addmedicion (medicion m){
        mediciones.add(m);
    }
    public String getCups() { return cups; }
    public float getPotenciaContratada() {return potenciaContratada; }
    public String getDireccionSuministro() { return direccionSuministro; }
    public float getTarifaKWh(){ return tarifaKWh; }
    public float getDescuento() { return descuento; }
    public Date getFechaAlta() { return fechaAlta; }
}

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estación implements Serializable {
    private int codigo;
    private String descripción;
    private float latitud;
    private float longitud;

    private String direccción;
    private int cp;
    private String población;
    private String provincia;
    private List<TramoHora> tramos;

    public Estación(int codigo, String descripción, String provincia) {
        this.codigo = codigo;
        this.descripción = descripción;
        this.provincia = provincia;
        this.tramos = new ArrayList<>();
    }

    //Getters
    public int getCodigo() {
        return codigo;
    }
    public String getDescripción() {
        return descripción;
    }
    public float getLatitud() {
        return latitud;
    }
    public float getLongitud() {
        return longitud;
    }
    public String getDireccción() {
        return direccción;
    }
    public int getCp() {
        return cp;
    }
    public String getPoblación() {
        return población;
    }
    public String getProvincia() {
        return provincia;
    }
    public List<TramoHora> getTramos() {
        return tramos;
    }

    //setters
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
    public void setCp(int cp) {
        this.cp = cp;
    }
    public void setPoblación(String población) {
        this.población = población;
    }

    public void setDireccción(String direccción){
        this.direccción = direccción;
    }

    public void addTramoHora(TramoHora t) {
        this.tramos.add(t);
    }
    @Override
    public String toString() {
        return "Estacion{" + "codigo=" + codigo + ", descripcion='" + descripción + '\'' + ", provincia='" + provincia + '\'' + ", poblacion='" + población + '\'' + ", tramos=" + tramos.size() + '}';
    }
}
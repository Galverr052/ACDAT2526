package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estacion implements Serializable {
    private int codigo;
    private String descripcion;
    private float latitud;
    private float longitud;

    private String direccion;
    private int cp;
    private String poblacion;
    private String provincia;
    private List<TramoHora> tramos;

    public Estacion(int codigo, String descripción, String provincia) {
        this.codigo = codigo;
        this.descripcion = descripción;
        this.provincia = provincia;
        this.tramos = new ArrayList<>();
    }

    //Getters
    public int getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public float getLatitud() {
        return latitud;
    }
    public float getLongitud() {
        return longitud;
    }
    public String getDireccion() {
        return direccion;
    }
    public int getCp() {
        return cp;
    }
    public String getPoblacion() {
        return poblacion;
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
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public void addTramoHora(TramoHora t) {
        this.tramos.add(t);
    }
    @Override
    public String toString() {
        return "Estacion{" + "codigo=" + codigo + ", descripcion='" + descripcion + '\'' + ", provincia='" + provincia + '\'' + ", poblacion='" + poblacion + '\'' + ", tramos=" + tramos.size() + '}';
    }
}
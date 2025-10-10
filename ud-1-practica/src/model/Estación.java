package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estación implements Serializable {
    private int codigo;
    private String descripción;
    private float latitud;
    private float longitud;
    private int cp;
    private String población;
    private String provincia;
    private List<TramoHora> tramoHoras;

    public Estación(int codigo, String descripción, float latitud, float longitud, int cp, String población, String provincia) {
        this.codigo = codigo;
        this.descripción = descripción;
        this.latitud = latitud;
        this.longitud = longitud;
        this.cp = cp;
        this.población = población;
        this.provincia = provincia;
        this.tramoHoras = new ArrayList<>();
    }

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

    public int getCp() {
        return cp;
    }

    public String getPoblación() {
        return población;
    }

    public String getProvincia() {
        return provincia;
    }

    public List<TramoHora> getTramoHoras() {
        return tramoHoras;
    }
}

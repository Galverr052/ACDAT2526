package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente implements Serializable {
    private String nif;
    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private List<contador> contadores;

    public Cliente(String nif, String nombre, String apellidos, Date fechaAlta) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contadores = new ArrayList<>();
    }
    public float getFactura(contador contador, int anio, int mes){
        return contador.getConsumo(mes) * contador.getTarifaKWh();
    }
    public contador[] getContadores() {
        return (contador[]) contadores.toArray();
    }
    public void addContador(contador contador) {
        contadores.add(contador);
    }
    public void delContador(contador contador){
        contadores.remove(contador);
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }
}

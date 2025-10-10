package model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalTime;

public class TramoHora implements Serializable {
    private int viajerosSubidos;
    private int viajerosBajados;
    private LocalTime inicio;
    private LocalTime fin;

    public TramoHora(int viajerosSubidos, int viajerosBajados, LocalTime inicio, LocalTime fin) {
        this.viajerosSubidos = viajerosSubidos;
        this.viajerosBajados = viajerosBajados;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getViajerosSubidos() {
        return viajerosSubidos;
    }

    public int getViajerosBajados() {
        return viajerosBajados;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFin() {
        return fin;
    }
}

package iesmm.ad.t1_03.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private boolean evoluciona;

    public Pokemon(String nombre, int vida, int ataque, int defensa, boolean evoluciona) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.evoluciona = evoluciona;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public int danio(int puntos) {
        vida -= puntos;
        return vida;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public void atacar(Pokemon objetivo, int puntos) {
        objetivo.danio(puntos);
        System.out.println("DAÑO CONSEGUIDO A " + objetivo.nombre + ": " + puntos + " puntos");
    }

    @Override
    public String toString() {
        return this.nombre + "\t§   " + this.vida + " puntos";
    }
}
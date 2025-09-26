package iesmm.ad.t1_03;

import iesmm.ad.t1_03.model.Pokemon;

import java.io.*;
import java.util.Random;

public class PokemonDuelo {
    public static void main(String[] args) {
        Pokemon pok1 = null, pok2 = null;

        try {
            // Lectura del fichero de dos pokemons
            ObjectInputStream finput = new ObjectInputStream(new FileInputStream("res" + File.separator + "pokemon.dat"));
            pok1 = (Pokemon) finput.readObject();
            pok2 = (Pokemon) finput.readObject();
            finput.close();

            // Generar batalla entre pokemons
            if (pok1 != null && pok2 != null)
                batalla(pok1, pok2);

            // Fin del programa
            System.out.println("FIN DEL JUEGO");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void batalla (Pokemon pok1, Pokemon pok2) {
        while (pok1.getVida() > 0 && pok2.getVida() > 0) {
            System.out.println("DUELO ENTRE:");
            System.out.println(pok1 + "\n" + pok2);
            pausa();

            System.out.println("TURNO DE ATAQUE PARA " + pok1.getNombre());
            pok1.atacar(pok2, new Random().nextInt(pok1.getAtaque()));
            pausa();

            if (pok2.getVida() >= 0) {
                System.out.println("DUELO ENTRE:");
                System.out.println(pok1 + "\n" + pok2);
                pausa();

                System.out.println("TURNO DE ATAQUE PARA " + pok2.getNombre());
                pok2.atacar(pok1, new Random().nextInt(pok2.getAtaque()));
                pausa();

                if (pok1.getVida() <= 0)
                    System.out.println(pok1.getNombre() + " HA SIDO DERROTADO.\n");
            } else
                System.out.println(pok2.getNombre() + " HA SIDO DERROTADO.\n");
        }
    }

    private static void pausa() {
        try {
            System.out.print("\n\t\tPRESIONAR ENTER PARA ATACAR\n\n");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void almacenarResultado (Pokemon ganador, Pokemon perdedor) {
        //abrir flujo de escritura para caracteres.
        try {
            FileWriter fout = new FileWriter("res" + File.separator + "partidas.txt", true);
            //almacenar registros
            fout.write("cadena_formato" + ganador + "vence a" + perdedor + "con una vida" + ganador.getVida());
            //cerrar flujo
            fout.close();

        }catch (IOException e) {
            System.err.println("error al registrar fichero");
        }
    }

}

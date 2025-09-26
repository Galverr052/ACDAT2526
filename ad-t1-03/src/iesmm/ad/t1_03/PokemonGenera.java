package iesmm.ad.t1_03;

import iesmm.ad.t1_03.model.Pokemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PokemonGenera {
	public static void main(String[] args) {
		try {
			ObjectOutputStream foutput = new ObjectOutputStream(new FileOutputStream("res" + File.separator + "pokemon.dat"));
			foutput.writeObject(new Pokemon("BULBASAUR", 15, 8, 10, true));
			foutput.writeObject(new Pokemon("PIKACHU", 15, 8, 15, true));
			foutput.close();
		} catch (IOException e) {
			System.err.println("Fallo al guardar objeto serializable");
		}
	}
}
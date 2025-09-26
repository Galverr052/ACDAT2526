package iesmm.ad.t1_03;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeePersonas {

	public static void main(String[] args) {

		try {
			File f = new File("res" + File.separator + "personas.dat");

			if (f.exists()) {
				DataInputStream fichero = new DataInputStream(new FileInputStream(f));

				// RECORRIDO DEL FICHERO
				// Es importante identificar: ORDEN y FORMATO (tipo)
				// Lectura por conjuntos de bytes relacionando tipo
				while (fichero.available() != 0) {
					System.out.print("Nombre y apellidos: " + fichero.readUTF());
					System.out.println(" - Edad: " + fichero.readInt());
				}

				// Cierre del fichero
				fichero.close();
			}
			else
				System.out.println("El fichero " + f.getName() + " no existe");
		} catch (IOException e) {
			System.err.println("Error al acceder al fichero");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}

package iesmm.ad.t1_03;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class EscribePersonas {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			File f = new File("res" + File.separator + "personas.dat");

			if (!f.exists()) {
				char opcion;
				String nombre;
				int edad;

				// Asociar flujo de salida de datos binarios a descriptor
				DataOutputStream fichero = new DataOutputStream(new FileOutputStream(f));

				do {
					// LECTURA DE DATOS DEL USUARIO
					System.out.println("Introduce nombre y apellidos: ");
					nombre = sc.nextLine();
					System.out.println("Introduce edad: ");
					edad = Integer.parseInt(sc.nextLine());

					// ESCRITURA DE DATOS EN EL FICHERO
					// Es importante identificar el orden y formato (tipo)
					fichero.writeUTF(nombre);
					fichero.writeInt(edad);

					// Recuerda limpiar buffer de entrada de Scanner

					// CONTROL DE NUEVA INSERCIÓN
					System.out.println("Preguntar otro (S/N)?: ");
					// Conversión a mayúsculas y solo primer carácter
					opcion = sc.nextLine().toUpperCase().charAt(0);
				} while (opcion == 'S');

				// Cierre del fichero
				fichero.close();
				System.out.println("Fichero generado en: " + f.getAbsolutePath());
			} else
				System.out.println("El fichero " + f.getName()
						+ " no se puede sobreescribir");
		} catch (IOException e) {
			System.err.println("Error al generar al fichero");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
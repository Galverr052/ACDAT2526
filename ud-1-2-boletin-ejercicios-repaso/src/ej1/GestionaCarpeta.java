package ej1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class GestionaCarpeta {
    public static File makeGlobalFile(String path) {
        File salida = null; // Fichero resultante
        File directorio = new File(path); // Directorio
        try {
// Comprobar existencia del directorio
            if (directorio.exists())
// Determinamos si es un fichero o directorio
                if (directorio.isDirectory()) {
                    File[] lista = directorio.listFiles();
// Contabilizamos si existe, al menos, un fichero XML
// contenido en el directorio de entrada
                    boolean encontrado = false;
                    int i = 0;
                    while (i < lista.length && !encontrado) {
                        if (lista[i].getName().lastIndexOf(".xml") > 0)
                            encontrado = true;
                        i++;
                    }
// Si existe al menos un fichero XML
                    if (i > 0) {
// Generación del archivo de salida
                        salida = new File(directorio.getAbsolutePath()
                                + File.separator + directorio.getName()
                                + ".txt");
                        FileWriter fsalida = new FileWriter(salida);
// Visualizamos que ficheros son los encontrados desde

// ese origen

                        String line; // Línea auxiliar de entrada
                        for (File fichero : lista) {
                            if (fichero.getName().lastIndexOf(".xml") > 0) {
// Nombre del fichero XML del directorio
                                fsalida.write(fichero.getName() + "\n");
// Apertura del canal del fichero XML
                                BufferedReader f1 = new BufferedReader(
                                        new FileReader(fichero));
// Lectura del fichero XML y escritura
                                while ((line = f1.readLine()) != null)
                                    fsalida.write(line + "\n");

// Cierre del fichero .XML
                                f1.close();
                            }
                        }
// Cierre del fichero resultante
                        fsalida.close();
                    }
                }
        } catch (IOException e) {
            System.out.println("Error al generar al fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }
    public static void main(String[] args) {
        File f = GestionaCarpeta.makeGlobalFile("res" + File.separator
                + "misficheros");
        if (f != null)
            System.out.println("Fichero generado en: " + f.getAbsolutePath());
    }
}
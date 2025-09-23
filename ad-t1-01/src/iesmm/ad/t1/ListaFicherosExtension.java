package iesmm.ad.t1;

import java.io.File;
import java.util.Scanner;

public class ListaFicherosExtension {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            //Escribimos el fichero
            System.out.println("Escribir ruta del fichero:");
            String ruta = sc.nextLine();

            //asociamos el flujo
            File dir = new File(ruta);

            //comprobamos si existe el fichero
            if(dir.isDirectory());{
                if (dir.isDirectory())
                    System.out.println("La ruta es un directorio");
                else if (dir.isFile())
                System.out.println("La ruta es un fichero");
            }

        }catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}

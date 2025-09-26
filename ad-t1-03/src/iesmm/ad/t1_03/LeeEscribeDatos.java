package iesmm.ad.t1_03;

import java.io.*;
import java.util.Random;

public class LeeEscribeDatos {

    public static void main(String[] args) {
        // ESCRITURA DE FICHERO BINARIO
        try {
            FileOutputStream f = new FileOutputStream("res" + File.separator + "datos.dat");
            DataOutputStream foutput = new DataOutputStream(f);

            // Genera un número de registros de 1 a 10
            int n = new Random().nextInt(10) + 1;

            // Almacena el número de registros que va a tener fichero
            foutput.writeInt(n);

            for (int i = 0; i < n; i++) {
                foutput.writeBoolean(true);
                foutput.writeChar((i % 2 == 0) ? 'A' : 'B');
                foutput.writeInt(new Random().nextInt(2) + 1);
            }

            foutput.close();
        } catch (IOException e) {
            System.err.println("Error en la escritura del fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // LECTURA DE FICHERO BINARIO
        boolean v;
        char c;
        int i;

        try {
            FileInputStream f = new FileInputStream("res/datos.dat");
            DataInputStream finput = new DataInputStream(f);

            // Lee número de registros
            int n = finput.readInt();

            while (finput.available() != 0) {
                v = finput.readBoolean();
                c = finput.readChar();
                i = finput.readInt();

                System.out.println(v + " " + c + " " + i);
            }

            System.out.println("TOTAL DE REGISTROS: " + n);

            finput.close();
        } catch (IOException e) {
            System.err.println("Error en la lectura del fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

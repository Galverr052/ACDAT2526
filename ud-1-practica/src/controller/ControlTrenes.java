package controller;

import model.Estacion;
import model.TramoHora;

import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ControlTrenes {

    public boolean generarFicheros(File fEstaciones, File fHorarios){
        File salida = new File(fEstaciones.getParent(), "estaciones.dat");

        try (
            BufferedReader brE = new BufferedReader(new FileReader(fEstaciones));
            BufferedReader brH = new BufferedReader(new FileReader(fHorarios));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(salida))
        ) {
            String linea = brE.readLine();
            Map<Integer, Estacion> mapa = new HashMap<>();

            while ((linea = brE.readLine()) !=null){
                String[] partes = linea.split(";");
                if (partes.length < 8) continue;

                int codigo = Integer.parseInt(partes[0]);
                String descripción = partes[1];
                float latitud = Float.parseFloat(partes[2].replace(",", "."));
                float longitud = Float.parseFloat(partes[3].replace(",", "."));
                String dirección = partes[4];
                int cp = Integer.parseInt(partes[5]);
                String población = partes[6];
                String provincia = partes[7];

                Estacion e = new Estacion(codigo, descripción, provincia);
                e.setLatitud(latitud);
                e.setLongitud(longitud);
                e.setDireccion(dirección);
                e.setCp(cp);
                e.setPoblacion(población);

                mapa.put(codigo, e);
            }

            brH.readLine(); // saltar linea 1
            while ((linea = brH.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length < 6) continue;

                int codigo = Integer.parseInt(partes[0]);
                String tramos = partes[3];
                int viajerosSubidos = Integer.parseInt(partes[4]);
                int viajerosBajados = Integer.parseInt(partes[5]);

                String[] horas = tramos.split("-");
                LocalTime inicio = LocalTime.parse(horas[0]);
                LocalTime fin = LocalTime.parse(horas[1]);

                TramoHora t = new TramoHora(viajerosSubidos, viajerosBajados, inicio, fin);

                Estacion e = mapa.get(codigo);
                if (e != null) {
                    e.addTramoHora(t);
                }
            }
            for (Estacion e : mapa.values()){
                oos.writeObject(e);
            }
            System.out.println("fichero generado: " + salida.getAbsolutePath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void ocupacion(LocalTime desde, LocalTime hasta) {
        File f = new File("C:/Users/usuario/Desktop/repositorios GA/ACDAT2526/ud-1-practica/res/estaciones.dat");
        File salida = new File("ocupacion.txt");

        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(salida))
        ) {
            bw.write("OCUPACIÓN DE VIAJEROS ENTRE " + desde + " Y " + hasta);
            bw.newLine();
            bw.newLine();

            while (true) {
                try {
                    Estacion e = (Estacion) ois.readObject();

                    int totalSuben = 0;
                    int totalBajan = 0;

                    for (TramoHora t : e.getTramos()) {
                        if (!t.getFin().isBefore(desde) && !t.getInicio().isAfter(hasta)) {
                            totalSuben += t.getViajerosSubidos();
                            totalBajan += t.getViajerosBajados();
                        }
                    }

                    int ocupacion = totalSuben - totalBajan;

                    bw.write(String.format("%-30s  Suben: %4d  Bajan: %4d  Ocupación: %4d",
                            e.getDescripcion(), totalSuben, totalBajan, ocupacion));
                    bw.newLine();

                } catch (EOFException ex) {
                    break; // fin del fichero
                }
            }

            System.out.println("Fichero de ocupación generado: " + salida.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

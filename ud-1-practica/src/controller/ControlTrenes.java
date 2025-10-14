package controller;

import model.Estación;
import model.TramoHora;

import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ControlTrenes {
    public boolean generarFicheros(File fEstaciones, File fHorarios){
        File salida = new File(fEstaciones.getParent(), "estaciones.bat");

        try (
            BufferedReader brE = new BufferedReader(new FileReader(fEstaciones));
            BufferedReader brH = new BufferedReader(new FileReader(fHorarios));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(salida))
        ) {
            String linea = brE.readLine();
            Map<Integer, Estación> mapa = new HashMap<>();

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

                Estación e = new Estación(codigo, descripción, provincia);
                e.setLatitud(latitud);
                e.setLongitud(longitud);
                e.setDireccción(dirección);
                e.setCp(cp);
                e.setPoblación(población);

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

                Estación e = mapa.get(codigo);
                if (e != null) {
                    e.addTramoHora(t);
                }
            }
            for (Estación e : mapa.values()){
                oos.writeObject(e);
            }
            System.out.println("fichero generado: " + salida.getAbsolutePath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

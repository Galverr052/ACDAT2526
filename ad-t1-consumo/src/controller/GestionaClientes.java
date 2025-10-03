package controller;

import java.io.*;
import java.rmi.ServerError;
import java.util.HashMap;
import java.util.Map;

public class GestionaClientes {
    public File consumoSuministros(File fmediciones){
       File fresult = null;

       if(fmediciones.exists()){
           //ubicar
           fresult= new File(fmediciones.getParent(),fmediciones.getName().replace(".csv",".txt"));

           //letura
           Map<String, Float> consumoPorCups = new HashMap<>();
           String line;

           try{
               BufferedReader br = new BufferedReader(new FileReader(fmediciones));

               while ((line = br.readLine()) != null) {
                   if (!line.startsWith("#")) {
                       String[] data = line.split(";");

                       if (data != null && data.length == 4) {
                           String cups = data[0];
                           float kwh = 0;
                           try {
                               kwh = Float.parseFloat(data[3]);
                           } catch (NumberFormatException e){
                               System.err.println("Error de conversión a float: " + data[3]);
                           }

                           consumoPorCups.put(cups, consumoPorCups.getOrDefault(cups, 0f) + kwh);
                       } else
                           System.err.println("error de conversión");
                   }
               }
               br.close();

               PrintWriter writer = new PrintWriter(new FileWriter(fresult));
               for (Map.Entry<String, Float> entry : consumoPorCups.entrySet()){
                   writer.printf("Referencia CUPS: " + entry.getKey() + " - Consumo total (kwh): %.2f \n", entry.getValue());
               }
               writer.close();
           }catch (IOException e){
               System.err.println("Error de E/S: " + e.getMessage());
           } catch (Exception e) {
               System.err.println(e.getMessage());
           }
        }
       return fresult;
    }


}

package controller;

import model.Administrador;
import model.Conexion;
import model.Usuario;

import java.io.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class ControlConexiones {

    /* ================================================================
        1) direccionesIP(File fconexiones)
       ================================================================ */
    public File direccionesIP(File fconexiones) {

        if (!fconexiones.exists())
            return null;

        File fsalida = new File(
                fconexiones.getParent(),
                fconexiones.getName().replace(".csv", ".txt")
        );

        Map<String, Integer> conteo = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fconexiones))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split("#");
                if (data.length < 2) continue;

                String ip = data[1];
                conteo.put(ip, conteo.getOrDefault(ip, 0) + 1);
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(fsalida))) {
                for (String ip : conteo.keySet()) {
                    out.println(ip + " - " + conteo.get(ip));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fsalida;
    }

    /* ================================================================
        2) generarFichero(File conexiones, File usuarios)
       ================================================================ */
    public boolean generarFichero(File fconexiones, File fusuarios) {

        if (!fconexiones.exists() || !fusuarios.exists())
            return false;

        Map<String, Usuario> mapaUsuarios = new HashMap<>();

        try {
            /* -------------------------------------------------------
               1) Cargar usuarios desde usuarios.csv
               ------------------------------------------------------- */
            BufferedReader brUsers = new BufferedReader(new FileReader(fusuarios));
            String line;

            while ((line = brUsers.readLine()) != null) {

                String[] data = line.split(":");
                if (data.length < 4) continue;

                String username = data[0];
                String password = data[1];
                String descripcion = data[2];

                Date fechaAlta = new SimpleDateFormat("dd/MM/yyyy").parse(data[3]);
                Date fechaBaja = (data.length >= 5 && !data[4].isEmpty())
                        ? new SimpleDateFormat("dd/MM/yyyy").parse(data[4])
                        : null;

                Usuario u;

                if (descripcion.equalsIgnoreCase("administrador")) {
                    int nivel = Integer.parseInt(data[5]);
                    u = new Administrador(username, password, descripcion,
                            fechaAlta, fechaBaja, nivel);
                } else {
                    u = new Usuario(username, password, descripcion,
                            fechaAlta, fechaBaja);
                }

                mapaUsuarios.put(username, u);
            }
            brUsers.close();

            /* -------------------------------------------------------
               2) Leer conexiones.csv y asignarlas
               ------------------------------------------------------- */
            BufferedReader brCon = new BufferedReader(new FileReader(fconexiones));

            while ((line = brCon.readLine()) != null) {

                String[] data = line.split("#");
                if (data.length < 3) continue;

                String username = data[0];

                if (!mapaUsuarios.containsKey(username)) {
                    System.out.println("Usuario no encontrado: " + username);
                    continue;
                }

                String ip = data[1];
                String[] partesFecha = data[2].split("@");

                Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(partesFecha[0]);
                Time hinicio = new Time(new SimpleDateFormat("HH_mm").parse(partesFecha[1]).getTime());
                Time hfin = new Time(new SimpleDateFormat("HH_mm").parse(partesFecha[2]).getTime());

                Conexion c = new Conexion(ip, fecha, hinicio, hfin);
                mapaUsuarios.get(username).newConexion(c);
            }
            brCon.close();

            /* -------------------------------------------------------
               3) Volcar a fichero binario .dat
               ------------------------------------------------------- */
            File fsalida = new File(
                    fconexiones.getParent(),
                    fconexiones.getName().replace(".csv", ".dat")
            );

            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(fsalida));

            for (Usuario u : mapaUsuarios.values())
                oos.writeObject(u);

            oos.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ================================================================
        3) conexionesAdmin(File f, int anio, int mes)
       ================================================================ */
    public Administrador[] conexionesAdmin(File f, int anio, int mes) {

        if (!f.exists())
            return null;

        List<Administrador> resultado = new ArrayList<>();

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(f))) {

            while (true) {
                Usuario u = (Usuario) ois.readObject();

                if (!(u instanceof Administrador)) continue;

                Conexion[] cons = u.getConexiones();

                for (Conexion c : cons) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(c.getFecha());

                    int y = cal.get(Calendar.YEAR);
                    int m = cal.get(Calendar.MONTH) + 1; // ENERO = 0

                    if (y == anio && m == mes) {
                        resultado.add((Administrador) u);
                        break;
                    }
                }
            }

        } catch (EOFException e) {
            // Fin normal del fichero
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return resultado.toArray(new Administrador[0]);
    }
}

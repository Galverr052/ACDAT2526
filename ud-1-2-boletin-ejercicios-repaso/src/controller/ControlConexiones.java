package controller;

import model.Administrador;
import model.Conexion;
import model.Usuario;

import java.io.*;
import java.util.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class ControlConexiones {

    public File direccionesIP(File fconexiones) {
        File fsalida = null;
        if (fconexiones.exists()) {
            fsalida = new File(fconexiones.getParent() + File.separator +
                    fconexiones.getName().replace(".csv", ".txt"));

            Map<String, Integer> conexiones = new HashMap<String, Integer>(); // Mapa con IP:número de conexiones

            try {
                // 1. Recoger datos de conexiones al mapa
                BufferedReader br = new BufferedReader(new FileReader(fconexiones));
                String line;
                while ((line = br.readLine()) != null) {
                    String data[] = line.split("#");
                    if (data.length > 1) {
                        String ip = data[1];
                        conexiones.put(ip, conexiones.getOrDefault(ip, 0) + 1);
                    }
                }
                br.close();

                // 2. Volcar datos del mapa al fichero
                if (!conexiones.isEmpty()) {
                    FileWriter out = new FileWriter(fsalida);
                    for (String ip : conexiones.keySet()) {
                        out.write(ip + " - " + conexiones.get(ip) + "\n");
                    }
                    out.close();
                }

            } catch (IOException e) {
                System.err.println("Error al generar el fichero: " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return fsalida;
    }

    public boolean generarFichero(File fconexiones, File fusuarios) {
        boolean estado = false;
        List<Usuario> usuarios = new ArrayList<>();

        if (fconexiones.exists() && fusuarios.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fconexiones))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String data[] = line.split("#");
                    if (data.length > 0) {
                        String username = data[0];
                        boolean encontrado = false;
                        int i = 0;

                        // Buscar usuario en lista
                        while (i < usuarios.size() && !encontrado) {
                            if (usuarios.get(i).getUsername().equals(username)) {
                                encontrado = true;
                            } else {
                                i++;
                            }
                        }

                        // Si no existe, buscar en fichero de usuarios
                        if (!encontrado) {
                            try (BufferedReader bufusuarios = new BufferedReader(new FileReader(fusuarios))) {
                                String lineUser;
                                while (!encontrado && (lineUser = bufusuarios.readLine()) != null) {
                                    if (lineUser.split(":")[0].equals(username)) {
                                        encontrado = true;
                                        String[] dataUser = lineUser.split(":");
                                        String password = dataUser[1];
                                        String descripcion = dataUser[2];

                                        Usuario usuario = null;
                                        if (descripcion.equals("usuarios")) {
                                            usuario = new Usuario(username, password, descripcion, null, null);
                                        } else if (descripcion.equals("administradores")) {
                                            int nivel = Integer.parseInt(dataUser[5]);
                                            usuario = new Administrador(username, password, descripcion, null, null, nivel);
                                        }

                                        usuarios.add(usuario);
                                    }
                                }
                            }
                        }

                        // Añadir nueva conexión si el usuario existe
                        if (encontrado) {
                            String ip = data[1];
                            Date fecha = new SimpleDateFormat("dd-MM-yyyy").parse(data[2].split("@")[0]);
                            Date hinicio = new SimpleDateFormat("HH_mm").parse(data[2].split("@")[1]);
                            Date hfin = new SimpleDateFormat("HH_mm").parse(data[2].split("@")[2]);

                            usuarios.get(i).newConexion(new Conexion(ip, fecha, new Time(hinicio.getTime()), new Time(hfin.getTime())));
                        } else {
                            System.out.println("El usuario " + username + " no existe");
                        }
                    }
                }

                // Volcar contenido a fichero binario
                File salida = new File(fconexiones.getParent() + File.separator + fconexiones.getName().replace(".csv", ".dat"));
                try (ObjectOutputStream fbinario = new ObjectOutputStream(new FileOutputStream(salida))) {
                    for (Usuario u : usuarios) {
                        fbinario.writeObject(u);
                    }
                }
                estado = true;

            } catch (IOException e) {
                System.err.println("Error al leer el fichero: " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } else {
            System.out.println("Comprobar ficheros de conexiones y usuarios");
        }

        return estado;
    }

    public Administrador[] conexionesAdmin(File f, int anio, int mes) {
        List<Administrador> administradores = new ArrayList<>();

        if (f.exists()) {
            try (ObjectInputStream fusers = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    Usuario user = (Usuario) fusers.readObject();

                    if (user instanceof Administrador) {
                        Conexion[] conexiones = user.getConexiones();
                        boolean encontrado = false;

                        for (Conexion c : conexiones) {
                            int c_anio = Integer.parseInt(new SimpleDateFormat("yyyy").format(c.getFecha()));
                            int c_mes = Integer.parseInt(new SimpleDateFormat("MM").format(c.getFecha()));
                            if (c_anio == anio && c_mes == mes) {
                                encontrado = true;
                                break;
                            }
                        }

                        if (encontrado) {
                            administradores.add((Administrador) user);
                        }
                    }
                }
            } catch (EOFException e) {
                // Fin de fichero
            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        Administrador[] admins = new Administrador[administradores.size()];
        admins = administradores.toArray(admins);
        return admins;
    }
}

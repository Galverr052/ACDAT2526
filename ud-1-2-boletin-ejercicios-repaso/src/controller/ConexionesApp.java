package controller;

import model.Administrador;

import java.io.File;
import java.util.Arrays;

public class ConexionesApp {

    public static void main(String[] args) {
        // Definir rutas de archivos directamente
        File fConexiones = new File("res/misficheros" + File.separator + "conexiones.csv");
        File fUsuarios = new File("res/misficheros" + File.separator + "usuarios.csv");

        ControlConexiones cc = new ControlConexiones();

        // 1. Generar fichero de direcciones IP
        File fIPs = cc.direccionesIP(fConexiones);
        if (fIPs != null && fIPs.exists()) {
            System.out.println("Fichero de direcciones IP generado en: " + fIPs.getAbsolutePath());
        } else {
            System.out.println("No se pudo generar el fichero de direcciones IP");
        }

        // 2. Generar fichero binario de usuarios con conexiones
        boolean generado = cc.generarFichero(fConexiones, fUsuarios);
        System.out.println("Fichero binario generado: " + generado);

        // 3. Mostrar administradores con conexiones de un mes específico
        int anio = 2025;  // ejemplo
        int mes = 11;     // noviembre
        File fBinario = new File("res/misficheros" + File.separator + "conexiones.dat");

        Administrador[] admins = cc.conexionesAdmin(fBinario, anio, mes);
        if (admins != null && admins.length > 0) {
            System.out.println("Administradores con conexiones en " + mes + "/" + anio + ":");
            Arrays.stream(admins).forEach(a -> System.out.println(" - " + a.getUsername()));
        } else {
            System.out.println("No hay administradores con conexiones en ese mes");
        }
    }
}

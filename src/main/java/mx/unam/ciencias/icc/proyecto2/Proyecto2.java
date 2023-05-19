package mx.unam.ciencias.icc.proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.IteradorLista;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;


/**
 * Proyecto 2: Ordenador lexicográfico.
 */
public class Proyecto2 {

    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;

    private static String id;

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar target/proyecto2.jar [-r|-o <identificador>] <archivo>");
        System.exit(ERROR_USO);
    }

    /* Obtiene los archivos a ordenar a partir de los parámetros de la línea de comandos*/
    private static Lista<String> getArchivos(String[] args) {
        Lista<String> archivos = new Lista<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-r") || args[i].equals("-o")) 
                continue;
            else {
                archivos.agregaFinal(args[i]);
            }
        }
        String identificador = getIdentificador(args);
        if (identificador != null){
            archivos.elimina(identificador);
        }
        return archivos;
    }

    /* Obtiene el identificador a partir de los parámetros de la línea de comandos*/
    private static String getIdentificador(String[] args) {
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                String identificador = args[i + 1];
                return identificador;
            } 
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            if (getArchivos(args).toString() == "[]") {
                if (args.length < 1) {
                    Aplicacion aplicacion = new Aplicacion(System.in);
                    aplicacion.ejecuta();
                } else {
                    Aplicacion aplicacion = new Aplicacion(args, System.in);
                    aplicacion.ejecuta();
                }
            } else {
                Aplicacion aplicacion = new Aplicacion(args);
                aplicacion.ejecuta();
            }
        } catch (IllegalArgumentException iae) {
            uso();
        }
    }
}
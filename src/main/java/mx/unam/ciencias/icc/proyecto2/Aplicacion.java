package mx.unam.ciencias.icc.proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.IteradorLista;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;

/**
 * Clase para aplicaciones del ordenador lexicográfico.
 */
public class Aplicacion {

    /* Modo de la aplicación. */
    private enum Modo {
        /* Modo para imprimir las líneas en orden inverso. */
        REVERSE(1),
        /* Modo para guardar. */
        OUT(2),
        /* Modo para imprimir el archivo ordenado. */
        DEFAULT(3),
        /* Modo para imprimir las líneas en orden inverso y guardar*/
        OR(4);

        /* Código de terminación. */
        private int codigo;

        /* Constructor. */
        private Modo(int codigo) {
            this.codigo = codigo;
        }

        /* Regresa el código. */
        public int getCodigo() {
            return codigo;
        }

        /* Regresa el modo de la bandera. */
        public static Modo getModo(String banderas) {
            switch (banderas) {
            case "-r": return REVERSE;
            case "-o": return OUT;
            case "": return DEFAULT;
            case "-o-r": return OR;
            case "-r-o": return OR;
            default: throw new IllegalArgumentException(
                "Banderas inválidas: " + banderas);
            }
        }
    }

    /* Ordenador. */
    private Ordenador ord;
    /* Parámetros de la línea de comandos. */
    private String[] args;
    /* El modo de la aplicación. */
    private Modo modo;
    /* Entrada estándar. */
    private InputStream entrada;
    /* Identificador. */
    private String identificador;
    /* Archivos a ordenar. */
    private Lista<String> archivos;

    /**
     * Define el estado inicial de la aplicación para archivos de la línea de comandos.
     * @param args parámetros de la línea de comandos.
     */
    public Aplicacion(String[] args) {
        this.args = args;
        identificador = getIdentificador(args);
        archivos = getArchivos(args);
        if (identificador != null){
            archivos.elimina(identificador);
        }
        modo = Modo.getModo(getBanderas());
        entrada = null;
        ord = new Ordenador();
    }

    /**
     * Define el estado inicial de la aplicación para la entrada estándar.
     * @param in entrada estándar.
     */
    public Aplicacion(InputStream in) {
        modo = Modo.getModo("");
        entrada = in;
        ord = new Ordenador();
    }

    /**
     * Define el estado inicial de la aplicación para la entrada estándar cuando además se reciben banderas.
     * @param args parámetros de la línea de comandos.
     * @param in entrada estándar.
     */
    public Aplicacion(String[] args, InputStream in) {
        this.args = args;
        identificador = getIdentificador(args);
        archivos = getArchivos(args);
        if (identificador != null){
            archivos.elimina(identificador);
        }
        modo = Modo.getModo(getBanderas());
        entrada = in;
        ord = new Ordenador();
    }

    /* Obtiene las banderas de los parámetros. */
    private String getBanderas(){
        String banderas = "";
        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-o")) {
                    banderas += "-o";
                } else if(args[i].equals("-r"))
                    banderas += "-r";
            }
            return banderas;
        }
        return banderas;
    }

    /* Obtiene el identificador para el modo de guarda. */
    private String getIdentificador(String[] args) {
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                String identificador = args[i + 1];
                return identificador;
            } 
        }
        return null;
    }

    /* Obtiene los archivos de los parámetros. */
    private Lista<String> getArchivos(String[] args) {
        Lista<String> archivos = new Lista<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-r") || args[i].equals("-o")) 
                continue;
            else {
                archivos.agregaFinal(args[i]);
            }
        }
        return archivos;
    }

    /* Ejecuta la aplicación. */
    public void ejecuta() {
        switch (modo) {
        case DEFAULT:
            imprimeOrdenado();
            break; 
        case OUT:
            imprimeOrdenado();
            guarda();
            break;
        case OR:
            imprimeReversa();
            guarda();
            break;
        case REVERSE:
            imprimeReversa();
            break;
        }
    }

    /* Imprime el/los archivo/s ordenado/s. */
    private void imprimeOrdenado() {
        try {
            if (entrada != null) {
                BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(entrada));
                ord.carga(in);
                in.close();
            }
            if (archivos != null) {
                for (String archivo : archivos) {
                        BufferedReader in =
                            new BufferedReader(
                                new InputStreamReader(
                                    new FileInputStream(archivo)));
                        ord.carga(in);
                        in.close();
                }
            } 
                    Lista<String> ordenada = ord.ordena();

                    int r = 0;
                    for (String renglon : ordenada) {
                        System.out.println(ordenada.get(r));
                        r++;
                    }
        } catch (IOException ioe) {
            System.err.printf("No pude cargar el archivo \"%s\".\n");
            System.exit(modo.getCodigo());
        }
    }

    /* Imprime el/los archivo/s en orden inverso. */
    public void imprimeReversa() {
        try {
            if (entrada != null) {
                BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(entrada));
                ord.carga(in);
                in.close();
            }
            if (archivos != null) {
                for (String archivo : archivos) {
                        BufferedReader in =
                            new BufferedReader(
                                new InputStreamReader(
                                    new FileInputStream(archivo)));
                        ord.carga(in);
                        in.close();
                    } 
            }
                    Lista<String> ordenada = ord.ordena();
                    ordenada = ordenada.reversa();
                    ord.setRenglones(ordenada);

                    int r = 0;
                    for (String renglon : ordenada) {
                        System.out.println(ordenada.get(r));
                        r++;
                    }


        } catch (IOException ioe) {
            System.err.printf("No pude cargar el archivo \"%s\".\n");
            System.exit(modo.getCodigo());
        }
    }

    /* Guarda el archivo. */
    private void guarda() {
        try {
            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(identificador)));
            ord.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.err.printf("No pude guardar en el archivo \"%s\".\n");
            System.exit(modo.getCodigo());
        }
    }
}
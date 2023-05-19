package mx.unam.ciencias.icc.proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.Normalizer;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.IteradorLista;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;


/**
 * Clase para ordenador lexicográfico. Provee métodos para cargar los renglones de una entrada dada
 * en una lista, ordenar la lista lexicográficamente, y para guardarse en una salida dada.
 */
public class Ordenador {

    /* Lista de líneas en el archivo de texto. */
    private Lista<String> renglones;

    /**
     * Constructor único.
     */
    public Ordenador() {
        renglones = new Lista<>();
    }


    /**
     * Carga los renglones de la entrada recibida en la lista. 
     * @param in la entrada de donde hay que cargar los renglones.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        String linea = "";
        while ((linea = in.readLine()) != null) {
            
            try {
                if (linea == null){
                    return;
                } else {
                    renglones.agregaFinal(linea);
                } 
            }
            catch (ExcepcionLineaInvalida cnfe) {
                throw new IOException();
            }   

        }
    }

    public void carga(Lista<String> lista) {
        renglones = lista;
    }

    /** Ordena lexicográficamente la lista por líneas. Las líneas
     * vacías se ordenan antes que las no vacías; los espacios son 
     * ignorados al ordenar pero preservados en la lista; los acentos, 
     * diéresis y eñes se toman como vocales y la letra n respectivamente.*/
    public Lista<String> ordena() {
        renglones = renglones.mergeSort(
            (a,b) -> Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("\\s+", "").replaceAll("[^a-zA-Z]", "").replaceAll("\\p{M}", "").compareToIgnoreCase(Normalizer.normalize(b, Normalizer.Form.NFD).replaceAll("\\s+", "").replaceAll("[^a-zA-Z]", "").replaceAll("\\p{M}", "")));
        return renglones;
    }

    /** Define el los renglones a ordenar 
     *@param renglones los renglones del archivo.    
    */
    public void setRenglones(Lista<String> renglones) {
        this.renglones = renglones;
    }

    /** Guarda línea por línea la lista renglones.
     * @param out la salida donde hay que guardar los renglones.   
    */
    public void guarda(BufferedWriter out) throws IOException {
        if(renglones == null)
            return;
        else {
            String lineas = "";
            for (String renglon : renglones) {
                lineas += renglon + "\n";
            }
            out.write(lineas);
            out.close();
        }    
    }
}
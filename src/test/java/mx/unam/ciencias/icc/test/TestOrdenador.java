package mx.unam.ciencias.icc.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.icc.ExcepcionIndiceInvalido;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import mx.unam.ciencias.icc.proyecto2.Ordenador;

/**
 * Clase para pruebas unitarias de la clase {@link Ordenador}.
 */

 public class TestOrdenador {

     /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;
    /* Número total de elementos. */
    private int total;
    /* La lista. */
    private Lista<String> lista;

    private Ordenador ordenador;

    /**
     * Crea un generador de números aleatorios para cada prueba, un número total
     * de elementos para nuestra lista, y una lista.
     */
    public TestOrdenador() {
        random = new Random();
        total = 10 + random.nextInt(90);
        lista = new Lista<String>();
        ordenador = new Ordenador();
    }

    /**
     * Prueba unitaria para {@link Ordenador#ordena()}.
     */
    @Test public void ordena() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt('z' - 'a') + 'a'));
        ordenador.carga(lista);
        Lista<String> ordenada = ordenador.ordena(); 
        Assert.assertFalse(lista == ordenada);
        Assert.assertTrue(lista.getLongitud() == ordenada.getLongitud());
        for (String e : lista)
            Assert.assertTrue(ordenada.contiene(e));
        String a = "";
        for (String e : ordenada) {
            Assert.assertTrue(a.compareTo(e) <= 0);
            Assert.assertTrue(("A").compareToIgnoreCase(e) <= 0);
            // a = e;
        }
        // validaLista(ordenada);
        // /* Prueba estabilidad. */
        // total = 100 + total * 10;
        // int c = 0;
        // int m = 7 + random.nextInt(20);
        // Lista<Par> pares = new Lista<Par>();
        // for (int i = 0; i < total; i++) {
        //     int v = ((i % m) == 0) ? m : random.nextInt(total);
        //     pares.agregaFinal(new Par(v, i));
        // }
        // pares = Lista.mergeSort(pares);
        // Par u = null;
        // for (Par par : pares) {
        //     if (u == null) {
        //         u = par;
        //         continue;
        //     }
        //     Assert.assertTrue(u.getValor() <= par.getValor());
        //     if (u.getValor() == par.getValor())
        //         Assert.assertTrue(u.getEtiqueta() < par.getEtiqueta());
        //     u = par;
        // }
    }
 }
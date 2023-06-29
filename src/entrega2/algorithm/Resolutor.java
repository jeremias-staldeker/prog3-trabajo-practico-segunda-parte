package entrega2.algorithm;

import java.util.ArrayList;
import java.util.List;

import entrega2.model.grafo.Arco;
import entrega2.util.Timer;

/**
 * Clase abstracta que representa un resolutor de problemas.
 */
public abstract class Resolutor {
    protected List<Integer> estaciones = new ArrayList<>();
    protected int metrica;
    protected int kms;
    protected String nombre; // Corresponde al nombre del resolutor, Greedy o Backtracking.

    /**
     * Constructor de la clase Resolutor.
     */
    public Resolutor() {
        this.estaciones = new ArrayList<>();
        this.kms = 0;
        this.metrica = 0;
    }

    /**
     * Método abstracto para buscar la solución.
     *
     * @param candidatos Lista de arcos candidatos.
     * @return Lista de arcos que representan la solución.
     */
    public abstract List<Arco<Integer>> buscarSolucion(List<Arco<Integer>> candidatos);

    /**
     * Obtiene la lista de estaciones.
     *
     * @return Lista de estaciones.
     */
    public List<Integer> getEstaciones() {
        return estaciones;
    }
    
    /**
     * Obtiene la lista de estaciones.
     *
     * @return Lista de estaciones.
     */
    public List<Integer> setEstaciones() {
        return estaciones;
    }

    /**
     * Obtiene la métrica actual.
     *
     * @return Valor de la métrica.
     */
    public int getMetrica() {
        return metrica;
    }

    /**
     * Obtiene los kilómetros totales de la solución.
     *
     * @return Kilómetros totales.
     */
    public int getKms() {
        return kms;
    }

    /**
     * Establece los kilómetros totales de la solución.
     *
     * @param kms Kilómetros totales.
     */
    public void setKms(int kms) {
        this.kms = kms;
    }

    /**
     * Imprime la solución del problema.
     *
     * @param arcos Lista de arcos del problema.
     */
    public void imprimirSolucion(List<Arco<Integer>> arcos) {
        // Inicia el contador de tiempo
        Timer timer = new Timer();
        timer.start();

        // Busca la solucion
        List<Arco<Integer>> solucion = buscarSolucion(arcos);

        // Detiene el contador
        double time = timer.stop();

        // Imprime resultados
        System.out.println("Nombre Algoritmo: " + getNombreResolutor());
        System.out.println("Solucion:");

        List<String> arcosString = new ArrayList<>();

        for (Arco<Integer> arco : solucion) {
            int origen = arco.getVerticeOrigen();
            int destino = arco.getVerticeDestino();
            arcosString.add("E" + origen + "-E" + destino);
        }

        System.out.println(String.join(", ", arcosString));
        System.out.println("Kilometros Totales: " + getKms());
        System.out.println("Metrica: " + getMetrica());
        System.out.println("Tiempo Transcurrido: " + time);
        System.out.println("");
    }

    /**
     * Obtiene el nombre del resolutor.
     *
     * @return Nombre del resolutor.
     */
    public String getNombreResolutor() {
        return nombre;
    }

    /**
     * Incrementa el valor de la métrica en 1.
     */
    public void sumarMetrica() {
        metrica++;
    }

    /**
     * Popula la lista de estaciones a partir de una lista de arcos.
     *
     * @param arcos      Lista de arcos.
     * @param estaciones Lista de estaciones.
     */
    protected void popularEstaciones(List<Arco<Integer>> arcos, List<Integer> estaciones) {
        for (Arco<Integer> arco : arcos) {
            if (!estaciones.contains(arco.getVerticeOrigen())) {
                estaciones.add(arco.getVerticeOrigen());
            }
            if (!estaciones.contains(arco.getVerticeDestino())) {
                estaciones.add(arco.getVerticeDestino());
            }
        }
    }
    
    
}

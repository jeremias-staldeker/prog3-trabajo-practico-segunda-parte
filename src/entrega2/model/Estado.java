package entrega2.model;

import java.util.ArrayList;
import java.util.List;

import entrega2.model.grafo.Arco;
import entrega2.util.UnionFind;

/**
 * Clase que representa el estado actual en el algoritmo de Backtracking.
 */
public class Estado {
    private int posicion;
    private int km;
    private List<Arco<Integer>> solucionParcial;
    private UnionFind unionFind;

    /**
     * Constructor de la clase Estado.
     *
     * @param cantEstaciones Cantidad de estaciones.
     */
    public Estado(int cantEstaciones) {
        this.posicion = 0;
        this.km = 0;
        this.solucionParcial = new ArrayList<>();
        this.unionFind = new UnionFind(cantEstaciones);
    }

    /**
     * Obtiene la estructura UnionFind que representa las uniones de las estaciones.
     *
     * @return La estructura UnionFind.
     */
    public UnionFind getUnion() {
        return unionFind;
    }

    /**
     * Establece la estructura UnionFind que representa las uniones de las estaciones.
     *
     * @param union La estructura UnionFind.
     */
    public void setUnion(UnionFind union) {
        this.unionFind = union;
    }

    /**
     * Obtiene la soluci�n parcial actual.
     *
     * @return La lista de arcos que representan la soluci�n parcial.
     */
    public List<Arco<Integer>> getSolucionParcial() {
        return new ArrayList<Arco<Integer>>(solucionParcial);
    }

    /**
     * Agrega un arco a la soluci�n parcial.
     *
     * @param arco Arco a agregar.
     */
    public void addArco(Arco<Integer> arco) {
        this.solucionParcial.add(arco);
    }

    /**
     * Elimina un arco de la soluci�n parcial.
     *
     * @param arco Arco a eliminar.
     */
    public void removeArco(Arco<Integer> arco) {
        this.solucionParcial.remove(arco);
    }

    /**
     * Obtiene la posici�n actual en la lista de candidatos.
     *
     * @return La posici�n actual.
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Establece la posici�n actual en la lista de candidatos.
     *
     * @param pos La posici�n actual.
     */
    public void setPosicion(int pos) {
        this.posicion = pos;
    }

    /**
     * Obtiene la cantidad de kil�metros recorridos hasta el momento.
     *
     * @return La cantidad de kil�metros recorridos.
     */
    public int getKms() {
        return km;
    }

    /**
     * Establece la cantidad de kil�metros recorridos hasta el momento.
     *
     * @param km La cantidad de kil�metros recorridos.
     */
    public void setKms(int km) {
        this.km = km;
    }
}

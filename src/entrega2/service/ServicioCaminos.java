package entrega2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entrega2.model.grafo.Grafo;

public class ServicioCaminos<T> {

    private Grafo<T> grafo;
    private int origen;
    private int destino;
    private int lim;

    public ServicioCaminos(Grafo<T> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }

    /**
     * Encuentra todos los caminos posibles desde un v�rtice de origen a un v�rtice
     * de destino sin pasar por m�s de "lim" arcos.
     * 
     * @return Lista con todos los caminos posibles.
     * 
     */
    public List<List<Integer>> caminos() {
        List<List<Integer>> caminos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();

        buscarCaminos(origen, destino, visitados, caminoActual, caminos, 0);

        return caminos;
    }

    /**
     * Realiza un recorrido DFS (Depth-First Search) para encontrar todos los
     * caminos posibles desde un v�rtice de origen a un v�rtice de destino sin pasar
     * por m�s de "lim" arcos.
     * 
     * Complejidad: O(V + E * C), donde V es la cantidad de v�rtices, E es la
     * cantidad de arcos y C es la cantidad de caminos posibles. Esto se debe a que
     * el algoritmo realiza un recorrido DFS desde el v�rtice de origen, explorando
     * todos los caminos posibles hasta el v�rtice de destino.
     * 
     * @param verticeActual  V�rtice actual en el recorrido.
     * @param verticeDestino V�rtice de destino del camino.
     * @param visitados      Conjunto de v�rtices visitados.
     * @param caminoActual   Lista con el camino actual.
     * @param caminos        Lista donde se almacenan todos los caminos encontrados.
     * @param arcos          N�mero de arcos recorridos en el camino actual.
     * 
     */
    private void buscarCaminos(int verticeActual, int verticeDestino, Set<Integer> visitados,
            List<Integer> caminoActual, List<List<Integer>> caminos, int arcos) {
        visitados.add(verticeActual);
        caminoActual.add(verticeActual);

        if (verticeActual == verticeDestino) {
            // Se ha encontrado un camino desde el v�rtice de origen al v�rtice de destino
            caminos.add(new ArrayList<>(caminoActual));
        } else if (arcos < lim) {
            // Obtiene los adyacentes del v�rtice actual
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeActual);
            while (adyacentes.hasNext()) {
                Integer adyacente = adyacentes.next();

                // Si el adyacente no ha sido visitado y el arco no ha sido utilizado antes,
                // realiza la recursi�n para explorar el siguiente v�rtice
                if (!visitados.contains(adyacente) && !caminoActual.contains(adyacente)) {
                    buscarCaminos(adyacente, verticeDestino, visitados, caminoActual, caminos, arcos + 1);
                }
            }
        }

        // Se retrocede al v�rtice anterior eliminando el v�rtice actual del camino
        caminoActual.remove(caminoActual.size() - 1);
        visitados.remove(verticeActual);
    }
}
package entrega2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entrega2.model.grafo.Grafo;

public class ServicioBFS<T> {

	private Grafo<T> grafo;

	public ServicioBFS(Grafo<T> grafo) {
		this.grafo = grafo;
	}

	/**
	 * Realiza un recorrido BFS (Breadth-First Search) en forma de bosque sobre el
	 * grafo.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos. Esto se debe a que el algoritmo realiza un recorrido BFS desde cada
	 * vértice no visitado en el grafo.
	 * 
	 * @return Lista con el recorrido BFS en forma de bosque.
	 */
	public List<Integer> bfsForest() {
		List<Integer> recorrido = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();

		// Recorre todos los vértices del grafo
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while (vertices.hasNext()) {
			Integer vertice = vertices.next();

			// Si el vértice no ha sido visitado, realiza el recorrido BFS
			if (!visitados.contains(vertice)) {
				bfs(vertice, visitados, recorrido);
			}
		}

		return recorrido;
	}

	/**
	 * Realiza un recorrido BFS (Breadth-First Search) desde el vértice dado.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos. Esto se debe a que el algoritmo recorre todos los vértices y arcos
	 * del grafo en un recorrido BFS.
	 * 
	 * @param vertice   Vértice inicial del recorrido.
	 * @param visitados Conjunto de vértices visitados.
	 * @param recorrido Lista donde se almacena el recorrido BFS.
	 */
	private void bfs(Integer vertice, Set<Integer> visitados, List<Integer> recorrido) {
		List<Integer> fila = new ArrayList<>();
		visitados.add(vertice);
		fila.add(vertice);

		while (!fila.isEmpty()) {
			Integer actual = fila.remove(0);
			recorrido.add(actual);

			// Obtiene los adyacentes del vértice
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
			while (adyacentes.hasNext()) {
				Integer adyacente = adyacentes.next();

				// Si el adyacente no ha sido visitado, lo agrega a la cola y marca como
				// visitado
				if (!visitados.contains(adyacente)) {
					visitados.add(adyacente);
					fila.add(adyacente);
				}
			}
		}
	}
}
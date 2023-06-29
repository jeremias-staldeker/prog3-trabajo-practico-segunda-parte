package entrega2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entrega2.model.grafo.Grafo;

public class ServicioDFS<T> {
	private Grafo<T> grafo;

	public ServicioDFS(Grafo<T> grafo) {
		this.grafo = grafo;
	}

	/**
	 * Realiza un recorrido DFS (Depth-First Search) en forma de bosque sobre el
	 * grafo.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos. Esto se debe a que el algoritmo realiza un recorrido DFS desde cada
	 * vértice no visitado en el grafo.
	 * 
	 * @return Lista con el recorrido DFS en forma de bosque.
	 */
	public List<Integer> dfsForest() {
		List<Integer> recorrido = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();

		// Recorre todos los vértices del grafo
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while (vertices.hasNext()) {
			Integer vertice = vertices.next();

			// Si el vértice no ha sido visitado, realiza el recorrido DFS
			if (!visitados.contains(vertice)) {
				dfs(vertice, visitados, recorrido);
			}
		}

		return recorrido;
	}

	/**
	 * Realiza un recorrido DFS (Depth-First Search) desde el vértice dado.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos. Esto se debe a que el algoritmo recorre todos los vértices y arcos
	 * del grafo.
	 * 
	 * @param vertice   Vértice inicial del recorrido.
	 * @param visitados Conjunto de vértices visitados.
	 * @param recorrido Lista donde se almacena el recorrido DFS.
	 */
	private void dfs(Integer vertice, Set<Integer> visitados, List<Integer> recorrido) {
		visitados.add(vertice);
		recorrido.add(vertice);

		// Obtiene los adyacentes del vértice
		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
		while (adyacentes.hasNext()) {
			Integer adyacente = adyacentes.next();
			// Si el adyacente no ha sido visitado, realiza el recorrido DFS
			if (!visitados.contains(adyacente)) {
				dfs(adyacente, visitados, recorrido);
			}
		}
	}
}
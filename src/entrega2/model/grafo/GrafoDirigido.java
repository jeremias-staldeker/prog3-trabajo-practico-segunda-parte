package entrega2.model.grafo;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {

	/**
	 * HashMap que almacena los vértices del grafo.
	 */
	private HashMap<Integer, ArrayList<Arco<T>>> vertices;

	public GrafoDirigido() {
		vertices = new HashMap<Integer, ArrayList<Arco<T>>>();
	}

	/**
	 * Agrega un vértice al grafo.
	 * 
	 * Complejidad: O(1)
	 *
	 * @param verticeId Identificador del vértice a agregar.
	 * 
	 */
	@Override
	public void agregarVertice(int verticeId) {
		if (!this.contieneVertice(verticeId)) {
			vertices.put(verticeId, new ArrayList<>());
		}
	}

	/**
	 * Elimina un vértice y sus arcos asociados del grafo.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos.
	 *
	 * @param verticeId Identificador del vértice a eliminar.
	 * 
	 */
	@Override
	public void borrarVertice(int verticeId) {
		vertices.remove(verticeId);
		Iterator<Integer> verticesIterator = obtenerVertices();
		while (verticesIterator.hasNext()) {
			int verticeActual = verticesIterator.next();
			List<Arco<T>> arcos = vertices.get(verticeActual);
			if (arcos != null) {
				Iterator<Arco<T>> iter = arcos.iterator();
				while (iter.hasNext()) {
					Arco<T> arco = iter.next();
					if (arco.getVerticeDestino() == verticeId) {
						iter.remove();
					}
				}
			}
		}
	}

	/**
	 * Agrega un arco entre dos vértices del grafo.
	 * 
	 * Complejidad: O(1)
	 *
	 * @param verticeId1 Identificador del vértice de origen.
	 * @param verticeId2 Identificador del vértice de destino.
	 * @param etiqueta   Etiqueta del arco.
	 * 
	 */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		if (arcos != null) {
			arcos.add(arco);
			vertices.put(verticeId1, arcos);
		}
	}

	/**
	 * Elimina un arco entre dos vértices del grafo.
	 * 
	 * Complejidad: O(E), donde E es la cantidad de arcos.
	 *
	 * @param verticeId1 Identificador del vértice de origen.
	 * @param verticeId2 Identificador del vértice de destino.
	 * 
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		if (arcos != null) {
			Iterator<Arco<T>> iter = arcos.iterator();
			while (iter.hasNext()) {
				Arco<T> arco = iter.next();
				if (arco.getVerticeDestino() == verticeId2) {
					iter.remove();
					break;
				}
			}
		}
	}

	/**
	 * Verifica si el grafo contiene un vértice con el identificador dado.
	 * 
	 * Complejidad: O(1)
	 *
	 * @param verticeId Identificador del vértice a buscar.
	 * @return true si el grafo contiene el vértice, false en caso contrario.
	 * 
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	/**
	 * Verifica si existe un arco entre dos vértices del grafo.
	 * 
	 * Complejidad: O(E), donde E es la cantidad de arcos.
	 *
	 * @param verticeId1 Identificador del vértice de origen.
	 * @param verticeId2 Identificador del vértice de destino.
	 * @return true si existe el arco, false en caso contrario.
	 * 
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		if (arcos != null) {
			for (Arco<T> arco : arcos) {
				if (arco.getVerticeDestino() == verticeId2) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtiene el arco entre dos vértices del grafo.
	 * 
	 * Complejidad: O(E), donde E es la cantidad de arcos.
	 *
	 * @param verticeId1 Identificador del vértice de origen.
	 * @param verticeId2 Identificador del vértice de destino.
	 * @return El arco entre los vértices, o null si no existe.
	 * 
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		if (arcos != null) {
			for (Arco<T> arco : arcos) {
				if (arco.getVerticeDestino() == verticeId2) {
					return arco;
				}
			}
		}
		return null;
	}

	/**
	 * Obtiene la cantidad de vértices en el grafo.
	 * 
	 * Complejidad: O(1)
	 *
	 * @return Cantidad de vértices.
	 * 
	 * 
	 */
	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	/**
	 * Obtiene la cantidad de arcos en el grafo.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos.
	 *
	 * @return Cantidad de arcos.
	 * 
	 */
	@Override
	public int cantidadArcos() {
		int count = 0;
		Iterator<Integer> verticesIterator = obtenerVertices();
		while (verticesIterator.hasNext()) {
			int verticeId = verticesIterator.next();
			ArrayList<Arco<T>> arcos = vertices.get(verticeId);
			if (arcos != null) {
				count += arcos.size();
			}
		}
		return count;
	}

	/**
	 * Obtiene un iterador sobre los vértices del grafo.
	 * 
	 * Complejidad: O(1)
	 *
	 * @return Iterador de enteros.
	 * 
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}

	/**
	 * Obtiene un iterador sobre los vértices adyacentes a un vértice dado.
	 * 
	 * Complejidad: O(1)
	 *
	 * @param verticeId Identificador del vértice.
	 * @return Iterador de enteros.
	 * 
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		List<Arco<T>> arcos = vertices.get(verticeId);
		if (arcos != null) {
			List<Integer> adyacentes = new ArrayList<>();
			for (Arco<T> arco : arcos) {
				adyacentes.add(arco.getVerticeDestino());
			}
			return adyacentes.iterator();
		}
		return Collections.emptyIterator();
	}

	/**
	 * Obtiene un iterador sobre todos los arcos del grafo.
	 * 
	 * Complejidad: O(V + E), donde V es la cantidad de vértices y E es la cantidad
	 * de arcos.
	 *
	 * @return Iterador de Arco<T>.
	 * 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> arcos = new ArrayList<>();
		Iterator<Integer> verticesIterator = obtenerVertices();
		while (verticesIterator.hasNext()) {
			int verticeId = verticesIterator.next();
			List<Arco<T>> listaArcos = vertices.get(verticeId);
			if (listaArcos != null) {
				arcos.addAll(listaArcos);
			}
		}
		return arcos.iterator();
	}

	/**
	 * Obtiene un iterador sobre los arcos que salen desde un vértice dado.
	 * 
	 * Complejidad: O(1)
	 *
	 * @param verticeId Identificador del vértice.
	 * @return Iterador de Arco<T>.
	 * 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		List<Arco<T>> arcos = vertices.get(verticeId);
		if (arcos != null) {
			return arcos.iterator();
		}
		return Collections.emptyIterator();
	}
}

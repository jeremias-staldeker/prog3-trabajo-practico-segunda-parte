package entrega2.algorithm;

import java.util.ArrayList;
import java.util.List;

import entrega2.model.grafo.Arco;
import entrega2.util.UnionFind;

/**
 * Clase que implementa el algoritmo Greedy para resolver el problema.
 */
public class Greedy extends Resolutor {
	private UnionFind unionFind;

	/**
	 * Constructor de la clase Greedy.
	 */
	public Greedy() {
		this.estaciones = new ArrayList<>();
		this.kms = 0;
		this.metrica = 0;
		this.nombre = "Greedy";
	}

	/**
	 * Implementacion del algoritmo Greedy para buscar la solucion.
	 * 
	 * @param candidatos Lista de arcos candidatos.
	 * @return Lista de arcos que representan la solucion.
	 * 
	 *  Complejidad: O(E * N), donde E es la cantidad de estaciones y N los tuneles.
	 */
	@Override
	public List<Arco<Integer>> buscarSolucion(List<Arco<Integer>> candidatos) {
		ArrayList<Arco<Integer>> solucion = new ArrayList<>();

		// Ordenar los candidatos por menor kilometro
		ordenarCandidatosMenorKm(candidatos);

		// Obtener las estaciones
		popularEstaciones(candidatos, estaciones);

		// Crear una estructura UnionFind para unir estaciones
		this.unionFind = new UnionFind(this.estaciones.size());

		while (!candidatos.isEmpty() && solucion.size() < this.estaciones.size() - 1) {
			Arco<Integer> arco = candidatos.remove(0);
			sumarMetrica();
			if (esArcoValido(arco)) {
				solucion.add(arco);
				int verticeOrigen = this.estaciones.indexOf(arco.getVerticeOrigen());
				int verticeDestino = this.estaciones.indexOf(arco.getVerticeDestino());
				this.unionFind.union(verticeOrigen, verticeDestino);
				this.kms += arco.getEtiqueta();
			}
		}

		return solucion;
	}

	/**
	 * El algoritmo de ordenamiento QuickSort es utilizado para realizar el
	 * ordenamiento.
	 * 
	 * Complejidad: O(N log N), donde N es la cantidad de candidatos.
	 * 
	 * @param candidatos Lista de arcos candidatos.
	 */
	private void ordenarCandidatosMenorKm(List<Arco<Integer>> candidatos) {
		candidatos.sort(Arco.etiquetaComparator());
	}

	/**
	 * Verifica si un arco es valido en base a las estaciones ya seleccionadas.
	 *
	 * Complejidad: O(N) donde N es la cantidad de estaciones.
	 * 
	 * @param arco Arco a verificar.
	 * @return true si el arco es valido, false en caso contrario.
	 * 
	 */
	private boolean esArcoValido(Arco<Integer> arco) {
		int destino = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeDestino()));
		int origen = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeOrigen()));

		// Verificar si el arco conecta dos componentes distintas
		return destino != origen;
	}
}

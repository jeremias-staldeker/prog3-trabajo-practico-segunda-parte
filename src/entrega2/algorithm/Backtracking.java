package entrega2.algorithm;

import java.util.ArrayList;
import java.util.List;

import entrega2.model.Estado;
import entrega2.model.grafo.Arco;
import entrega2.util.UnionFind;

/**
 * Clase que implementa el algoritmo Backtracking para resolver el problema.
 */
public class Backtracking extends Resolutor {

	private List<Arco<Integer>> mejorSolucion;

	/**
	 * Constructor de la clase Backtracking.
	 */
	public Backtracking() {
		this.estaciones = new ArrayList<>();
		this.setKms(Integer.MAX_VALUE);
		this.metrica = 0;
		this.nombre = "Backtracking";
		this.mejorSolucion = new ArrayList<>();
	}

	/**
	 * Implementacion del algoritmo Backtracking para buscar la solucion.
	 *
	 * @param candidatos Lista de arcos candidatos.
	 * @return Lista de arcos que representan la solucion.
	 */
	@Override
	public List<Arco<Integer>> buscarSolucion(List<Arco<Integer>> candidatos) {
		// Obtener la lista de estaciones a partir de los arcos candidatos
		this.popularEstaciones(candidatos, estaciones);

		// Crear un estado inicial con la cantidad de estaciones
		Estado estado = new Estado(estaciones.size());

		// Iniciar el proceso de backtracking
		backtracking(candidatos, estado);

		// Devolver la mejor solución encontrada
		return this.mejorSolucion;
	}

	/**
	 * Metodo recursivo de Backtracking para buscar la solucion.
	 * Complejidad: O((2 ^ N) * N), donde N es la cantidad de tuneles.
	 *
	 * @param candidatos Lista de arcos candidatos.
	 * @param e          Estado actual.
	 */
	private void backtracking(List<Arco<Integer>> candidatos, Estado e) {
		sumarMetrica();

		// Verificar si se ha explorado todos los arcos candidatos
		if (e.getPosicion() == candidatos.size()) {
			// Verificar si el estado actual tiene una unica componente conexa
			if (e.getUnion().numberOfSets() == 1) {
				// Verificar si es la primera solucion encontrada o si es mejor que la anterior
				if (this.mejorSolucion.isEmpty()) {
					this.setKms(e.getKms());
					this.mejorSolucion.addAll(e.getSolucionParcial());
				} else {
					if (e.getKms() <= this.getKms()) {
						this.setKms(e.getKms());
						this.mejorSolucion.clear();
						this.mejorSolucion.addAll(e.getSolucionParcial());
					}
				}
			}
		} else {
			int posicionActual = e.getPosicion();
			int kmActual = e.getKms();
			Arco<Integer> arco = candidatos.get(posicionActual);

			int origenEstacion = this.estaciones.indexOf(arco.getVerticeOrigen());
			int destinoEstacion = this.estaciones.indexOf(arco.getVerticeDestino());

			// Verificar si el arco es factible y si mejora la mejor solucion encontrada hasta ahora
			if (this.esArcoValido(arco, e) && kmActual + arco.getEtiqueta() < this.getKms()) {
				// Hacer una copia de la estructura UnionFind para poder deshacer cambios
				UnionFind unionAux = e.getUnion().clone();

				// Realizar la union de las estaciones y actualizar el estado
				e.getUnion().union(origenEstacion, destinoEstacion);
				e.addArco(arco);
				e.setKms(kmActual + arco.getEtiqueta());
				e.setPosicion(posicionActual + 1);

				// Llamar recursivamente al backtracking con el nuevo estado
				this.backtracking(candidatos, e);

				// Deshacer los cambios realizados para explorar otras opciones
				e.setUnion(unionAux);
				e.removeArco(arco);
				e.setPosicion(posicionActual);
				e.setKms(kmActual);
			}

			// Continuar explorando sin usar el arco actual
			e.setPosicion(posicionActual + 1);
			this.backtracking(candidatos, e);
			e.setPosicion(posicionActual);
		}
	}

	/**
	 * Verifica si un arco es factible en base al estado actual.
	 *
	 * @param arco   Arco a verificar.
	 * @param estado Estado actual.
	 * @return true si el arco es factible, false en caso contrario.
	 * 
	 *         Complejidad: Complejidad: O(N) donde N es la cantidad de estaciones.
	 */
	private boolean esArcoValido(Arco<Integer> arco, Estado estado) {
		int destino = estado.getUnion().find(estaciones.indexOf(arco.getVerticeDestino()));
		int origen = estado.getUnion().find(estaciones.indexOf(arco.getVerticeOrigen()));

		// Verificar si el arco conecta dos componentes distintas
		return destino != origen;
	}
}

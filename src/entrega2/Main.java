package entrega2;


import entrega2.algorithm.Backtracking;
import entrega2.algorithm.Greedy;
import entrega2.util.CSVReader;

/**
 * Clase principal del programa.
 */
public class Main {

	/**
	 * Punto de entrada del programa.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Se arma un listado de arcos en base a un archivo TXT
		String path = "src/entrega2/datasets/dataset1.txt";
		CSVReader reader = new CSVReader(path);

		// Se instancia el resolutor con algoritmo Greedy, busca la solucion e imprime.
		Greedy greedy = new Greedy();
		greedy.imprimirSolucion(reader.getArcos());

		// Se instancia el resolutor con algoritmo Backtracking, busca la solucion e imprime.
		Backtracking backtracking = new Backtracking();
		backtracking.imprimirSolucion(reader.getArcos());
	}
}

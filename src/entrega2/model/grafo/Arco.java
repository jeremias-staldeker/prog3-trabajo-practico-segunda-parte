package entrega2.model.grafo;

import java.util.Comparator;
import java.util.Objects;

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}

	public int getVerticeOrigen() {
		return verticeOrigen;
	}

	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco<T> other = (Arco<T>) obj;
		return Objects.equals(etiqueta, other.etiqueta) && verticeDestino == other.verticeDestino
				&& verticeOrigen == other.verticeOrigen;
	}

	@Override
	public String toString() {
		return "Origen: " + verticeOrigen + ",Destino: " + verticeDestino + ", etiqueta: " + etiqueta + "\n";
	}
	
	// Comparator para comparar los arcos por etiqueta
    public static <T extends Comparable<T>> Comparator<Arco<T>> etiquetaComparator() {
        return Comparator.comparing(Arco::getEtiqueta);
    }

}

package entrega2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entrega2.model.Parser;
import entrega2.model.grafo.Arco;

public class CSVReader {

	private String path;

	public CSVReader(String path) {
		this.path = path;
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		return lines;
	}

	public <T> List<Arco<T>> getArcos() {
		// Se obtiene el parser de String a T
		// Podria agregarse logica en una funcion "getParser()" que retorne un parser especifico segun el tipo de T.
		// No se realizo en este caso porque no se justificaba.
		Parser<T> parser = Parser.getParser();
		List<Arco<T>> arcos = new ArrayList<>();

		// Se procesa cada linea individualmente y mapea a un Arco
		for (String[] line : this.readContent()) {
			Integer origen = Integer.parseInt(line[0].trim().substring(1));
			Integer destino = Integer.parseInt(line[1].trim().substring(1));
			T etiqueta = parser.parse(line[2].trim());

			Arco<T> arco = new Arco<T>(origen, destino, etiqueta);
			arcos.add(arco);
		}

		return arcos;
	}

}

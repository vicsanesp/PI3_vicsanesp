package ejemplos;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejemplo2 {

	public static void main(String[] args) {
		List<BinaryTree<Character>> ej2Arboles = lectorEjemplo2Arbol("ficherosEjemplo/Ejemplo2_DatosEntrada.txt");
		List<List<Character>> ej2Listas = lectorEjemplo2Listas("ficherosEjemplo/Ejemplo2_DatosEntrada.txt");
		System.out.println("Recursivo:");
		for (int i = 0; i < ej2Arboles.size(); i++) {
			System.out.println("Entrada: " + ej2Arboles.get(i) + ej2Listas.get(i) + "\nSalida: " + ejemplo2Rec(ej2Arboles.get(i), ej2Listas.get(i), 0));
		}
	}
	
	public static Boolean ejemplo2Rec(BinaryTree<Character> arbolaso, List<Character> camino, Integer i) {
		Boolean res = true;
		switch(arbolaso.getType()) {
		case Empty:
			res = i == camino.size();
			break;
			
		case Leaf:
			res = arbolaso.getLabel() == camino.get(i) && i == camino.size()-1;
			break;
			
		case Binary:
			res = arbolaso.getLabel() == camino.get(i) && (ejemplo2Rec(arbolaso.getLeft(), camino, i+1) && ejemplo2Rec(arbolaso.getLeft(), camino, i+1));
			
		}
		return res;
	}
	
	public static List<BinaryTree<Character>> lectorEjemplo2Arbol(String ruta){
		List<String> fichero = Files2.linesFromFile(ruta);
		List<BinaryTree<Character>> res = new ArrayList<>();
		for(String l:fichero) {
			BinaryTree<Character> arbolito = BinaryTree.parse(l.split("#")[0], x->x.charAt(0));
			res.add(arbolito);
		}
		return res;
	}
	
	public static List<List<Character>> lectorEjemplo2Listas(String ruta){
		List<String> fichero = Files2.linesFromFile(ruta);
		List<List<Character>> res = new ArrayList<>();
		for(String l:fichero) {
			List<Character> aux = new ArrayList<>();
			String arbolito = l.split("#")[1];
			arbolito = arbolito.replace("[", "");
			arbolito = arbolito.replace("]", "");
			for (int i = 0; i < arbolito.split(",").length; i++) {
				aux.add(arbolito.split(",")[i].charAt(0));
			}
			res.add(aux);
			
		}
		return res;
	}

}

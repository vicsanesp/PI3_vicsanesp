package ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejemplo1 {

	public static void main(String[] args) {
		List<BinaryTree<Integer>> ej1 = lectorEjemplo1("ficherosEjemplo/Ejemplo1_DatosEntrada.txt");
		System.out.println("Recursivo:");
		for(BinaryTree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + "\nSalida: " + ejemplo1Rec(arbolito, true));
		}
		System.out.println("\nIterativo:");
		for(BinaryTree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + "\nSalida: " + ejemplo1It(arbolito));
		}

		
	}
	
	public static Boolean ejemplo1Rec(BinaryTree<Integer> arbolaso, Boolean cum) {
		switch(arbolaso.getType()) {
		case Empty:
			cum = false;
			break;
			
		case Leaf:
			cum = false;
			break;
			
		case Binary:
			if(!arbolaso.getRight().isEmpty() && !arbolaso.getLeft().isEmpty()) {
				Integer a = (Integer) arbolaso.getLeft().getLabel();
				Integer b = (Integer) arbolaso.getRight().getLabel();
				cum = arbolaso.getLabel().equals(a+b);
				if(cum && arbolaso.getLeft().isBinary()) {
					cum = ejemplo1Rec(arbolaso.getLeft(), cum);
				}
				if(cum && arbolaso.getRight().isBinary()) {
					cum = ejemplo1Rec(arbolaso.getRight(), cum);
				}
			}
		}
		return cum;
	}
	
	public static Boolean ejemplo1It(BinaryTree<Integer> arbolaso) {
		Boolean cum = true;
		Iterator<BinaryTree<Integer>> it = arbolaso.iterator();
		while(it.hasNext()) {
			BinaryTree<Integer> arbolito = it.next();
			if(arbolito.isBinary()){
				if(!arbolito.getRight().isEmpty() && !arbolito.getLeft().isEmpty()){
					Integer a = (Integer) arbolito.getLeft().getLabel();
					Integer b = (Integer) arbolito.getRight().getLabel();
					cum = arbolito.getLabel().equals(a+b);
				}
			}
		}
		return cum;
	}
	
	public static List<BinaryTree<Integer>> lectorEjemplo1(String ruta){
		List<String> fichero = Files2.linesFromFile(ruta);
		List<BinaryTree<Integer>> res = new ArrayList<>();
		
		for(String l:fichero) {
			BinaryTree<Integer> arbolito = BinaryTree.parse(l, x->Integer.parseInt(x));
			res.add(arbolito);
		}
		return res;
	}

}

package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.Set2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio2 {

	public static Set<Integer> ej2Rec(BinaryTree<Integer> arbolaso, Integer numerito){
		return ej2Rec(arbolaso, Set2.empty(), numerito);
	}
	
	public static Set<Integer> ej2Rec(BinaryTree<Integer> arbolaso, Set<Integer> res, Integer numerito){
		switch(arbolaso.getType()) {
		case Empty:
			return res;
		
		case Leaf:
			if(arbolaso.getLabel()>=numerito){
				res.add(arbolaso.getLabel());
			}
			return res;
			
		case Binary:
			if(arbolaso.getLabel()>=numerito) {
				res.add(arbolaso.getLabel());
			}
			
			if(arbolaso.getLeft().getLabel()>=numerito) {
				res.add(arbolaso.getLeft().getLabel());
				res.add(arbolaso.getRight().getLabel());
				ej2Rec(arbolaso.getLeft(), res, numerito);
				ej2Rec(arbolaso.getRight(), res, numerito);
			}
			else {
				if(arbolaso.getRight().getLabel()>=numerito) {
					res.add(arbolaso.getRight().getLabel());
				}
				ej2Rec(arbolaso.getRight(), res, numerito);
				ej2Rec(arbolaso.getLeft(), res, numerito);
			}
		}
		
		return res;
	}
	
	public static List<BinaryTree<Integer>> lector2Arboles(String fichero){
		List<String> lineas = Files2.linesFromFile(fichero);
		List<BinaryTree<Integer>> res = new ArrayList<>();
		
		for (String l:lineas) {
			String[] array = l.split("#");
			BinaryTree<Integer> arbolaso = BinaryTree.parse(array[0], x->Integer.parseInt(x));
			res.add(arbolaso);
		}
		return res;
	}
	
	public static List<Integer> lector2Limite(String fichero){
		List<String> lineas = Files2.linesFromFile(fichero);
		List<Integer> res = new ArrayList<>();
		for (String l:lineas) {
			String[] array = l.split("#");
			Integer arbolaso = Integer.parseInt(array[1]);
			res.add(arbolaso);
		}
		return res;
	}
}

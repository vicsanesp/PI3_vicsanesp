package ejercicios;


import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio3 {
	
	public static Map<Integer, List<Integer>> ej3Rec(BinaryTree<Integer> arbolaso){
		return ej3Rec(arbolaso, List2.empty(), Map2.empty(), 0, arbolaso.getLabel());
	}
	
	public static Map<Integer, List<Integer>> ej3Rec(BinaryTree<Integer> arbolaso, List<Integer> camino, Map<Integer, List<Integer>> mem, Integer cum, Integer initCum){
		switch(arbolaso.getType()) {
		case Empty:
			mem.put(cum, camino);
			cum = initCum;
			camino = List2.empty();
			break;
			
		case Leaf:
			camino.add(arbolaso.getLabel());
			cum = cum * arbolaso.getLabel();
			mem.put(cum, camino);
			camino = List2.empty();
			cum = initCum;
			break;
		
		case Binary:
			if(cum == 0) {
				ej3Rec(arbolaso.getLeft(), camino, mem, arbolaso.getLabel(), initCum);
				camino = List2.empty();
				ej3Rec(arbolaso.getRight(), camino, mem, arbolaso.getLabel(), initCum);
			}
			cum = cum * arbolaso.getLabel();
			camino.add(arbolaso.getLabel());
			ej3Rec(arbolaso.getLeft(), camino, mem, cum, initCum);
			camino = List2.empty();
			ej3Rec(arbolaso.getRight(), camino, mem, cum, initCum);
			
		}
		return mem;
	}
}

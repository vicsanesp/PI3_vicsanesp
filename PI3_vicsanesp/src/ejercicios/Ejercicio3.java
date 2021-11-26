package ejercicios;


import java.util.ArrayList;
import java.util.List;

import us.lsi.common.List2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio3 {
	
	public static List<Integer> ej3Rec(BinaryTree<Integer> arbolaso){
		return ej3Rec(arbolaso, List2.empty(), List2.empty());
	}
	
	public static List<Integer> ej3Rec(BinaryTree<Integer> arbolaso, List<Integer> cum, List<Integer> res){
		switch(arbolaso.getType()) {
		case Empty:
			return res;
			
		case Leaf:
			cum.add(arbolaso.getLabel());
			Integer productoRes = productoLista(res);
			Integer productoCum = productoLista(cum);
			if(productoRes<productoCum) {
				res = cum;
			}
			return res;
			
		
		case Binary:
			cum.add(arbolaso.getLabel());
			res = ej3Rec(arbolaso.getLeft(), new ArrayList<Integer>(cum), res);
			res = ej3Rec(arbolaso.getRight(), new ArrayList<Integer>(cum), res);
		}
		return res;
	}
	
	
	public static Integer productoLista(List<Integer> lista) {
		return lista.stream().mapToInt(x->x).reduce(1, Math::multiplyExact);
	}
	
	public static <E>List<E> listaAnyadido(List<E> lista, E anyade){
		lista.add(anyade);
		return lista;
	}
}

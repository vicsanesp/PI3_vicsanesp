package ejercicios;

import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio5 {

	public static Map<Paridad, List<Integer>> ej5Rec(BinaryTree<Integer> arbolaso){
		Map<Paridad, List<Integer>> map = Map2.empty();
//		map.put(Paridad.Par, List2.empty());
//		map.put(Paridad.Impar, List2.empty());
		return ej5Rec(arbolaso, map);
	}
	public static Map<Paridad, List<Integer>> ej5Rec(BinaryTree<Integer> arbolaso, Map<Paridad, List<Integer>> res){
		switch(arbolaso.getType()) {
		case Empty:
			return res;
			
		case Leaf:
			return res;
			
		case Binary:
			if(arbolaso.getLeft().getLabel()<arbolaso.getLabel() && arbolaso.getLabel()<arbolaso.getRight().getLabel()) {
				if(arbolaso.getLabel() % 2 == 0) {
					if(res.containsKey(Paridad.Par)) {
						res.get(Paridad.Par).add(arbolaso.getLabel());
					}
					else {
						List<Integer> par = List2.empty();
						par.add(arbolaso.getLabel());
						res.put(Paridad.Par, par);
					}
				}else {
					if(res.containsKey(Paridad.Impar)) {
						res.get(Paridad.Impar).add(arbolaso.getLabel());
					}
					else {
						List<Integer> imPar = List2.empty();
						imPar.add(arbolaso.getLabel());
						res.put(Paridad.Impar, imPar);
					}
				}
			}
			ej5Rec(arbolaso.getLeft(), res);
			ej5Rec(arbolaso.getRight(), res);
		
		}
		return res;
	}
}

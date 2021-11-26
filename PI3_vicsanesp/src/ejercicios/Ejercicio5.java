package ejercicios;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio5 {

	public static Map<Paridad, List<Integer>> ej5Rec(BinaryTree<Integer> arbolaso) {
		Map<Paridad, List<Integer>> map = Map2.empty();
//		map.put(Paridad.Par, List2.empty());
//		map.put(Paridad.Impar, List2.empty());
		return ej5Rec(arbolaso, map);
	}

	public static Map<Paridad, List<Integer>> ej5Rec(BinaryTree<Integer> arbolaso, Map<Paridad, List<Integer>> res) {
		switch (arbolaso.getType()) {
		case Empty:
			return res;

		case Leaf:
			return res;

		case Binary:
			if (!(arbolaso.getLeft().isEmpty() || arbolaso.getRight().isEmpty())) {
				if (arbolaso.getLeft().getLabel() < arbolaso.getLabel()
						&& arbolaso.getLabel() < arbolaso.getRight().getLabel()) {
					if (arbolaso.getLabel() % 2 == 0) {
						if (res.containsKey(Paridad.Par)) {
							res.get(Paridad.Par).add(arbolaso.getLabel());
						} else {
							List<Integer> par = List2.empty();
							par.add(arbolaso.getLabel());
							res.put(Paridad.Par, par);
						}
					} else {
						if (res.containsKey(Paridad.Impar)) {
							res.get(Paridad.Impar).add(arbolaso.getLabel());
						} else {
							List<Integer> imPar = List2.empty();
							imPar.add(arbolaso.getLabel());
							res.put(Paridad.Impar, imPar);
						}
					}
				}
			}
			ej5Rec(arbolaso.getLeft(), res);
			ej5Rec(arbolaso.getRight(), res);

		}
		return res;
	}

	public static Map<Paridad, List<Integer>> ej5It(BinaryTree<Integer> arbolaso) {
		Map<Paridad, List<Integer>> res = Map2.empty();
		Iterator<BinaryTree<Integer>> it = arbolaso.iterator();
		while (it.hasNext()) {
			BinaryTree<Integer> aux = it.next();
			if (aux.isBinary()) {
				if (!(aux.getLeft().isEmpty() || aux.getRight().isEmpty())) {
					if (aux.getLeft().getLabel() < aux.getLabel() && aux.getLabel() < aux.getRight().getLabel()) {
						if (aux.getLabel() % 2 == 0) {
							if (res.containsKey(Paridad.Par)) {
								res.get(Paridad.Par).add(aux.getLabel());
							} else {
								List<Integer> par = List2.empty();
								par.add(aux.getLabel());
								res.put(Paridad.Par, par);
							}
						} else {
							if (res.containsKey(Paridad.Impar)) {
								res.get(Paridad.Impar).add(aux.getLabel());
							} else {
								List<Integer> impar = List2.empty();
								impar.add(aux.getLabel());
								res.put(Paridad.Impar, impar);
							}
						}
					}
				}
			}
		}
		return res;
	}
}

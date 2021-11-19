package ejercicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import us.lsi.common.Files2;
import us.lsi.common.Set2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {

	public static <E> Set<E> ej1Rec(Tree<E> arbolaso, Predicate<E> p){
		Set<E> res = Set2.empty();
		return ej1Rec(arbolaso, p, res);
	}
	
	public static <E> Set<E> ej1Rec(Tree<E> arbolaso, Predicate<E> p, Set<E> res){
		switch(arbolaso.getType()) {
		
		case Empty:
			return res;
			
		case Leaf:
			if(p.test(arbolaso.getLabel())) {
				res.add(arbolaso.getLabel());
			}
			return res;
			
		case Nary:
//			if(p.test(arbolaso.getLabel())) {
//				res.add(arbolaso.getLabel());
//			}
			for (Tree<E> arbolito:arbolaso.getChildren()) {
				ej1Rec(arbolito, p, res);
			}
		}
		return res;
	}
	
	public static <E> Set<E> ej1It(Tree<E> arbolaso, Predicate<E> p){
		Set<E> res = Set2.empty();
		Iterator<Tree<E>> it = arbolaso.iterator();
		while(it.hasNext()) {
			Tree<E> arbolito = it.next();
			if(arbolito.isLeaf()) {
				E aux = arbolito.getLabel();
				if(p.test(aux)) {
					res.add(aux);
				}
			}
		}
		return res;
	}
	
	public static List<Tree<Integer>> lector1(String fichero){
		List<String> lineas = Files2.linesFromFile(fichero);
		List<Tree<Integer>> res = new ArrayList<>();
		for (String l:lineas) {
			Tree<Integer> arbolaso = Tree.parse(l, x->Integer.parseInt(x));
			res.add(arbolaso);
		}
		return res;
	}
}

package ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TreeLevel;

public class Ejemplo3 {

	public static void main(String[] args) {
		Predicate<Integer> esPar = x->x%2 == 0;
		Predicate<Integer> esPrimo = x->Math2.esPrimo(x);
		List<Tree<Integer>> ejemplo3 = lectorEjemplo3("ficherosEjemplo/Ejemplo3_DatosEntrada.txt");
		System.out.println("Recursivo:");
		for (int j = 0; j < ejemplo3.size(); j++) {
			System.out.println("Es par: " + ejemplo3Rec(ejemplo3.get(j), esPar));
			System.out.println("Es primo: " + ejemplo3Rec(ejemplo3.get(j), esPrimo));
			System.out.println("------------------------------------------------");
		}
		System.out.println("Iterativo:");
		for(Tree<Integer> arbolito:ejemplo3) {
			System.out.println("Es par: " + ejemplo3It(arbolito, esPar));
			System.out.println("Es primo: " + ejemplo3It(arbolito, esPrimo));
			System.out.println("------------------------------------------------");
		}
		
		
	}

	public static List<Tree<Integer>> lectorEjemplo3(String ruta) {
		List<Tree<Integer>> res = new ArrayList<>();
		List<String> fichero = Files2.linesFromFile(ruta);
		for (int i = 0; i < fichero.size(); i++) {
			Tree<Integer> arbolito = Tree.parse(fichero.get(i), x->Integer.parseInt(x));
			res.add(arbolito);
		}
		return res;
	}
	
	public static <E> List<Boolean> ejemplo3It(Tree<E> arbolaso, Predicate<E> p){
		List<Boolean> res = List2.empty();
		Iterator<TreeLevel<E>> it = arbolaso.byLevel();
		Boolean enc = false;
		while(it.hasNext()) {
			TreeLevel<E> nxt = it.next();
			if(res.size()-1<nxt.level()) {
				res.add(true);
				enc = false;
			}
			if(!enc) {
				if(!nxt.tree().isEmpty()) {
					if(!p.test(nxt.tree().getLabel())) {
						res.set(nxt.level(), false);
						enc = true;
					}
				}
			}
		}
		return res;
	}
	
	public static <E> List<Boolean> ejemplo3Rec(Tree<E> arbolaso, Predicate<E> p){
		return ejemplo3Rec(arbolaso, p, 0, new ArrayList<>());
	}
	
	public static <E> List<Boolean> ejemplo3Rec(Tree<E> arbolaso, Predicate<E> p, int nivel, List<Boolean> res){
		switch(arbolaso.getType()) {
		case Empty:
			break;
		case Leaf:
			actualizarLista(p.test(arbolaso.getLabel()), nivel, res);
			break;
		case Nary:
			actualizarLista(p.test(arbolaso.getLabel()), nivel, res);
			arbolaso.getChildren().forEach(x->ejemplo3Rec(x, p, nivel+1, res));
		}
		return res;
	}
	
	public static void actualizarLista(boolean ok, int nivel, List<Boolean> res) {
		if(nivel == res.size()) {
			res.add(ok);
		}
		else if(res.get(nivel)) {
			res.set(nivel, ok);
		}
	}
	
}

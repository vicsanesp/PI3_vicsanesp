package ejemplos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TreeLevel;

public class Ejemplo4 {

	public static void main(String[] args) {
		List<Tree<Integer>> ejemplo4 = lectorEjemplo4("ficherosEjemplo/Ejemplo4_DatosEntrada.txt");
		for (int i = 0; i < ejemplo4.size(); i++) {
			System.out.println("Recursivo: " + ejemplo4Rec(ejemplo4.get(i)));
			System.out.println("Iterativo: " + ejemplo4It(ejemplo4.get(i)));
			System.out.println("---------------------------------------------");
		}

	}
	
	public static <E> Map<Integer, List<E>> ejemplo4It(Tree<E> arbolaso){
		Map<Integer, List<E>> res = Map2.empty();
		Iterator<TreeLevel<E>> it = arbolaso.byLevel();
		while(it.hasNext()) {
			TreeLevel<E> nxt = it.next();
			List<E> value = res.get(nxt.level());
			if(value==null) {
				value = new LinkedList<E>();
				res.put(nxt.level(), value);
			}
			
			if(nxt.tree().isNary()) {
				if(nxt.tree().getChildren().size()%2==0) {
					value.add(nxt.tree().getLabel());
				}
			}
		}
		return res;
	}
	
	public static List<Tree<Integer>> lectorEjemplo4(String ruta) {
		List<Tree<Integer>> res = List2.empty();
		List<String> fichero = Files2.linesFromFile(ruta);
		for(String l: fichero) {
			Tree<Integer> arbolaso = Tree.parse(l, x->Integer.parseInt(x));
			res.add(arbolaso);
		}
		return res;
	}
	
	public static <E> void actualizaMap(E e, boolean ok, int nivel, Map<Integer, List<E>> res) {
		List<E> ls = res.get(nivel);
		if(ls==null) {
			if(ok) {
				res.put(nivel, new ArrayList<>(List.of(e)));
			}
			else {
				res.put(nivel, new ArrayList<>());
			}
		}
		else if(ok) {
			ls.add(e);
		}
	}
	
	public static <E> Map<Integer, List<E>> ejemplo4Rec(Tree<E> arbolaso){
		return ejemplo4Rec(arbolaso, 0, Map2.empty());
	}
	
	public static <E> Map<Integer, List<E>> ejemplo4Rec(Tree<E> arbolaso, int nivel, Map<Integer, List<E>> res){
		switch(arbolaso.getType()) {
		case Empty:
		case Leaf:
			actualizaMap(null, false, nivel, res);
			break;
		case Nary:
			actualizaMap(arbolaso.getLabel(), arbolaso.getChildren().size()%2==0, nivel, res);
			arbolaso.getChildren().forEach(x->ejemplo4Rec(x, nivel+1, res));
		}
		return res;
	}
	
	
}

package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.Set2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio4 {

	public static Set<String> ej4Rec(Tree<String> arbolaso){
		return ej4Rec(arbolaso, Set2.empty(), "");
	}
	
	public static Set<String> ej4Rec(Tree<String> arbolaso, Set<String> res, String palabra){
		switch(arbolaso.getType()) {
		case Empty:
			if(esPalindromo(palabra)==true) {
				System.out.println(palabra);
				res.add(palabra);
			}
			palabra = "";
			break;
		
		case Leaf:
			palabra += arbolaso.getLabel();
			if(esPalindromo(palabra)==true) {
				res.add(palabra);
			}
			palabra = "";
			break;
		
		case Nary:
			palabra += arbolaso.getLabel();
			for (Tree<String> arbolito:arbolaso.getChildren()) {
				ej4Rec(arbolito, res, palabra);
			}
		}
		return res;
	}
	
	public static List<Tree<String>> lector4(String ruta){
		List<Tree<String>> res = new ArrayList<>();
		List<String> lineas = Files2.linesFromFile(ruta);
		for (String l:lineas) {
			Tree<String> arbolito = Tree.parse(l);
			res.add(arbolito);
		}
		return res;
	}
	
	public static boolean esPalindromo(String p) {
		int i = 0;
		int j= p.length()-1;
		Boolean b = true;
		while(j - i >= 0 && b) {	
			b = p.charAt(i) == p.charAt(j);
			i = i + 1;
			j = j - 1;
			
		}
		return b;
	}
	
}

package test;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("==================================Ejercicio 1==================================");
		List<Tree<Integer>> ej1 = Ejercicio1.lector1("ficheros/PI3E1_DatosEntrada.txt");
		Predicate<Integer> par = x->x%2==0;
		Predicate<Integer> menor = x->x<5;
		System.out.println("SOLUCIÓN RECURSIVA-PAR:");
		for (Tree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + " Salida: " + Ejercicio1.ej1Rec(arbolito, par));
		}
		System.out.println("\n");
		System.out.println("SOLUCIÓN RECURSIVA-MENOR_QUE_CINCO:");
		for (Tree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + " Salida: " + Ejercicio1.ej1Rec(arbolito, menor));
		}
		
		System.out.println("\n");
		System.out.println("SOLUCIÓN ITERATIVA-PAR:");
		for (Tree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + " Salida: ");
		}
		
		System.out.println("\n");
		System.out.println("SOLUCIÓN ITERATIVA-MENOR_QUE_CINCO:");
		for (Tree<Integer> arbolito:ej1) {
			System.out.println("Entrada: " + arbolito + " Salida: ");
		}

		System.out.println("==================================Ejercicio 2==================================");
		System.out.println("SOLUCIÓN RECURSIVA:");
		List<BinaryTree<Integer>> ej2Arboles = Ejercicio2.lector2Arboles("ficheros/PI3E2_DatosEntrada.txt");
		List<Integer> ej2Limite = Ejercicio2.lector2Limite("ficheros/PI3E2_DatosEntrada.txt");
		for (int i = 0; i < ej2Arboles.size(); i++) {
			System.out.println("Entrada: " + ej2Arboles.get(i) + " Limite: " + ej2Limite.get(i) + " Salida: " + Ejercicio2.ej2Rec(ej2Arboles.get(i), ej2Limite.get(i)));
		}
		
		System.out.println("==================================Ejercicio 3==================================");
		System.out.println("SOLUCIÓN RECURSIVA:");
		List<BinaryTree<Integer>> ej3 = Ejercicio2.lector2Arboles("ficheros/PI3E3_DatosEntrada.txt");
		for(BinaryTree<Integer> arbolito:ej3) {
			System.out.println("Entrada: " + arbolito + " Salida: " + Collections.max(Ejercicio3.ej3Rec(arbolito).keySet()));
		}
	}

}

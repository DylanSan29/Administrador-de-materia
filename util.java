package proyecto;

import java.util.Scanner;

public class util {
	public static Scanner consola;
	public  static void pausar() {
		consola.nextLine();
		System.out.println("Presione enter para continuar...");
		consola.nextLine();

	}
	public static void inicializarPrograma() {
		consola=new Scanner(System.in);
	}
	public static void finalizarPrograma() {
		consola.close();
	}
}

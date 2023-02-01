package proyecto;


public class MenuMateria {
	final static int CREAR_MATERIA=1;
	final static int ELIMINAR_MATERIA=2;
	final static int REGRESAR_M_P=3;
	private 
	AdministraMateria administraMateria;
	public
	MenuMateria(){
		administraMateria=new AdministraMateria();
	}
	void fijaAdministradora(AdministraMateria administraMateria) {
		 this.administraMateria=administraMateria;
	}
	void funcionesMenu()  {
		boolean continuarPrograma;
		continuarPrograma=true;
		util.inicializarPrograma();
		MateriaDAO.cargarDAO();
		fijaAdministradora(MateriaDAO.administraMateria);
		do {
			switch(opcionesMenu()){
			case CREAR_MATERIA:
				crearMateria();
				break;
			case ELIMINAR_MATERIA:
				eliminarMateria();
				break;
			case REGRESAR_M_P:
				continuarPrograma=false;
				break;
			default:
			}
			if(continuarPrograma) {
				util.pausar();
			}
		}while(continuarPrograma);
		util.finalizarPrograma();
	}
	void eliminarMateria(){
		String patron;
		Materia materia;
		int coincidencia,decision;
		if(administraMateria.dameCuentaMaterias()>0) {
			System.out.print("C�digo de materia para eliminar: ");
			util.consola.nextLine();
			patron = util.consola.nextLine();
			coincidencia = administraMateria.buscaMateria(patron);
			if(coincidencia!=-1) {
				materia = administraMateria.dameMateria(coincidencia);
				System.out.println("Nombre de la materia seleccionada: "+materia.dameNombre());
				System.out.println("Cr�ditos: "+materia.dameCreditos());
				System.out.print("\n�Seguro que desea eliminar esta materia 1)Si  2)No?: ");
				decision = util.consola.nextInt();
				switch(decision) {
				case 1:
					administraMateria.borraMateria(coincidencia);
					MateriaDAO.borrarMateria(patron); 
					System.out.println("Materia eliminada con �xito.");
					break;
				case 2:
					System.out.println("No se elimin� la materia.");
					break;
				default:
					System.out.println("Opci�n incorrecta.");
				}
			}else {
				System.out.println("No se encontr� materia");
			}
		}else {
			System.out.println("No hay materias registradas");
		}
	}
	void crearMateria() {
		String nombre,codigo;
		int creditos;
		if(administraMateria.dameCuentaMaterias()<AdministraMateria.MAX_MATERIAS) {
			System.out.println("Crear materia\n\n");
			System.out.print("Nombre: ");
			util.consola.nextLine();
			nombre = util.consola.nextLine();
			System.out.print("Cr�ditos: ");
			creditos = util.consola.nextInt();
			System.out.print("C�digo: ");
			util.consola.nextLine();
			codigo = util.consola.nextLine();
			if(administraMateria.dameCuentaMaterias()>0) {
				if(administraMateria.buscaMateria(codigo)==-1) {
					administraMateria.insertarDatos(nombre,codigo,creditos);
					MateriaDAO.agregarMateria(nombre,codigo,creditos); 
					System.out.print("Materia creada...");
				}else {
					System.out.println("El c�digo que se intent� registrar ya lo contiene una materia...");
				}
			}else {
				administraMateria.insertarDatos(nombre,codigo,creditos);
				MateriaDAO.agregarMateria(nombre,codigo,creditos); 
				System.out.print("Materia creada...");
			}
		}else {
			System.out.println("No hay espacio de captura...");
		}
	}
	int opcionesMenu() {
		int opcion;
		System.out.println("Menu materias");
		System.out.println("1.-Crear materia.");
		System.out.println("2.-Eliminar materia.");
		System.out.println("3.-Regresar al men� principal.");
		System.out.print("Seleccione una opci�n: ");
		opcion  = util.consola.nextInt();
		return opcion;
	}
}
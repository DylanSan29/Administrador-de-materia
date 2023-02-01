package proyecto;

public class AdministraMateria {
	public final static int MAX_MATERIAS=10;
	private
	Materia materia[];
	int cuentaMaterias;
	public
	AdministraMateria() {
		materia = new Materia[MAX_MATERIAS];
		cuentaMaterias=0;
	}
	void insertarDatos(String nombre, String codigo, int creditos) {
		materia[cuentaMaterias] = new Materia(nombre,codigo,creditos);
		cuentaMaterias++;
	}
	int buscaMateria(String patron){
		int i,coincidencia=-1;
		String auxiliar;
		boolean continuarBusqueda=true;
		for(i=0;i<cuentaMaterias && continuarBusqueda;i++){
			auxiliar=materia[i].dameCodigo();
			if(patron.equals(auxiliar)){
				System.out.println("Coinciden ");
				coincidencia=i;
				continuarBusqueda=false;
			}
		}
		return coincidencia;
	}
	int dameCuentaMaterias() {
		return cuentaMaterias;
	}
	Materia dameMateria(int i) {
		return materia[i];
	}
	void borraMateria(int materiaBorrar) {
		int i;
		if(cuentaMaterias>0 && cuentaMaterias< MAX_MATERIAS){
			for(i=materiaBorrar;i<cuentaMaterias;i++) {
				materia[i]=materia[i+1];
			}
		}
		cuentaMaterias--;
	}
}

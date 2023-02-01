package proyecto;

public class Materia {
	int creditos;
	String nombre;
	String codigo;
	public
	Materia(String nombre,String codigo,int creditos) {
		this.nombre=nombre;
		this.codigo=codigo;
		this.creditos=creditos;
	}
	void fijaNombre(String nombre) {
		this.nombre=nombre;
	}
	void fijaCodigo(String codigo) {
		this.codigo=codigo;
	}
	void fijaCreditos(int creditos) {
		this.creditos=creditos;
	}
	String dameNombre() {
		return nombre;
	}
	String dameCodigo() {
		return codigo;
	}
	int dameCreditos() {
		return creditos;
	}
}

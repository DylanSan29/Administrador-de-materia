package proyecto;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriaDAO {
	public static AdministraMateria administraMateria;
	public  static void cargarDAO() {
		conector DAO=null;
		try {
			DAO = new conector(
					conector.MYSQL,
					"localhost",//"127.0.0.1"
					3306,
					"",
					"sievel",
					"root",
					"12345");
			cargaMaterias(DAO);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (DAO != null) {
					System.out.println("Closing connection");
					DAO.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}	
	public static void cargaMaterias(conector bd)
			throws SQLException{
		administraMateria = new AdministraMateria();
		ResultSet rs;
		String nombre,codigo;
		int creditos;
		rs=bd.executeQuery("select nombre,codigo, creditos from materia");
		System.out.println("Cargando materias...");
		do{
			rs.next();
			nombre=rs.getString(1);
			codigo=rs.getString(2);
			creditos=rs.getInt(3);
			System.out.println("nombre="+nombre+
					"\t\tcodigo="+codigo+ 
					"\t\tcreditos="+creditos
					);
			administraMateria.insertarDatos(nombre, codigo, creditos);
		}while(!rs.isLast())	;
	}
	
public static 	void agregarMateria(String nom,String cod, int cred) {
		conector DAO=null;
		try {
			DAO = new conector(
					conector.MYSQL,
					"localhost",//"127.0.0.1"
					3306,
					"",
					"sievel",
					"root",
					"Nalyddylan29$");
			DAO.execute("INSERT INTO materia (nombre,codigo,creditos) "+
					"VALUES ('"+nom+
					"','"+cod+
					"',"+cred+")"
					);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (DAO != null) {
					DAO.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
}
	public static void borrarMateria(String codigo) {
		conector DAO=null;
		try {
			DAO = new conector(
					conector.MYSQL,
					"localhost",//"127.0.0.1"
					3306,
					"",
					"sievel",
					"root",
					"Nalyddylan29$");
			DAO.execute("DELETE FROM materia  WHERE codigo='"+codigo+ "'");
		}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace(System.err);
			}finally {
				try {
					if (DAO != null) {
						DAO.close();
					}
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		
	}
}

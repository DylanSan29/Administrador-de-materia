package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

public class conector {
	private Connection connection;
	private Statement stmt;
	private HashMap<Integer, Statement> statements = new HashMap<Integer, Statement>();
	private int statementKeyCounter=0;
	public static final int INFORMIX = 0;
	public static final int MYSQL = 1;

	public conector(int databaseType, String machine, int port, String serverName,
			String databaseName, String user, String password)
					throws SQLException, ClassNotFoundException {
		String drivers[] = {
				"com.informix.jdbc.IfxDriver",
				"com.mysql.cj.jdbc.Driver"};
		//    "com.mysql.jdbc.Driver"};//in an older MySQL version, this worked
		String databaseTypes[] = {
				"informix-sqli",
				"mysql"
		};
		String connectString;
		switch (databaseType) {
		case INFORMIX:
			connectString = "jdbc:" + databaseTypes[databaseType]
					+ "://" + machine + ":" + port + ":INFORMIXSERVER=" + serverName
					+ ";Database=" + databaseName;
			break;
		case MYSQL:
			connectString = "jdbc:" + databaseTypes[databaseType]
					+ "://" + machine + ":" + port + "/" + databaseName;
			break;
		default:
			connectString = "";
		}
		Class.forName(drivers[databaseType]);
		connection = DriverManager.getConnection(connectString, user, password);
		stmt = connection.createStatement();
	}

	public conector(int databaseType, String machine, int port,
			String databaseName, String user, String password)
					throws SQLException, ClassNotFoundException {
		this(databaseType, machine, port, null, databaseName, user, password);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		return stmt.executeQuery(query);
	}

	public boolean execute(String instruction) throws SQLException {
		return stmt.execute(instruction);
	}
	public int executeUpdate(String instruction) throws SQLException {
		return stmt.executeUpdate(instruction);
	}

	public void close() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (statements.size()>0){
			Iterator<Integer> i=statements.keySet().iterator();
			while(i.hasNext()){
				statements.remove(i.next()).close();
			}
		}
		if (connection != null) {
			connection.close();
		}
	}

	public int createStatement() throws SQLException{
		statements.put(++statementKeyCounter,connection.createStatement());
		return statementKeyCounter;
	}

	public ResultSet executeQuery(int statementKey,String query) throws SQLException {
		return statements.get(statementKey).executeQuery(query);
	}

	public boolean execute(int statementKey,String instruction) throws SQLException {
		return statements.get(statementKey).execute(instruction);
	}

	public void closeStatement(int statementKey) throws SQLException{
		statements.remove(statementKey).close();
	}
}


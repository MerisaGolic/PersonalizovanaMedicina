package ba.etf.personalizovanamedicina;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class KonekcijaNaBazu {
	
	Connection connection;
	Statement iskaz;
	
	KonekcijaNaBazu()
	{
		connection = null;
		iskaz = null;	
	}
	
	public Connection konektuj()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
					"root");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		
		return connection;
	}
	
	public void diskonektuj(Connection c)
	{
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Statement getStatement() {
		
		try {
		// za datu konekciju kreira i vraća objekat klase Statement
		iskaz = connection.createStatement();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return iskaz;
		}
	
	public Statement createResultSetStatement(Connection konekcija) {
		try {
		iskaz = konekcija.createStatement(
		ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_UPDATABLE,
		ResultSet.HOLD_CURSORS_OVER_COMMIT);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return iskaz;
		} 


}
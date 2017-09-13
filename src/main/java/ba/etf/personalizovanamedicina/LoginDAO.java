package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
	
	ResultSet rs = null;

	ResultSet createResultSet(Connection kon, String username, String password) {
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select username, password from \"PersonalizovanaMedicina\".korisnici "
				   + "where username='" + username + "' and password='" + password + "'";
		
		try {
		rs = naredba.executeQuery(sql);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs;
		} 
	
	public Boolean provjeriPodatke(String username, String password)
	{
		rs = createResultSet(kon, username, password);
		try {
			return rs.first();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}

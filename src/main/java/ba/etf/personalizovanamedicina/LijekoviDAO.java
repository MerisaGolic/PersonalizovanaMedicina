package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LijekoviDAO {
	
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
	
	ResultSet rs = null;
	
	ResultSet createResultSet(Connection kon) {
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select naziv, standardna_doza from \"PersonalizovanaMedicina\".lijekovi ";
		
		try {
		rs = naredba.executeQuery(sql);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs;
		} 

	public ArrayList<String> dajSveNaziveLijekova() {
		rs = createResultSet(kon);
		try {
			
			ArrayList<String> niz = new ArrayList<String>();
				
			while(rs.next()){
				niz.add(rs.getString("naziv"));
			}
			//System.out.println(niz);
			return niz;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}




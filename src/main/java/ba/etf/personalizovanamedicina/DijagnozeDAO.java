package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DijagnozeDAO {
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
	
	
	ResultSet createResultSet(Connection kon) {

		ResultSet rs = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select naziv, opis, postotak from \"PersonalizovanaMedicina\".dijagnoze " +
					 "where koristi = 1 " + 
					 "order by postotak desc";
		
		try {
		rs = naredba.executeQuery(sql);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs;
		} 

	public ArrayList<String> dajDijagnoze() {

		ResultSet rs = null;
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
	
	public ArrayList<String> dajOpiseDijagnoze() {

		ResultSet rs = null;
		rs = createResultSet(kon);
		try {
			
			ArrayList<String> niz = new ArrayList<String>();
			rs.first();
			niz.add(rs.getString("opis"));
			
			while(rs.next()){
				niz.add(rs.getString("opis"));
			}
			//System.out.println(niz);
			return niz;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> dajPostotke() {

		ResultSet rs = null;
		rs = createResultSet(kon);
		try {
			
			ArrayList<String> niz = new ArrayList<String>();
			
			while(rs.next()){
				niz.add(rs.getString("postotak"));
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

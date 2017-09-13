package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.Models.Parametri;

public class DijagnozePacijentiDAO {
	
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
	
	
	
	public List<String> dodajParametre(int id, String naziv, Parametri p){
		ResultSet rs = null;
		List<String> rezultat = new ArrayList<String>();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PacijentiDAO pDAO = new PacijentiDAO();
		rs = pDAO.nadjiDijagnozu(naziv);
		int idD = 0;
		try {
			while(rs.next()){
				idD = rs.getInt(1);
			
			}
		Statement naredba = k.createResultSetStatement(kon);
		String sql2 = "Select id, naziv, standardna_doza " +
					  "From \"PersonalizovanaMedicina\".lijekovi as l, \"PersonalizovanaMedicina\".dijagnoze_lijekovi as dl " +
					  "where l.id = dl.id_lijeka and dl.id_dijagnoze = '"+ idD+ "'";
		String sql = "SELECT id_dijagnoze, id_pacijenta, datum_dijagnoze, nivo_secera_u_krvi, broj_eritrocita, broj_leukocita, broj_trombocita, doza_lijeka " +
	                 "FROM \"PersonalizovanaMedicina\".dijagnoze_pacijenti " +
	                 "WHERE  id_dijagnoze = '"+ idD +"' and id_pacijenta ='" + id + "'";
		
		
		double doza = 0;
		int standardna_doza = 0;
		double secer = p.getSecer();
		double er = p.getEritrociti();
		double le = p.getLeukociti();
		double tr = p.getTrombociti();
		String ime = "";
		rs2 = naredba.executeQuery(sql2);
		
		while(rs2.next()){
			standardna_doza = rs2.getInt(3);
			ime = rs2.getString(2);
			
			doza = standardna_doza;
			if(secer < 3.5 )
				doza = doza - doza*0.05;
			if(secer > 6.1)
				doza = doza + doza*0.18;
			if(er < 3.8)
				doza = doza - doza*0.1;
			if(er > 5.8)
				doza = doza + doza*0.12;
			if(le < 4)
				doza = doza + doza*0.08;
			if(le > 11.5)
				doza = doza + doza*0.09;
			if(tr < 150)
				doza = doza - doza*0.15;
			if(tr > 400)
				doza = doza - doza*0.13;
			
			}
		
		rs1 = naredba.executeQuery(sql);
		
		while(rs1.next())
		{
			rs1.updateDouble(4, secer);
			rs1.updateDouble(5, er);
			rs1.updateDouble(6, le);
			rs1.updateDouble(7, tr);
			rs1.updateInt(8, (int)doza); 
			rs1.updateRow();
		}
		
		rezultat.add(Double.toString(secer));
		rezultat.add(Double.toString(er));
		rezultat.add(Double.toString(le));
		rezultat.add(Double.toString(tr));
		rezultat.add(Integer.toString((int)doza));
		rezultat.add(ime);
		rezultat.add(naziv);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rezultat;
	}
	
	public List<String> dajParametre(int id, String naziv) {
		List<String> rezultat = new ArrayList<String>();
		ResultSet rs = null;
		Statement naredba = k.createResultSetStatement(kon);
		PacijentiDAO pDAO = new PacijentiDAO();
		rs = pDAO.nadjiDijagnozu(naziv);
		int idD = 0;
		
		try {
			while(rs.next()){
				idD = rs.getInt(1);
			}
		String sql = "SELECT nivo_secera_u_krvi, broj_eritrocita, broj_leukocita, broj_trombocita, doza_lijeka, naziv " +
				     "FROM \"PersonalizovanaMedicina\".dijagnoze_pacijenti as dp, \"PersonalizovanaMedicina\".lijekovi as l, \"PersonalizovanaMedicina\".dijagnoze_lijekovi as dl " +
				     "where dp.id_dijagnoze = dl.id_dijagnoze and l.id = dl.id_lijeka and dp.id_pacijenta = "+ id +" and dp.id_dijagnoze = "+ idD +" ";	
		rs = naredba.executeQuery(sql);
		while(rs.next()){
			rezultat.add(Double.toString(rs.getDouble(1)));
			rezultat.add(Double.toString(rs.getDouble(2)));
			rezultat.add(Double.toString(rs.getDouble(3)));
			rezultat.add(Double.toString(rs.getDouble(4)));
			rezultat.add(Integer.toString(rs.getInt(5)));
			rezultat.add(rs.getString(6));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rezultat;
	}
	
	

}

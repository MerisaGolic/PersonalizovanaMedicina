package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.Models.Dijagnoza;

public class SimptomiDijagnozeDAO {
	
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
	
	ResultSet createResultSetSveDijagnoze(Connection kon, List<String> simptomi) {
		Statement naredba = k.createResultSetStatement(kon);
		int vel = simptomi.size();
		
		String sim = "";
		for(int i=0;i<vel;i++){
			if(i==vel-1)
				sim=sim+"'"+simptomi.get(i)+"'";
			else sim=sim+"'"+simptomi.get(i)+"',";
		}
		
		ResultSet rs = null;
		String sql = "select d.naziv, d.opis, count(sd.id_simptoma) " +
                     "from  \"PersonalizovanaMedicina\".simptomi as s," +
				           "\"PersonalizovanaMedicina\".dijagnoze as d," +
                           "\"PersonalizovanaMedicina\".simptomi_dijagnoze as sd " +
                     "where s.id = sd.id_simptoma and d.id = sd.id_dijagnoze and " +
                           "s.naziv IN (" + sim + ") " +
                     "group by d.id " +
                     "having count(*) >= 1"; 
		try {
		rs = naredba.executeQuery(sql);
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		return rs;
		}
	
		private ResultSet createResultSetBrojSimptoma() {
		Statement naredba = k.createResultSetStatement(kon);
		
		ResultSet rs = null;
		String sql = "select d.naziv, count(sd.id_simptoma) " +
                     "from \"PersonalizovanaMedicina\".dijagnoze as d, " +
                           "\"PersonalizovanaMedicina\".simptomi_dijagnoze as sd " +
                     "where d.id = sd.id_dijagnoze " +
                     "group by d.naziv ";
                    
		try {
		rs = naredba.executeQuery(sql);
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		return rs;
		}
	
	
	public void dajSveDijagnoze(List<String> simptomi, Integer pos) {
		ResultSet rs_sve_dijagnoze = createResultSetSveDijagnoze(kon, simptomi);
		ResultSet rs_brojSimptoma = createResultSetBrojSimptoma();

		try {
			
			ArrayList<String> nizNaziva = new ArrayList<String>();
			ArrayList<String> nizOpisa = new ArrayList<String>();
			ArrayList<String> nizPostotaka = new ArrayList<String>();
			int postotak = 0;
			String d = null;
			
			while(rs_sve_dijagnoze.next()){
				
				d = rs_sve_dijagnoze.getString("naziv");
				
				rs_brojSimptoma.beforeFirst();
				while(rs_brojSimptoma.next()){
					String br = rs_brojSimptoma.getString(1);
					if(d.equals(br))
					{
						postotak = (int)((rs_sve_dijagnoze.getInt(3)/(double)rs_brojSimptoma.getInt(2))*100);
						if(postotak >= pos)
						{
							nizPostotaka.add(Integer.toString(postotak));	
							nizNaziva.add(d);
							nizOpisa.add(rs_sve_dijagnoze.getString("opis"));
						}
					}		
				}
				
			}
			
			ArrayList<ArrayList<String>> rezultat = new ArrayList<ArrayList<String>>();
			rezultat.add(nizNaziva);
			rezultat.add(nizOpisa);
			rezultat.add(nizPostotaka);
			
			odaberiDijagnoze(kon, rezultat);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
	}
	
	private void odaberiDijagnoze(Connection con, ArrayList<ArrayList<String>> listaDijagnoza)
	{
		
		int vel = listaDijagnoza.get(0).size();
		
		String d = "";
		for(int i=0;i<vel;i++){
			if(i==vel-1)
				d=d+"'"+listaDijagnoza.get(0).get(i)+"'";
			else d=d+"'"+listaDijagnoza.get(0).get(i)+"',";
		}
		
		Statement naredba = k.createResultSetStatement(con);
		String sql = "select * from \"PersonalizovanaMedicina\".dijagnoze " +
					 "WHERE naziv IN (" + d + ")";
		try {
			ResultSet rs = null;
			rs = naredba.executeQuery(sql);
			
			while(rs.next()){
				int index = listaDijagnoza.get(0).indexOf(rs.getString("naziv"));
				rs.updateInt("koristi", 1);
				rs.updateRow();
				
				rs.updateInt("postotak", Integer.parseInt(listaDijagnoza.get(2).get(index))); //promejna string i unt
				rs.updateRow();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetujDijagnoze()
	{
		
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select * from \"PersonalizovanaMedicina\".dijagnoze";
					 
		try {
			ResultSet rs = null;
			rs = naredba.executeQuery(sql);
			while(rs.next()){
				rs.updateInt("koristi", 0);
				rs.updateInt("postotak", 0);  //promjena string u int
				rs.updateRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int dajIdSimptoma(String naziv){
		int id = 0;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "Select id from \"PersonalizovanaMedicina\".simptomi " +
		             "where naziv = '"+ naziv +"'";
		try {
			ResultSet rs = naredba.executeQuery(sql);
			while(rs.next())
				return rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void dodajNovuDijagnozu(ArrayList<String> listaSimpt, Dijagnoza d){
		Statement naredba = k.createResultSetStatement(kon);
		String sql = null;
		PacijentiDAO pDAO = new PacijentiDAO();
		ResultSet rs = pDAO.nadjiDijagnozu(d.getNaziv());
		try {
			if(rs.next())
				return;
			else{
				sql = "INSERT INTO \"PersonalizovanaMedicina\".dijagnoze( " + 
	                  "naziv, opis, koristi) " + 
                      "VALUES ( '"+ d.getNaziv() +"', '"+ d.getOpis() +"', 0)";
				naredba.executeUpdate(sql);
				ResultSet rs1 = pDAO.nadjiDijagnozu(d.getNaziv());
				int idDijagnoze = 0;
				if(rs1.next())
					idDijagnoze = rs1.getInt("id");
				
				for(int i= 0; i<listaSimpt.size(); i++)
				{
					int id = dajIdSimptoma(listaSimpt.get(i));
					sql = "INSERT INTO \"PersonalizovanaMedicina\".simptomi_dijagnoze( " +
	                      "id_simptoma, id_dijagnoze) "+
	                      "VALUES ("+ id +", "+ idDijagnoze +")";
					naredba.executeUpdate(sql);
				}
				
				sql = "INSERT INTO \"PersonalizovanaMedicina\".lijekovi( "+
	                  "naziv, standardna_doza) " +
	                  "VALUES ('"+ d.getNazivLijeka() +"', '"+ d.getDoza() +"')"; 
				naredba.executeUpdate(sql);
				
				sql = "SELECT id FROM \"PersonalizovanaMedicina\".lijekovi "+
				      "where naziv = '"+d.getNazivLijeka()+"'";
				rs = naredba.executeQuery(sql);
				int idLijeka = 0;
				if(rs.next())
					idLijeka = rs.getInt("id");
				
				sql = "INSERT INTO \"PersonalizovanaMedicina\".dijagnoze_lijekovi( " +
	                  "id_dijagnoze, id_lijeka) "+
	                  "VALUES ('"+ idDijagnoze+ "', '"+ idLijeka+"')";
				naredba.executeUpdate(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

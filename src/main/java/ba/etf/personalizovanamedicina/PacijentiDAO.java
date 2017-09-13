package ba.etf.personalizovanamedicina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ba.etf.personalizovanamedicina.Models.Dijagnoza;
import ba.etf.personalizovanamedicina.Models.Korisnik;
import ba.etf.personalizovanamedicina.Models.Pacijent;

public class PacijentiDAO {
	
	KonekcijaNaBazu k = new KonekcijaNaBazu();

	Connection kon = k.konektuj();
    
	public ArrayList<Pacijent> dajSvePacijente(Korisnik kor){
		ResultSet rs = nadjiKorisnika(kor);
		int id = -1;

		try {
			while(rs.next())
				id = rs.getInt("id");
			
			Statement naredba = k.createResultSetStatement(kon);
			String sql = "select ime_prezime, datum_rodjenja, spol " +  
						 "from \"PersonalizovanaMedicina\".pacijenti as p, \"PersonalizovanaMedicina\".pacijenti_korisnici as pk " +
					     "where p.id = pk.id_pacijenta and pk.id_korisnika = '" + id + "'";
			
			rs = naredba.executeQuery(sql);
			ArrayList<Pacijent> niz = new ArrayList<Pacijent>();
			
			while(rs.next()){
				Pacijent p = new Pacijent();
				p.setImeiprezime(rs.getString("ime_prezime"));
				p.setDatumRodjenja(rs.getString("datum_rodjenja"));
				p.setSpol(rs.getString("spol"));
				
				niz.add(p);
			}
			
			return niz;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> dajSvePacijente(){
		ResultSet rs = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select ime_prezime from \"PersonalizovanaMedicina\".pacijenti";
		
		try {
			rs = naredba.executeQuery(sql);
			ArrayList<String> niz = new ArrayList<String>();
			
			while(rs.next()){
				niz.add(rs.getString("ime_prezime"));
			}
			
			return niz;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private ResultSet nadjiPacijenta(Pacijent p){
		ResultSet rs1 = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "select id from \"PersonalizovanaMedicina\".pacijenti " +
					 "where ime_prezime = '" + p.getImeiprezime() + "' and datum_rodjenja = '" + p.getDatumRodjenja() +"'";
		
		try {
		rs1 = naredba.executeQuery(sql);
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs1;
	}
	private ResultSet nadjiKorisnika(Korisnik kor){
		ResultSet rs2 = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "SELECT id FROM \"PersonalizovanaMedicina\".korisnici " +
                     "where username = '" + kor.getUsername() +"' and password = '"+ kor.getPassword()+ "'";
		
		try {
		rs2 = naredba.executeQuery(sql);
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs2;
	}
	public ResultSet nadjiDijagnozu(String d){
		ResultSet rs3 = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "SELECT id FROM \"PersonalizovanaMedicina\".dijagnoze " +
		             "where naziv = '" + d + "'";
		
		try {
		rs3 = naredba.executeQuery(sql);
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs3;
	}
	private ResultSet nadjiVezuKorisnikPacijent(int idk, int idp){
		ResultSet rs4 = null;
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "SELECT id_pacijenta, id_korisnika FROM \"PersonalizovanaMedicina\".pacijenti_korisnici " +
		             "where id_pacijenta = '" + idp + "' and id_korisnika = '" + idk + "'";
		
		try {
		rs4 = naredba.executeQuery(sql);
	
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return rs4;
	}
	private void dodajPacijentaUBazu(Pacijent p){
		Statement naredba = k.createResultSetStatement(kon);
		String sql = "INSERT INTO \"PersonalizovanaMedicina\".pacijenti(ime_prezime, spol, datum_rodjenja) " +
	                 "VALUES ('"+ p.getImeiprezime() +"', '"+ p.getSpol() +"', '"+ p.getDatumRodjenja() +"')";
		
		try {
		naredba.executeUpdate(sql);
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public void dodajDijagnozu(Pacijent p, Korisnik kor){
		//dodati dijagnozu pacijentu i pacijenta doktoru ako ga ima u bazi
		//dodati pacijeta u bazu ako ga nema
		ResultSet rsp = nadjiPacijenta(p);
		ResultSet rsk = nadjiKorisnika(kor);
		ResultSet rsd = nadjiDijagnozu(p.getDijagnoza());
		
		try {
			if(!rsp.next()){
				dodajPacijentaUBazu(p);
				rsp = nadjiPacijenta(p);
			}
			rsp.beforeFirst();
			while(rsp.next() && rsk.next() && rsd.next()){
				
				Statement naredba = k.createResultSetStatement(kon);
				//nadjiVEzu pacijnt doktor ako nema onda insert
				ResultSet rspk = nadjiVezuKorisnikPacijent(rsk.getInt("id"), rsp.getInt("id"));
				if(!rspk.next())
				{
					String sql = "INSERT INTO \"PersonalizovanaMedicina\".pacijenti_korisnici(id_pacijenta, id_korisnika) "+
							     "VALUES ('"+ rsp.getInt("id") +"', '"+ rsk.getInt("id") +"')";
					naredba.executeUpdate(sql);
				}
				
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.now();
				
				String sql = "INSERT INTO \"PersonalizovanaMedicina\".dijagnoze_pacijenti(id_dijagnoze, id_pacijenta, datum_dijagnoze) "+
	                  "VALUES ('"+ rsd.getInt("id")+"', '"+ rsp.getInt("id") +"', '" + dtf.format(localDate) +"')";
				naredba.executeUpdate(sql);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dijagnoza> dajSveDijagnoze(Pacijent p){
		ResultSet rs = null;
		Statement naredba = k.createResultSetStatement(kon);
		rs = nadjiPacijenta(p);
		int id = 0;
		try {
			while(rs.next())
				id = rs.getInt("id");
			
			String sql = "select naziv, opis, datum_dijagnoze " +
			             "from \"PersonalizovanaMedicina\".pacijenti as p, \"PersonalizovanaMedicina\".dijagnoze_pacijenti as dp, \"PersonalizovanaMedicina\".dijagnoze as d " +
					     "where p.id = dp.id_pacijenta and d.id = dp.id_dijagnoze " +
			             " and p.id = '" + id + "'";
			
			rs = naredba.executeQuery(sql);
			ArrayList<Dijagnoza> niz = new ArrayList<Dijagnoza>();
			
			while(rs.next()){
				Dijagnoza d = new Dijagnoza();
				d.setNaziv(rs.getString("naziv"));
				d.setOpis(rs.getString("opis"));
				d.setDatum(rs.getString("datum_dijagnoze"));
				niz.add(d);
			}
			
			return niz;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int dajIdPacijenta(Pacijent p){
	ResultSet rs = nadjiPacijenta(p);
	try {
		while(rs.next())
		{
			return rs.getInt(1);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	}

}

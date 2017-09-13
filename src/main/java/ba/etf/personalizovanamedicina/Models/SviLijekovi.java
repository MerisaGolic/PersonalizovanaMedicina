package ba.etf.personalizovanamedicina.Models;

import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.LijekoviDAO;

public class SviLijekovi {
	List<String> sviLijekovi = new ArrayList<String>();

	public List<String> getSviLijekovi() {
		LijekoviDAO ld = new LijekoviDAO();
		sviLijekovi = ld.dajSveNaziveLijekova();
		return sviLijekovi;
	}

	public void setSviLijekovi(List<String> sviLijekovi) {
		this.sviLijekovi = sviLijekovi;
	}

}

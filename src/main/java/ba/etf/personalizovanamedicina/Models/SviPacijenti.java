package ba.etf.personalizovanamedicina.Models;

import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.PacijentiDAO;

public class SviPacijenti {
	
	private List<String> sviPacijenti = new ArrayList<String>();

	public List<String> getSviPacijenti() {
		PacijentiDAO pDAO = new PacijentiDAO();
		sviPacijenti = pDAO.dajSvePacijente();
		
		return sviPacijenti;
	}

	public void setSviPacijenti(List<String> sviPacijenti) {
		this.sviPacijenti = sviPacijenti;
	}

}

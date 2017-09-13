package ba.etf.personalizovanamedicina.Models;

import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.SimptomiDAO;

public class SviSimptomi {
	List<String> sviSimptomi = new ArrayList<String>();

	public List<String> getSviSimptomi() {
		SimptomiDAO sDAO= new SimptomiDAO();
		sviSimptomi = sDAO.dajSveSimptome();
		
		return sviSimptomi;
	}

	public void setSviSiptomi(List<String> sviSiptomi) {
		this.sviSimptomi = sviSiptomi;
	} 
	
	

}

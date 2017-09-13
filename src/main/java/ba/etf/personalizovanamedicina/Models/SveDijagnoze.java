package ba.etf.personalizovanamedicina.Models;

import java.util.ArrayList;
import java.util.List;

import ba.etf.personalizovanamedicina.DijagnozeDAO;

public class SveDijagnoze {
	List<String> sveDijagnoze = new ArrayList<String>();
	List<String> sviOpisiDijagnoza = new ArrayList<String>();
	List<String> sviPostoci = new ArrayList<String>();

	public List<String> getSviPostoci() {
		DijagnozeDAO dDAO= new DijagnozeDAO();
		sviPostoci = dDAO.dajPostotke();
		return sviPostoci;
	}

	public void setSviPostoci(List<String> sviPostoci) {
		this.sviPostoci = sviPostoci;
	}

	public List<String> getSviOpisiDijagnoza() {
		DijagnozeDAO dDAO= new DijagnozeDAO();
		sviOpisiDijagnoza = dDAO.dajOpiseDijagnoze();
		
		return sviOpisiDijagnoza;
	}

	public void setSviOpisiDijagnoza(ArrayList<String> sviOpisiDijagnoza) {
		this.sviOpisiDijagnoza = sviOpisiDijagnoza;
	}

	public List<String> getSveDijagnoze() {	
		DijagnozeDAO dDAO= new DijagnozeDAO();
		sveDijagnoze = dDAO.dajDijagnoze();
		
		return sveDijagnoze;
	}

	public void setSveDijagnoze(ArrayList<String> sviSiptomi) {
		this.sveDijagnoze = sviSiptomi;
	} 
	public SveDijagnoze()
	{
		
	}
}

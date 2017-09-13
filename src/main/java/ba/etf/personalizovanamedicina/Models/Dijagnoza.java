package ba.etf.personalizovanamedicina.Models;


public class Dijagnoza {
	
	private String naziv;
	private String opis;
	private String datum;
	private String nazivLijeka;
	private String doza;
	public String getNazivLijeka() {
		return nazivLijeka;
	}

	public void setNazivLijeka(String nazivLijeka) {
		this.nazivLijeka = nazivLijeka;
	}

	public String getDoza() {
		return doza;
	}

	public void setDoza(String doza) {
		this.doza = doza;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public Dijagnoza()
	{
		
	}
}

package ba.etf.personalizovanamedicina.Models;

public class Pacijent {
	
	private String imeiprezime;
    private String datumRodjenja;
    private String spol;
    private String dijagnoza;

    public String getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public String getImeiprezime() {
        return imeiprezime;
    }

    public void setImeiprezime(String ip) {
        this.imeiprezime = ip;
    }
    
    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String dr) {
        this.datumRodjenja = dr;
    }
    
    public String getSpol() {
        return spol;
    }

    public void setSpol(String s) {
        this.spol = s;
    }
    
    public Pacijent() 
    {
    }

}

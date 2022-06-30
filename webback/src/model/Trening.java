package model;

public class Trening {
	
	private int id;
	private String naziv;
	private String tipTreninga;
	private int trajanje;
	private String opis;
	private String slika;
	private String trener;
	
	
	
	
	public Trening(String naziv, String tipTreninga, int trajanje, String opis, String slika, String trener) {
		super();
		this.naziv = naziv;
		this.tipTreninga = tipTreninga;
		this.trajanje = trajanje;
		this.opis = opis;
		this.slika = slika;
		this.trener = trener;
	}

	public Trening() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTipTreninga() {
		return tipTreninga;
	}

	public void setTipTreninga(String tipTreninga) {
		this.tipTreninga = tipTreninga;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
	
	public void setTrener(String trener) {
		this.trener = trener;
	}
	
	public String getTrener() {
		return trener;
	}
	
	
	
	

}

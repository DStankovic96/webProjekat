package model;

public class Sauna {
	
	private int id;
	private String naziv;
	private int trajanje;
	private String opis;
	private String slika;
	
	public Sauna() {
		super();
	}
	
	public Sauna(String naziv, int trajanje, String opis, String slika) {
		super();
		this.naziv = naziv;
		this.trajanje = trajanje;
		this.opis = opis;
		this.slika = slika;
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
	
	
}

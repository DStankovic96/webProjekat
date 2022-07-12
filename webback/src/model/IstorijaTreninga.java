package model;

public class IstorijaTreninga {
	
	private int id;
	private String datum;
	private String trening;
	private String kupac;
	private String trener;
	
	
	
	public IstorijaTreninga() {
		super();
	}
	public IstorijaTreninga(String datum, String trening, String kupac, String trener) {
		super();
		this.datum = datum;
		this.trening = trening;
		this.kupac = kupac;
		this.trener = trener;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getTrening() {
		return trening;
	}
	public void setTrening(String trening) {
		this.trening = trening;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public String getTrener() {
		return trener;
	}
	public void setTrener(String trener) {
		this.trener = trener;
	}
	
	
	
	

}

package model;

import java.util.ArrayList;

public class Trener extends User {
		
	private ArrayList<IstorijaTreninga> istorijaTreninga = new ArrayList<IstorijaTreninga>();
	private ArrayList<Trening> trening = new ArrayList<Trening>();

	

	public Trener(boolean obrisan, String username, String password, String name, String lastname, String gender,
			String dateOfBirth,  ArrayList<IstorijaTreninga> istorijaTreninga, ArrayList<Trening> trening) {
		super(obrisan, username, password, name, lastname, gender, dateOfBirth);
		// TODO Auto-generated constructor stub
		this.istorijaTreninga = istorijaTreninga;
		this.trening = trening;
	}



	



	public Trener() {
		super();
	}



	public ArrayList<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}



	public void setIstorijaTreninga(ArrayList<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}
	
	public ArrayList<Trening> getTrening() {
		return trening;
	}



	public void setTrening(ArrayList<Trening> trening) {
		this.trening = trening;
	}
	
	
	
	
}

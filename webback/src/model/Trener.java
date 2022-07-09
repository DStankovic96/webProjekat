package model;

import java.util.ArrayList;

public class Trener extends User {
		
	private ArrayList<IstorijaTreninga> istorijaTreninga = new ArrayList<IstorijaTreninga>();

	

	public Trener(boolean obrisan, String username, String password, String name, String lastname, String gender,
			String dateOfBirth,  ArrayList<IstorijaTreninga> istorijaTreninga) {
		super(obrisan, username, password, name, lastname, gender, dateOfBirth);
		// TODO Auto-generated constructor stub
		this.istorijaTreninga = istorijaTreninga;
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
	
	
	
	
}

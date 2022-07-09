package model;

import java.util.ArrayList;

public class Customer extends User {
	
	private int numberOfPoints;
	private ArrayList<Objekat> poseceniObjekti = new ArrayList<Objekat>();
//	private ArrayList<Clanarina> clanarina = new ArrayList<Clanarina>();
	
	private String typeOfCustomer;
	private ArrayList<Komentar> commentsMade = new ArrayList<Komentar>();
	private ArrayList<IstorijaTreninga> istorijaTreninga = new ArrayList<IstorijaTreninga>();
	private ArrayList<Clanarina> sveClanarine = new ArrayList<Clanarina>();
	
	public Customer() {
		super();
	}

	public Customer(boolean obrisan, String username, String password, String name, String lastname, String gender,
			String dateOfBirth, int numberOfPoints, ArrayList<Objekat> poseceniObjekti, String typeOfCustomer, ArrayList<Komentar> commentsMade,
			ArrayList<IstorijaTreninga> istorijaTreninga,  ArrayList<Clanarina> sveClanarine) {
		super(obrisan, username, password, name, lastname, gender, dateOfBirth);
		// TODO Auto-generated constructor stub
		
		this.numberOfPoints = numberOfPoints;
		this.poseceniObjekti = poseceniObjekti;
		this.typeOfCustomer = typeOfCustomer;
		this.commentsMade = commentsMade;
		this.istorijaTreninga = istorijaTreninga;
		this.sveClanarine = sveClanarine;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public ArrayList<Objekat> getPoseceniObjekti() {
		return poseceniObjekti;
	}

	public void setPoseceniObjekti(ArrayList<Objekat> poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}

	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}

	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}

	public ArrayList<Komentar> getCommentsMade() {
		return commentsMade;
	}

	public void setCommentsMade(ArrayList<Komentar> commentsMade) {
		this.commentsMade = commentsMade;
	}

	public ArrayList<IstorijaTreninga> getIstorijaTreninga() {
		return istorijaTreninga;
	}

	public void setIstorijaTreninga(ArrayList<IstorijaTreninga> istorijaTreninga) {
		this.istorijaTreninga = istorijaTreninga;
	}

	public ArrayList<Clanarina> getSveClanarine() {
		return sveClanarine;
	}

	public void setSveClanarine(ArrayList<Clanarina> sveClanarine) {
		this.sveClanarine = sveClanarine;
	}

	
	
	
	
	
	

}

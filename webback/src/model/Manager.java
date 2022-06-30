package model;

public class Manager extends User {
	private String objekat;

	public Manager(boolean obrisan, String username, String password, String name, String lastname, String gender,
			String dateOfBirth, String objekat) {
		super(obrisan, username, password, name, lastname, gender, dateOfBirth);
		this.objekat = objekat;
	}

	public Manager() {
		super();
	}

	public String getObjekat() {
		return objekat;
	}

	public void setObjekat(String objekat) {
		this.objekat = objekat;
	}
}

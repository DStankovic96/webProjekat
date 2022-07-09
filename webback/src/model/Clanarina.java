package model;

import java.util.ArrayList;

public class Clanarina {
		private int id;
		private String ime;
		private String typeOfClanarina;
		private String datumPlacanja;
		private String datumVazenja;
		private int cena;
		private String customer;
		private String statusClanarine;
		private int brojTermina;
		
		public Clanarina(String ime, String typeOfClanarina, String datumPlacanja, String datumVazenja, int cena,
				String customer, String statusClanarine, int brojTermina) {
			super();
			this.ime=ime;
			this.typeOfClanarina = typeOfClanarina;
			this.datumPlacanja = datumPlacanja;
			this.datumVazenja = datumVazenja;
			this.cena = cena;
			this.customer = customer;
			this.statusClanarine = statusClanarine;
			this.brojTermina = brojTermina;
		}

		public Clanarina() {
			super();
		}

		public int getId() {
			return id;
		}

		public String getIme() {
			return ime;
		}

		public void setIme(String ime) {
			this.ime = ime;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTypeOfClanarina() {
			return typeOfClanarina;
		}

		public void setTypeOfClanarina(String typeOfClanarina) {
			this.typeOfClanarina = typeOfClanarina;
		}

		public String getDatumPlacanja() {
			return datumPlacanja;
		}

		public void setDatumPlacanja(String datumPlacanja) {
			this.datumPlacanja = datumPlacanja;
		}

		public String getDatumVazenja() {
			return datumVazenja;
		}

		public void setDatumVazenja(String datumVazenja) {
			this.datumVazenja = datumVazenja;
		}

		public int getCena() {
			return cena;
		}

		public void setCena(int cena) {
			this.cena = cena;
		}

		public String getCustomer() {
			return customer;
		}

		public void setCustomer(String customer) {
			this.customer = customer;
		}

		public String getStatusClanarine() {
			return statusClanarine;
		}

		public void setStatusClanarine(String statusClanarine) {
			this.statusClanarine = statusClanarine;
		}

		public int getBrojTermina() {
			return brojTermina;
		}

		public void setBrojTermina(int brojTermina) {
			this.brojTermina = brojTermina;
		}
		
		
		
		
}

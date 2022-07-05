package dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Trener;


public class TrenerDAO {
	private Map<String, Trener> treneri = new HashMap<>();
	private String contextPath;
	public TrenerDAO() {
		
	}
	
	public TrenerDAO(String contextPath) {
		this.contextPath = contextPath;
		loadTreneri(contextPath);
	}
	
	public void loadTreneri(String contextPath) {
		//ucitavanje svih trenera iz baze(fajla treneri.json koji se nalazi u kontekstu)
		try {
			JsonReader reader = new JsonReader(new FileReader(contextPath + "/treneri.json"));
			Gson gson = new Gson();
			Trener[] tempTreneri = gson.fromJson(reader,  Trener[].class);
			for(Trener dm : tempTreneri) {
				this.treneri.put(dm.getUsername(), dm);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int generateId(String naziv) {
		int generatedId = -1;
		if(treneri.containsKey(naziv)) {
			ArrayList<Trener> listaTreneri = (ArrayList<Trener>) this.treneri.values().stream().collect(Collectors.toList());
			List<Integer> listIds = new ArrayList<Integer>();
			
			for(Trener dm : listaTreneri) {
				listIds.add(dm.getId());
			}
			
			for(int i = 0; i < listIds.size() + 1; i++) {
				if(!listIds.contains(i)) {
					generatedId = i;
					break;
				}
			}
		}
		return generatedId;
	}
	

	//vraca sve trenere
	public Collection<Trener> findAllTreneri(){
		return this.treneri.values();
	}
	//vraca trenera sa konkretnim korisnickim imenom
	public Trener findTrener(String username) {
		return this.treneri.containsKey(username) ? this.treneri.get(username) : null;
	}
	//dodavanje novog trenera i njegovo cuvanje u bazu
	public Trener addNewTrener(Trener trener) {
		if(!this.treneri.containsKey(trener.getUsername())) {
			this.treneri.put(trener.getUsername(), trener);
			this.treneri.get(trener.getUsername()).setId(this.generateId(trener.getUsername()));
			Gson gson = new Gson();
			//String temp = gson.toJson(this.treneri);
			
			Collection<Trener> tmp = this.treneri.values();
			String fileInput = gson.toJson(tmp);
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "treneri.json", false))){
				System.out.println("Upis novog trenera u bazu.");
				bw.append(fileInput);
				bw.append("\n");
				bw.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			return trener;
		}
		return null;
	}
	
	//izmena postojeceg trenera i smestanje u bazu
		public Trener updateTrener(Trener trener) {
			if(this.treneri.containsKey(trener.getUsername())) {
				Trener c = this.treneri.get(trener.getUsername());
				//menjam mu polja
				c.setName(trener.getName()); c.setLastname(trener.getLastname());
				c.setDateOfBirth(trener.getDateOfBirth()); c.setPassword(trener.getPassword());
				c.setGender(trener.getGender());
//				this.treneri.replace(trener.getUsername(), trener);
				Gson gson = new Gson();
				String temp = gson.toJson(this.treneri.values());
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "treneri.json", false))){
					bw.append(temp);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				return trener;
			}
			return null;
		}
		
		//brisanje trenera iz baze
		public Trener removeTrener(Trener trener) {
			if(this.treneri.containsKey(trener.getUsername())) {
				this.treneri.remove(trener.getUsername());
				Gson gson = new Gson();
				String temp = gson.toJson(this.treneri);
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "treneri.json", false))){
					bw.append(temp);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				return trener;
			}
			return null;
		}
		public boolean checkPassword(String tempPassword, String username) {
			System.out.println("Provera sifri");
			if(treneri.containsKey(username)) {
				String tempSifra = treneri.get(username).getPassword();
				if(tempSifra.equals(tempPassword)) {
					return true;
				}
			}
			return false;
		}
	
}

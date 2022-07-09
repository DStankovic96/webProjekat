package dao;

import java.io.BufferedReader;
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


import model.Objekat;

import model.Trening;



public class ObjekatDAO {
	
	private Map<String, Objekat> objekti = new HashMap<>();
	private String contextPath;
	
	public ObjekatDAO(){
		
	}
	
	public ObjekatDAO(String contextPath){
		this.contextPath=contextPath;
		loadObjekti(contextPath);
	}
	
	public void loadObjekti(String contextPath){
		BufferedReader in = null;
		try{
			JsonReader reader = new JsonReader(new FileReader(contextPath + "objekti.json"));
			Gson gson = new Gson();
			Objekat[] tempObjekti = gson.fromJson(reader, Objekat[].class);
			if(tempObjekti != null){
				for(Objekat o : tempObjekti){
					objekti.put(o.getName(), o);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Collection<Objekat> findAllObjekti(){
		loadObjekti(contextPath);
		return objekti.values();
	}
	
	public Objekat findObjekat(String name){
		Objekat o = objekti.containsKey(name) ? objekti.get(name) : null;
		return o;
	}
	
	public int generateId(String naziv) {
		int generatedId = -1;
		if(objekti.containsKey(naziv)) {
			ArrayList<Objekat> listaObjekata = (ArrayList<Objekat>) this.objekti.values().stream().collect(Collectors.toList());
			List<Integer> listIds = new ArrayList<Integer>();
			
			for(Objekat r : listaObjekata) {
				listIds.add(r.getId());
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
	
	public Objekat addNewObjekat(Objekat objekat){
		System.out.println("USLO U ADDNEWOBJEKAT");
		if(!objekti.containsKey(objekat.getName())){
			objekti.put(objekat.getName(), objekat);
			objekti.get(objekat.getName()).setId(this.generateId(objekat.getName()));
			Gson gson = new Gson();
			String temp = gson.toJson(objekti);
			
			Collection<Objekat> tmp = this.objekti.values();
			String fileInput = gson.toJson(tmp);
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "objekti.json", false))){
				System.out.println("Upis novog objekta u bazu.");
				bw.append(fileInput);
				bw.append("\n");
				bw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return objekat;
		}
		return null;
	}
	
//	public Objekat updateObjekat(Objekat objekat) {
//		if(objekti.containsKey(objekat.getName())) {
//			objekti.replace(objekat.getName(), objekat);
//			Gson gson = new Gson();
//			String temp = gson.toJson(objekti);
//			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "restorani.json", false))){
//				bw.append(temp);
//				bw.append("\n");
//				bw.close();
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
//			return objekat;
//		}
//		return null;
//	}
	
	//brisanje korisnika iz baze
//	public Objekat removeObjekat(Objekat objekat) {
//		if(objekti.containsKey(objekat.getName())) {
//			objekti.remove(objekat.getName());
//			Gson gson = new Gson();
//			String temp = gson.toJson(objekti);
//			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "restorani.json", false))){
//				bw.append(temp);
//				bw.append("\n");
//				bw.close();
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
//			return objekat;
//		}
//		return null;
//	}
	
	public Objekat getObjekatByManager(String username){
		Collection<Objekat> temp = objekti.values();
		Objekat o = new Objekat();
		for(Objekat tempObjekat : objekti.values()){
			if(tempObjekat.getManager().equals(username)){
				o = tempObjekat;
				break;
			}
		}
		return o;
	}
	
	public Trening addTrening(String objectName, Trening a){
		Collection<Objekat> temp = objekti.values();
		System.out.println("STA ISPISUJE Collection<Objekat> temp = objekti.values()." + temp);
		Objekat o = new Objekat();
		//u for petlji dobijarmo objekat o u kojem radi prosledjeni menadzer
		for(Objekat tempObjekat : objekti.values()){
			System.out.println("STA ISPISUJE Collection<Objekat> temp = objekti.values()." + tempObjekat.getType());
			if(tempObjekat.getName().equals(objectName)){
				o = tempObjekat;
				break;
			}
		}
		
		for(Trening tempTrening : o.getTreningList()){
			if(a.getNaziv().equals(tempTrening.getNaziv())){
				//vec postoji taj trening
				return null;
			}
		}
		o.getTreningList().add(a);
		System.out.println("try" + o); 
		System.out.println("TRENING LISTA" + o.getTreningList()); 
		Gson gson = new Gson();
		Collection<Objekat> tmp = this.objekti.values();
		for(Objekat tempObjekat : tmp){
			System.out.println("aj da vidimo ovako" + tempObjekat.getTreningList());
			if(tempObjekat.getName().equals(objectName)){
				o = tempObjekat;
				break;
			}
		}
		String fileInput = gson.toJson(tmp);
//		System.out.println("Upis novog objekta u bazu." + fileInput);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "objekti.json", false))){
			System.out.println("Upis novog objekta u bazu.");
			bw.append(fileInput);
			bw.append("\n");
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public Collection<Trening> getTreninzi(String username){
		Collection<Objekat> temp = objekti.values();
		Objekat r = new Objekat();
		//u for petlji dobijarmo objekat r u kojem radi prosledjeni menadzer
		for(Objekat tempObjekat : objekti.values()) {
			if(tempObjekat.getManager().equals(username)) {
				r = tempObjekat;
				break;
			}
		}
		
		return r.getTreningList();
	}

}

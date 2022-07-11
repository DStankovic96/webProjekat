package dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import model.Komentar;
import model.Objekat;


public class KomentarDAO {
		
		private Map<String, Komentar> comments = new HashMap<>();
		private Map<String, Objekat> objekti = new HashMap<>();
		private String contextPath;
		public KomentarDAO() {
			
		}
		public KomentarDAO(String contextPath) {
			this.contextPath = contextPath;
			loadComments(contextPath);
		}
		public void loadComments(String contextPath) {
			ArrayList<Objekat> objects = new ArrayList<Objekat>();
			try {
				JsonReader reader = new JsonReader(new FileReader(contextPath + "objekti.json"));
				Gson gson = new Gson();
				Objekat[] tempObjekti = gson.fromJson(reader, Objekat[].class);
				for(Objekat r : tempObjekti) {
					objects.add(r);
					objekti.put(r.getName(), r);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
				
		}
		
		//metoda koja vraca listu svih NEODOBRENIH komentara o jednom objektu[STRIKTNO ZA MENADZERA]
		public Collection<Komentar> findAllUnmoderatedComments(){
			loadComments(contextPath);
//			Objekat r = this.getObjekatByNazivManagera();
			ArrayList<Komentar> cmntList = new ArrayList<Komentar>();
			for(Objekat r : objekti.values())
			{
				for(Komentar k : r.getComments()) {
					System.out.println("isOdobren = " + k.isOdobren());
					if(!k.isOdobren()) {
						cmntList.add(k);
						System.out.println("duzina liste" + cmntList.size());
					}
				}
			}
			
			if(cmntList.size() > 0) {
				return cmntList;
			}
			return null;
		}
		
		//metoda koja brise komentar[sa aspekta MANAGER-a]
		public Komentar removeComment(Komentar k) {
			if(this.objekti.containsKey(k.getKomentarisaniObjekat())) {
				ArrayList<Komentar> listaKomentara = this.objekti.get(k.getKomentarisaniObjekat()).getComments();
				for(Komentar temp: listaKomentara) {
					if(temp.getId() == k.getId()) {
						temp.setObrisanOdModeratora(true);
						break;
					}
				}
				return k;
			}
			return null;
		}
		
		//metoda koja brise komentar[sa aspekta USER-a]
			public Komentar removeCommentUser(Komentar k) {
				if(this.objekti.containsKey(k.getKomentarisaniObjekat())) {
					ArrayList<Komentar> listaKomentara = this.objekti.get(k.getKomentarisaniObjekat()).getComments();
					for(Komentar temp: listaKomentara) {
						if(temp.getId() == k.getId()) {
							temp.setObrisanOdKupca(true);
							break;
						}
					}
					return k;
				}
				return null;
			}
		
//		public Objekat getObjekatByNazivManagera(String nazivManagera) {
//			Objekat temp = null;
//			for(Objekat r : objekti.values()) {
//				if(r.getManager().equals(nazivManagera)) {
//					temp = r;
//					return temp;
//				}
//			}
//			return temp;
//		}
		
		
		//metoda koja vraca listu svih ODOBRENIH komentara o jednom objektu na osnovu imena objekta[KUPAC]
			public Collection<Komentar> findAllKomentareForCustomer(String name){
//				if(this.objekti.containsKey(name)) {
//					return objekti.get(name).getComments();
//				}
//				return null;
				Collection<Objekat> r = objekti.values();
				Collection<Komentar> comments=  new ArrayList<Komentar>();
				System.out.println("STRING NAME " + name);
				for(Objekat k : r) {
						if(k.getName().equals(name))
						{
							System.out.println("PRONASO OBJEKAT " + name);
						for(Komentar z : k.getComments()){
							if(z.isOdobren()==true){
								System.out.println("ODOBREN " + name);
								comments.add(z);
							}
						}
						}
				}
				
				return comments;
			}
		
		
		
		//metoda koja vraca listu svih ODOBRENIH komentara o jednom objektu
//		public Collection<Komentar> findAllKomentare(String nazivManagera){
//			Objekat r = this.getObjekatByNazivManagera(nazivManagera);
//			ArrayList<Komentar> listaKomentara = new ArrayList<Komentar>();
//			for(Komentar k : r.getComments()) {
//				if(k.isOdobren() == true && k.isObrisanOdKupca() == false && k.isObrisanOdModeratora() == false) {
//					listaKomentara.add(k);
//				}
//			}
//			if(listaKomentara.size() > 0) {
//				return listaKomentara;
//			}
//			return null;
//		}
		
		//metoda koja vraca komentare koje je ostavio jedan korisnik o nekom objektu
		public Collection<Komentar> findKomentar(String username, String nazivObjekta) {
			Collection<Komentar> komentariKorisnika = new ArrayList<Komentar>();
			if(objekti.containsKey(nazivObjekta)) {
				ArrayList<Komentar> tempLista = objekti.get(nazivObjekta).getComments();
				
				for(Komentar k : tempLista) {
					if(k.getKupacKomentator().equals(username)) {
						komentariKorisnika.add(k);
					}
				}
			}
			return komentariKorisnika;
		}
		
		public int calculateOcena(ArrayList<Komentar> listaKomentara) {
			int temp = 0;
			for(Komentar k : listaKomentara) {
				temp += k.getOcena();
			}
			int ocena = temp / listaKomentara.size();
			return ocena;
		}
		
		public String generateId(String naziv) {
			String generatedId = "-1";
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			System.out.println("TIMESTAMP" + timeStamp);
			if(objekti.containsKey(naziv)) {
				ArrayList<Komentar> listaKomentara = this.objekti.get(naziv).getComments();
				List<String> listIds = new ArrayList<String>();
				for(Komentar k : listaKomentara) {
					listIds.add(k.getId());
				}
				
				for(int i = 0; i < listIds.size() + 1; i++) {
					if(!listIds.contains(i)) {
						generatedId = i + '_' + naziv + timeStamp;
						
						break;
					}
				}
			}
			
			return generatedId;
		}
		
//		public Collection<Komentar> updateModeratedComments(ArrayList<Komentar> listaKomentara){
//			Objekat r = this.getObjekatByNazivManagera(name);
//			ArrayList<Objekat> r = objekti.values();
//			if(r != null) {
//				Map<Integer, Komentar> komentari = new HashMap<>();
//				for(Komentar k : r.getComments()) {
//					komentari.put(k.getId(), k);
//				}
//				
//				for(Komentar cmnt : listaKomentara) {
//					komentari.replace(cmnt.getId(), cmnt);
//				}
//				
//				ArrayList<Komentar> tempList = (ArrayList<Komentar>) komentari.values().stream().collect(Collectors.toList());
//				r.setComments(tempList);
//				
//				Gson gson = new Gson();
//				String temp = gson.toJson(this.objekti.values());
//				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "objekti.json", false))){
//					bw.append(temp);
//					bw.append("\n");
//					bw.close();
//				}catch(IOException e) {
//					e.printStackTrace();
//				}
//			}
//			return null;
//		}
		
		public Komentar save(Komentar comment) {
			String nazivObjekta = comment.getKomentarisaniObjekat();
			System.out.println("TESdawdaw" + comment.isOdobren());
			comment.setId(this.generateId(nazivObjekta));
			objekti.get(nazivObjekta).getComments().add(comment);
			objekti.get(nazivObjekta).setOcena(this.calculateOcena(objekti.get(nazivObjekta).getComments()));
			System.out.println("Trenutna ocena objekta: " + nazivObjekta + " je " + objekti.get(nazivObjekta).getOcena());
			Gson gson = new Gson();
			//ovde sam imao gresku jer sam u fajl upisivao mapu a ne niz objekata
			String temp = gson.toJson(this.objekti.values());
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "objekti.json", false))){
				bw.append(temp);
				bw.append("\n");
				bw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return comment;
		}
	
			public Komentar update(Komentar comment) {
				
				loadComments(contextPath);
				String nazivObjekta = comment.getKomentarisaniObjekat();
				System.out.println("TESdawdaw" + comment.isOdobren());
//				comment.setId(this.generateId(nazivObjekta));
				for(Objekat o : objekti.values()){
					for(Komentar k : o.getComments()){
						if(k.getId().equals(comment.getId())){
							k.setOdobren(comment.isOdobren());
							System.out.println("PRONASAO KOMENTAR" + comment.isOdobren());
							
						}
					}
				}
			
				Gson gson = new Gson();
				//ovde sam imao gresku jer sam u fajl upisivao mapu a ne niz objekata
				String temp = gson.toJson(this.objekti.values());
				
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "objekti.json", false))){
					bw.append(temp);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				return comment;
			}
		
	
		
}

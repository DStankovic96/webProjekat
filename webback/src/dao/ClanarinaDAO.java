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
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Clanarina;
import model.Customer;



public class ClanarinaDAO {
		private Map<String, Clanarina> clanarine = new HashMap<>();
		private Map<String, Customer> kupci = new HashMap<>();
		private String contextPath;
		Random random = new Random();   
		
		public ClanarinaDAO(){
			
		}
		public ClanarinaDAO(String contextPath){
			this.contextPath = contextPath;
			loadClanarine(contextPath);
		}
		
		public void loadClanarine(String contextPath) {
			ArrayList<Customer> kupcii = new ArrayList<Customer>();
			try {
				JsonReader reader = new JsonReader(new FileReader(contextPath + "customers.json"));
				Gson gson = new Gson();
				Customer[] tempKupac = gson.fromJson(reader, Customer[].class);
				for(Customer r : tempKupac) {
					kupcii.add(r);
					kupci.put(r.getName(), r);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
				
		}
		
//		public String generateId(String naziv) {
//			String generatedId = "-1";
//			
//			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
//			System.out.println("TIMESTAMP" + timeStamp);
//			if(objekti.containsKey(naziv)) {
//				ArrayList<Komentar> listaKomentara = this.objekti.get(naziv).getComments();
//				List<String> listIds = new ArrayList<String>();
//				for(Komentar k : listaKomentara) {
//					listIds.add(k.getId());
//				}
//				
//				for(int i = 0; i < listIds.size() + 1; i++) {
//					if(!listIds.contains(i)) {
//						generatedId = i + '_' + naziv + timeStamp;
//						
//						break;
//					}
//				}
//			}
//			
//			return generatedId;
//		}
		
		public Collection<Clanarina> findAllClanarineForCustomer(String name){
//			System.out.println("IDEMO DRUGI TESTIC");
			if(this.kupci.containsKey(name)) {
				return kupci.get(name).getSveClanarine();
			}
			return null;
		}
		
		
		
		public ArrayList<Clanarina> posaljiClanarinu1(String name, ArrayList<Clanarina> clanarina){
			Collection<Customer> temp = kupci.values();
			Customer c = new Customer();
			for(Customer tempCustomer : kupci.values()){
				if(tempCustomer.getName().equals(name)){
					c = tempCustomer;
					break;
				}
			}
			

			
			c.setSveClanarine(clanarina);
			
			for(Clanarina tempClanarina : c.getSveClanarine()){
			if(tempClanarina.getId()==0){
				tempClanarina.setId(random.nextInt(50));
				}
			}
			Gson gson = new Gson();
			Collection<Customer> tmp = this.kupci.values();
			String fileInput = gson.toJson(tmp);

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
				System.out.println("Upis novog u bazu.");
				bw.append(fileInput);
				bw.append("\n");
				bw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	
			
			return clanarina;
		}
		
		public ArrayList<Clanarina> updateClanarinu1(String name, ArrayList<Clanarina> clanarina){
			Collection<Customer> temp = kupci.values();
			Customer c = new Customer();
			for(Customer tempCustomer : kupci.values()){
				if(tempCustomer.getName().equals(name)){
					c = tempCustomer;
					break;
				}
			}
			

			
			c.setSveClanarine(clanarina);
			Gson gson = new Gson();
			Collection<Customer> tmp = this.kupci.values();
			String fileInput = gson.toJson(tmp);

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
				System.out.println("Upis novog u bazu.");
				bw.append(fileInput);
				bw.append("\n");
				bw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	
			
			return clanarina;
		}
}

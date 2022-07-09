package dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Clanarina;
import model.Customer;
import model.Komentar;



public class ClanarinaDAO {
		private Map<String, Clanarina> clanarine = new HashMap<>();
		private Map<String, Customer> kupci = new HashMap<>();
		private String contextPath;
		
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
			
//			for(Clanarina tempClanarina : c.getSveClanarine()){
//				if(clanarin)
//			}
			
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

package dao;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Clanarina;
import model.Customer;
import model.IstorijaTreninga;

public class IstorijaTreningaDAO {

		private Map<String, Customer> kupci = new HashMap<>();
		private String contextPath;
		Random random = new Random();  
		
		public IstorijaTreningaDAO(){
			
		}
		public IstorijaTreningaDAO(String contextPath){
			this.contextPath = contextPath;
			loadIstorijaTreninga(contextPath);
		}
		
		public void loadIstorijaTreninga(String contextPath) {
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
		
		public ArrayList<IstorijaTreninga> findAllIstorijaTreningaForCustomer(String name){
//			System.out.println("IDEMO DRUGI TESTIC");
			System.out.println("USlO BR 4");
			if(this.kupci.containsKey(name)) {
				System.out.println("USlO BR 1");
				return kupci.get(name).getIstorijaTreninga();
			}
			return null;
		}
		
		public ArrayList<IstorijaTreninga> posaljiIstorijuTreninga(String name, ArrayList<IstorijaTreninga> istorijaTreninga){
			System.out.println("USLO U DODAj NOVU ISTORIJU TRENINGA DAO ODMA");
			loadIstorijaTreninga(contextPath);
			System.out.println("USLO U DODAj NOVU ISTORIJU TRENINGA DAO");
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
			
			c.setIstorijaTreninga(istorijaTreninga);
			for(IstorijaTreninga tempIT : c.getIstorijaTreninga()){
				if(tempIT.getId()==0){
					tempIT.setId(random.nextInt(50));
					}
				}
			Gson gson = new Gson();
			Collection<Customer> tmp = this.kupci.values();
			String fileInput = gson.toJson(tmp);
			
			System.out.println("USLO U DODAj NOVU ISTORIJU TRENINGA DAO3");
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
				System.out.println("Upis novog u bazu.");
				bw.append(fileInput);
				bw.append("\n");
				bw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	
			
			return istorijaTreninga;
		}
}

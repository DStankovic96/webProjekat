package dao;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Customer;

public class CustomerDAO {
	
	private Map<String, Customer> customers = new HashMap<>();
	private String contextPath;
	
	public CustomerDAO(){
		
	}
	
	public CustomerDAO(String contextPath){
		this.contextPath = contextPath;
		loadCustomers(contextPath);
	}
	
	public void loadCustomers(String contextPath){
		try{
			JsonReader reader = new JsonReader(new FileReader(contextPath + "/customers.json"));
			Gson gson = new Gson();
			Customer[] tempCustomers = gson.fromJson(reader, Customer[].class);
			for(Customer c : tempCustomers){
				customers.put(c.getUsername(), c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int generateId(String Naziv){
		int generatedId = -1;
		if(customers.containsKey(Naziv)){
			ArrayList<Customer> listaCustomera = (ArrayList<Customer>) this.customers.values().stream().collect(Collectors.toList());
			List<Integer> listIds = new ArrayList<Integer>();
			
			for(Customer c : listaCustomera){
				listIds.add(c.getId());
			}
			
			for(int i = 0; i<listIds.size()+1; i++){
				if(!listIds.contains(i)){
					generatedId = i;
					break;
				}
			}
		}
		return generatedId;
	}
	
	//vraca sve musterije
		public Collection<Customer> findAllCustomers(){
			return customers.values();
		}
		//vraca musteriju sa zadatim korisnickim imenom
		public Customer findCustomer(String username) {
			return customers.containsKey(username) ? customers.get(username) : null;
		}
		
		//dodavanje novog Customera i njegovo cuvanje u bazu
		public Customer addNewCustomer(Customer customer) {
			
			if(!customers.containsKey(customer.getUsername())) {
				customers.put(customer.getUsername(), customer);
				customers.get(customer.getUsername()).setId(this.generateId(customer.getUsername()));
				Gson gson = new Gson();
//				String temp = gson.toJson(customers);
				
				Collection<Customer> tmp = this.customers.values();
				String fileInput = gson.toJson(tmp);
				
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
					System.out.println("Upis novog korisnika u bazu.");
					bw.append(fileInput);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
					
				}
				return customer;
			}	
			return null;
		}
		
		//izmena postojeceg korisnika i njegovo cuvanje u bazu
		public Customer updateCustomer(Customer customer) {
			if(customers.containsKey(customer.getUsername())) {
				//dobavljam trazenog korisnika
				Customer c = this.customers.get(customer.getUsername());
				//menjam mu polja
				c.setName(customer.getName()); c.setLastname(customer.getLastname());
				c.setDateOfBirth(customer.getDateOfBirth()); c.setPassword(customer.getPassword());
				c.setGender(customer.getGender());
				//
//				customers.replace(customer.getUsername(), c);
				Gson gson = new Gson();
				String temp = gson.toJson(customers.values());
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
					bw.append(temp);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				return customer;
			}
			return null;
		}
		
		//brisanje korisnika iz baze
		public Customer removeCustomer(Customer customer) {
			if(customers.containsKey(customer.getUsername())) {
				customers.remove(customer.getUsername());
				Gson gson = new Gson();
				String temp = gson.toJson(customers);
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(contextPath + "customers.json", false))){
					bw.append(temp);
					bw.append("\n");
					bw.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				return customer;
			}
			return null;
		}
		
		public boolean checkPassword(String tempPassword, String username) {
			System.out.println("Provera sifri");
			if(this.customers.containsKey(username)) {
				String tempSifra = this.customers.get(username).getPassword();
				if(tempSifra.equals(tempPassword)) {
					return true;
				}
			}
			return false;
		}
	

}

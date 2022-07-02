package services;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Administrator;
import model.Customer;
import model.LoginUser;
import model.Manager;
import model.Trener;


@Path("/login")
public class LoginService {
	
	private String contextPath;
	
	@Context
	ServletContext ctx;
	
	public LoginService(){
		
	}
	
	@PostConstruct
	public void init(){
		this.contextPath=ctx.getRealPath("");
	}
	
	@POST
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginUser login(@PathParam("username") String username, String password){
		System.out.println("/login/username PASSWORD TEST: " + password);
		String tempRole = "";
		String[] tokens = password.split(":");
		String pw = "";
		if(tokens.length == 2){
			pw = tokens[1].trim().substring(1, tokens[1].length() - 2);
		}
		
		System.out.println("SIFRA KOJA JE DOSLA SA FRONTA: " + pw);
		
		LoginUser tempLog = new LoginUser();
		Map<String, Administrator> administrators = new HashMap<>();
		Map<String, Manager> managers = new HashMap<>();
		Map<String, Customer> customers = new HashMap<>();
		Map<String, Trener> treneri = new HashMap<>();
		
		
		try {
			JsonReader reader = new JsonReader(new FileReader(contextPath + "administrators.json"));
			Gson gson = new Gson();
			Administrator[] tempAdministrators = gson.fromJson(reader, Administrator[].class);
			for(Administrator c : tempAdministrators){
				administrators.put(c.getUsername(), c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(administrators.containsKey(username)){
			System.out.println("SIFRA PROCITANA IZ BAZE[ADMIN]: " + administrators.get(username).getPassword());
			if(administrators.get(username).getPassword().equals(pw)){
				tempRole = "admin";
				tempLog.setRole("admin");
				tempLog.setUsername(username);
			}
			
			return tempLog;
		}
		
		//potrazi u customers, ako nadjes prekini izvrsavanje metode
				try {
					JsonReader reader = new JsonReader(new FileReader(contextPath + "customers.json"));
					Gson gson = new Gson();
					Customer[] tempCustomers = gson.fromJson(reader, Customer[].class);
					for(Customer c : tempCustomers) {
						customers.put(c.getUsername(), c);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				if(customers.containsKey(username)) {
					System.out.println("SIFRA PROCITANA IZ BAZE[CUSTOMER]: " + customers.get(username).getPassword());
					if(customers.get(username).getPassword().equals(pw)) {
						tempRole = "customer";
						tempLog.setRole("customer");
						tempLog.setUsername(username);
					}
					
					return tempLog;
				}
				
				//potrazi u managers, ako nadjes prekini izvrsavanje metode
				try {
					JsonReader reader = new JsonReader(new FileReader(contextPath + "managers.json"));
					Gson gson = new Gson();
					Manager[] tempManagers = gson.fromJson(reader, Manager[].class);
					if(tempManagers != null) {
						for(Manager c : tempManagers) {
							managers.put(c.getUsername(), c);
						}
					}

				}catch(Exception ex) {
					ex.printStackTrace();
				}
				if(managers.containsKey(username)) {
					System.out.println("SIFRA PROCITANA IZ BAZE[MANAGER]: " + managers.get(username).getPassword());
					if(managers.get(username).getPassword().equals(pw)) {
						tempRole = "manager";
						tempLog.setRole("manager");
						tempLog.setUsername(username);
					}
					
					return tempLog;
				}
				//potrazi u deliverymen, ako nadjes prekini izvrsavanje
				try {
					JsonReader reader = new JsonReader(new FileReader(contextPath + "treneri.json"));
					Gson gson = new Gson();
					Trener[] tempTreneri = gson.fromJson(reader, Trener[].class);
					if(tempTreneri != null) {
						for(Trener dm : tempTreneri) {
							treneri.put(dm.getUsername(), dm);
						}
					}

				}catch(Exception ex) {
					ex.printStackTrace();
				}
				if(treneri.containsKey(username)) {
					System.out.println("SIFRA PROCITANA IZ BAZE[DELIVERY_MAN]: " + treneri.get(username).getPassword());
					if(treneri.get(username).getPassword().equals(pw)) {
						tempRole = "coach";
						tempLog.setRole("coach");
						tempLog.setUsername(username);
					}
					
					return tempLog;
				}
				//ako ne nadjes nista, vrati null
				return tempLog;
			}
			
		}

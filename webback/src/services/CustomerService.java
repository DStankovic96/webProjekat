package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.CustomerDAO;
//import dtos.UpdateCustomerDTO;
import model.Customer;

import java.util.ArrayList;
import java.util.Collection;

@Path("/customers")
public class CustomerService {
	
	@Context
	ServletContext ctx;
	
	public CustomerService(){
		
	}
	
	@PostConstruct
	public void init(){
		if(ctx.getAttribute("customerDAO") == null){
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customerDAO", new CustomerDAO(contextPath));
		}
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer addCustomer(Customer customer){
		
		CustomerDAO dao = (CustomerDAO)ctx.getAttribute("customerDAO");
		return dao.addNewCustomer(customer);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getCustomers(){
		System.out.println("Nalazimo se u " + ctx.toString());
		CustomerDAO dao = (CustomerDAO)ctx.getAttribute("customerDAO");
		return dao.findAllCustomers();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("username") String username) {
		System.out.println("POGODAK SA FRONTA");
		CustomerDAO dao = (CustomerDAO)ctx.getAttribute("customerDAO");
		return dao.findCustomer(username);
	}
//	@PUT
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Customer updateCustomer(UpdateCustomerDTO customer) {
//		CustomerDAO dao = (CustomerDAO)ctx.getAttribute("customerDAO");
//		if(dao.checkPassword(customer.getOldPassword(), customer.getUsername()) == true) {
//			Customer c = new Customer();
//			c.setDateOfBirth(customer.getDateOfBirth()); c.setGender(customer.getGender()); c.setLastname(customer.getLastname()); c.setName(customer.getName());
//			c.setPassword(customer.getPassword()); c.setUsername(customer.getUsername());
//			return dao.updateCustomer(c);
//		}
//		return null;
//	}

}

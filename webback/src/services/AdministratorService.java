package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.AdministratorDAO;
import dtos.UpdateAdminDTO;
//import dtos.UpdateAdminDTO;
import model.Administrator;

@Path("/administrators")
public class AdministratorService {

		@Context
		ServletContext ctx;
		
		public AdministratorService() {
			
			
		}
		
		
		@PostConstruct
		public void init() {
//			System.out.println("TESTADMIN usao u init.");
			if (ctx.getAttribute("administratorDAO") == null) {
//				System.out.println("TESTADMIN usao u initx2.");
		    	String contextPath = ctx.getRealPath("");
				ctx.setAttribute("administratorDAO", new AdministratorDAO(contextPath));
				System.out.println("TESTADMIN usao u initnovo.");
			}
		}	
		
		@GET
		@Path("/{username}")
		@Produces(MediaType.APPLICATION_JSON)
		public Administrator getAdministrator(@PathParam("username") String username){
//			System.out.println("TESTADMIN1.");
			AdministratorDAO dao = (AdministratorDAO)ctx.getAttribute("administratorDAO");
			return dao.findAdministrator(username);
		}
		
		@PUT
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public Administrator updateAdministrator(UpdateAdminDTO administrator) {
			AdministratorDAO dao = (AdministratorDAO)ctx.getAttribute("administratorDAO");
			if(dao.checkPassword(administrator.getOldPassword(), administrator.getUsername()) == true) {
//				Administrator a = new Administrator(administrator.isObrisan(), administrator.getUsername(), administrator.getPassword(), administrator.getName(), administrator.getLastname(), administrator.getGender(), administrator.getDateOfBirth());
				Administrator c = new Administrator();
				c.setDateOfBirth(administrator.getDateOfBirth()); c.setGender(administrator.getGender()); c.setLastname(administrator.getLastname()); c.setName(administrator.getName());
				c.setPassword(administrator.getPassword()); c.setUsername(administrator.getUsername());
				return dao.updateAdministrator(c);
			}
			return null;
		}
		
}

package services;

import java.util.Collection;

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

import dao.TrenerDAO;

import dtos.UpdateTrenerDTO;

import model.Trener;



@Path("/coaches")
public class TrenerService {
	@Context
	ServletContext ctx;
	
	public TrenerService() {
		
	}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("trenerDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trenerDAO", new TrenerDAO(contextPath));
		}
	}
	
	//dodavanje novog trenera u bazu
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Trener addTrener(Trener trener) {
		TrenerDAO dao = (TrenerDAO)ctx.getAttribute("trenerDAO");
		return dao.addNewTrener(trener);
	}
	
	//dobavljanje svih trenera iz baze
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trener> getTreneri(){
		System.out.println("Nalazimo se u: " + ctx.toString());
		TrenerDAO dao = (TrenerDAO)ctx.getAttribute("trenerDAO");
		return dao.findAllTreneri();
	}
	
	//dobavljanje konkretnog trenera iz baze, identifikuje se preko usernamea
		@GET
		@Path("/{username}")
		@Produces(MediaType.APPLICATION_JSON)
		public Trener getTrener(@PathParam("username") String username) {
			System.out.println("POGODAK SA FRONTA");
			TrenerDAO dao = (TrenerDAO)ctx.getAttribute("trenerDAO");
			return dao.findTrener(username);
		}
		@PUT
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public Trener updateTrener(UpdateTrenerDTO trener) {
			TrenerDAO dao = (TrenerDAO)ctx.getAttribute("trenerDAO");
			if(dao.checkPassword(trener.getOldPassword(), trener.getUsername()) == true) {
//				Trener dm = new Trener(trener.isObrisan(), trener.getUsername(), trener.getPassword(), trener.getName(), trener.getLastname(), trener.getGender(), trener.getDateOfBirth(), trener.getListOfActiveOrders(), trener.getListOfAllOrders());
				Trener c = new Trener();
				c.setDateOfBirth(trener.getDateOfBirth()); c.setGender(trener.getGender()); c.setLastname(trener.getLastname()); c.setName(trener.getName());
				c.setPassword(trener.getPassword()); c.setUsername(trener.getUsername());
				
				return dao.updateTrener(c);
			}
			return null;
		}
}

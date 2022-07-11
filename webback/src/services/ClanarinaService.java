package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ClanarinaDAO;

import model.Clanarina;


@Path("/clanarine")
public class ClanarinaService {
	
public ClanarinaService() {
		
	}
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("clanarinaDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("clanarinaDAO", new ClanarinaDAO(contextPath));
		}
	}
	
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Clanarina> findAllClanarineForCustomer(@PathParam("username") String name){
//		System.out.println("IDEMO PRVI TESTIC");
		ClanarinaDAO dao = (ClanarinaDAO)ctx.getAttribute("clanarinaDAO");
		return dao.findAllClanarineForCustomer(name);
	}
	
	
	
	@POST
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Clanarina> dodajNovuClanarinu1(@PathParam("username") String name, ArrayList<Clanarina> clanarina){
		ClanarinaDAO dao = (ClanarinaDAO)ctx.getAttribute("clanarinaDAO");
		return dao.posaljiClanarinu1(name, clanarina);
	}
	
	@PUT
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Clanarina> updateClanarinu1(@PathParam("username") String name, ArrayList<Clanarina> clanarina){
		ClanarinaDAO dao = (ClanarinaDAO)ctx.getAttribute("clanarinaDAO");
		return dao.updateClanarinu1(name, clanarina);
	}

}

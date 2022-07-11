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
import dao.IstorijaTreningaDAO;
import model.Clanarina;
import model.IstorijaTreninga;





@Path("/istorijaTreninga")
public class IstorijaTreningaService {
	
	public IstorijaTreningaService() {
		
	}
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("clanarinaDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("istorijatreningaDAO", new IstorijaTreningaDAO(contextPath));
		}
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<IstorijaTreninga> findAllIstorijeTreningaForCustomer(@PathParam("username") String name){
//		System.out.println("IDEMO PRVI TESTIC");
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO)ctx.getAttribute("istorijatreningaDAO");
		return dao.findAllIstorijaTreningaForCustomer(name);
	}
	
	
	
	@POST
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<IstorijaTreninga> dodajNovuIstorijuTreninga(@PathParam("username") String name, ArrayList<IstorijaTreninga> istorijaTreninga){
		
		IstorijaTreningaDAO dao = (IstorijaTreningaDAO)ctx.getAttribute("istorijatreningaDAO");
		System.out.println("USLO U DODAj NOVU ISTORIJU TRENINGA" + name );
		System.out.println("USLO U DODAj NOVU ISTORIJU TRENINGA" + istorijaTreninga );
		return dao.posaljiIstorijuTreninga(name, istorijaTreninga);
	}

}

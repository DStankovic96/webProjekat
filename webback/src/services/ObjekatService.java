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

import dao.ObjekatDAO;
import model.Lokacija;
import model.Objekat;
import model.Trening;

@Path("/objects")
public class ObjekatService {
	
	@Context
	ServletContext ctx;
	
	public ObjekatService(){
		
	}
	
	@PostConstruct
	public void init(){
		if(ctx.getAttribute("objekatDAO")==null){
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("objekatDAO", new ObjekatDAO(contextPath));
		}
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Objekat addObjekat(Objekat o){
		System.out.println("uslo se u POST objekat");
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.addNewObjekat(o);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Objekat> getObjekti(){
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.findAllObjekti();
	}
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Objekat getObjekat(@PathParam("name") String name){
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.findObjekat(name);
	}

//	@GET
//	@Path("/hadManagers")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Objekat> getObjectsWithManagers(){
//		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
//		return null;
//	}
	
	@PUT
	@Path("/{objectName}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Trening addTrening(@PathParam ("objectName") String objectName, Trening a){
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.addTrening(objectName, a);
	}
	
	@GET
	@Path("/bymanager/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Objekat getObjectByManager(@PathParam("username") String username) {
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.getObjekatByManager(username);
	}
	
	@GET
	@Path("/yours/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Trening> getTreninzi(@PathParam("username") String username) {
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		return dao.getTreninzi(username);
	}
	
	@GET
	@Path("/filtered/{request}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Objekat> getFilteredObjects(@PathParam("request") String request){
		ObjekatDAO dao = (ObjekatDAO)ctx.getAttribute("objekatDAO");
		String name = "";
		String lokacija = "";
		int ocena = 0;
		String tip = "";
		Objekat r = new Objekat();
		boolean tempBoolean = false;
		try {
			String[] tokens = request.split("&");
			for(String s : tokens) {
				String[] tempTokens = s.split(":");
				if(tempTokens[0].equals("name")) {
					if(!tempTokens[1].equals("_")) {
						name = tempTokens[1];
						r.setName(name);
						tempBoolean = true;
					}
				}
				else if(tempTokens[0].equals("type")) {
					if(!tempTokens[1].equals("_")) {
						tip = tempTokens[1];
						r.setType(tip);
						tempBoolean = true;
					}
					
				}
				else if(tempTokens[0].equals("lokacija")) {
					if(!tempTokens[1].equals("_")) {
						lokacija = tempTokens[1];
						Lokacija l = new Lokacija("","", "", lokacija);
						r.setLokacija(l);
						tempBoolean = true;
					}
					
				}
				else if(tempTokens[0].equals("ocena")) {
					if(!tempTokens[1].equals("_")) {
						ocena = Integer.parseInt(tempTokens[1]);
						r.setOcena(ocena);
						tempBoolean = true;
					}
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dao.findFilteredResults(r);
//		System.out.println("NAZIV TRAZENOG OBJEKTA JE: " + r.getName());
//		if(tempBoolean == true) {
//			
//		}
//			return null;		
	}
}

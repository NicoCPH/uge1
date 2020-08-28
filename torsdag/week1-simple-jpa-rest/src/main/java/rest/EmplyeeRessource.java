package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("employee")
public class EmplyeeRessource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    static EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

   @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeeById(@PathParam("id")Long id) {
         return Response.ok().entity(gson.toJson(facade.findEmployee(id))).build();
    }
    
@Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyees() {
         return Response.ok().entity(gson.toJson(facade.getAllEmployees())).build();
    }
    @Path("/hpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response highestpaid() {
         return Response.ok().entity(gson.toJson(facade.findhHigstSalary())).build();
    }
    @Path("/name{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeesByName(@PathParam("name")String name) {
         return Response.ok().entity(gson.toJson(facade.findEmploye(name))).build();
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.Facade;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("web")
public class WebResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/Exam2020",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final Facade FACADE =  Facade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
        
        
    }
    
    public static void main(String[] args) {
        System.out.println(FACADE.getAllSignedUpByStudentId(3).get(0).getGrade());
    }
    
    

 
}

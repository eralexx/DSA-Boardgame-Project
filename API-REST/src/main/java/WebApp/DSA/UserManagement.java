package WebApp.DSA;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("/UserManagement")
public class UserManagement {

    @GET
    @Path("/Register;{Email}:{Username};{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTrack(@PathParam("Email") String Email, @PathParam("Username") String Username,@PathParam("Email") String Password) {
        try {

            //Check if user exists, if email is valid etc...

            return "User Added to Database";
        }
        catch(Exception ex){

            return "Unable to add User to database.";
        }
    }

    @GET
    @Path("/Login")
    @Produces(MediaType.TEXT_HTML)
    public String Test() {
        try {
            return "Aqui deberia devolver el HTML del formulario";
        }
        catch(Exception ex){

            return "Unable to add User to database.";
        }
    }



}
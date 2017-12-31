package WebApp.DSA;

import Model.Classes.User;
import Model.Classes.UserLists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/UserManagement")
public class UserManagement {

    UserLists lists;

    //Endpoints Rest para el logeo, registro y control de sesion de los users
    @GET
    @Path("/Register?{Email}:{Username};{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public int getTrack(@PathParam("Email") String Email, @PathParam("Username") String Username,@PathParam("Email") String Password) {
        try {

            if (lists.IsRegisteredByEmail(Email)){
                return -1;
            }
            else {
                User newUser= new User(Email, Username, Password);
                lists.AddRegisteredUser(newUser);
                lists.IsRegisteredByEmail(newUser.getEmail());
                return 0;
            }
        }
        catch(Exception ex){
            return -1;
        }
    }

    @GET
    @Path("/GetRegisteredUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> GetRegisteredUsers() {
        try {
            return lists.getRegisteredUsers();
        }
        catch(Exception ex){
            return null;
        }
    }

    @GET
    @Path("/GetOnlineUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> GetOnlineUsers() {
        try {
            return lists.getRegisteredUsers();
        }
        catch(Exception ex){
            return null;
        }
    }
    @GET
    @Path("/Login?{EmailOrUsername};{Password}")
    @Produces(MediaType.TEXT_HTML)
    public int  Login(@PathParam("EmailOrUsername") String EmailOrUsername, @PathParam("Password") String Password) {
        try {
           if (lists.CheckLogin(EmailOrUsername, Password)) {
               return 0;
           }
           else return -1;
        }
        catch(Exception ex)
        {
            return -1;
        }
    }





}
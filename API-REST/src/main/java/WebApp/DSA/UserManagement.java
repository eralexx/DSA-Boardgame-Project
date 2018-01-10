package WebApp.DSA;

import Model.Classes.User;
import Model.Classes.UserLists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/UserManagement")
public class UserManagement {

    private static List<User> registeredUsers = UserLists.RegisteredUsers;
    private static List<User> onlineUsers = UserLists.OnlineUsers;

    @GET
    @Path("/InitUserData")
    @Produces(MediaType.APPLICATION_JSON)
    public void InitializeSomeData(){
        User user1= new User("Alex", "Alex@hahaha.com", "1234");
        User user2= new User("Daniel", "Daniel@hahaha.com", "1234");
        User user3= new User("Daniel", "Antonio@hahaha.com", "1234");
        User user4= new User("test", "test@test.com", "test");
        this.AddRegisteredUser(user1);
        this.AddRegisteredUser(user2);
        this.AddRegisteredUser(user3);
        this.AddRegisteredUser(user4);
    }
    //Endpoints Rest para el logeo, registro y control de sesion de los users
    @GET
    @Path("/Register/{Email}/{Username}/{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public int registerUser(@PathParam("Email") String Email, @PathParam("Username") String Username,@PathParam("Email") String Password) {
        try {
            if (this.IsRegisteredByEmail(Email)){
                return -1;
            }
            else {
                User newUser= new User(Email, Username, Password);
                this.AddRegisteredUser(newUser);
                this.IsRegisteredByEmail(newUser.getEmail());
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
            return registeredUsers;
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
            return registeredUsers;
        }
        catch(Exception ex){
            return null;
        }
    }
    @GET
    @Path("/Login/{EmailOrUsername}/{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Login(@PathParam("EmailOrUsername") String EmailOrUsername, @PathParam("Password") String Password) {
        try {
           if (this.CheckLogin(EmailOrUsername, Password)) {
               return Response.ok()
                       .entity(0)
                       .header("Access-Control-Allow-Origin", "*")
                       .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                       .allow("OPTIONS").build();
           }
           else return Response.ok()
                   .entity(-1)
                   .header("Access-Control-Allow-Origin", "*")
                   .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                   .allow("OPTIONS").build();
        }
        catch(Exception ex)
        {
            return Response.ok()
                    .entity(-1)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }
    public  void AddRegisteredUser(User newUser){
        if (this.registeredUsers==null){
            this.registeredUsers= new ArrayList<>();
        }
        this.registeredUsers.add(newUser);

    }

    public boolean IsRegisteredByEmail(String Email){
        for(int i=0; i<registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getEmail()==Email){
                return true;
            }
        }
        return false;
    }
    public boolean CheckLogin(String Email, String Password){
        for(int i=0; i<registeredUsers.size(); i++) {
            if (registeredUsers.get(i).getEmail().equals(Email) && registeredUsers.get(i).getPassword().equals(Password)){
                return true;
            }
        }
        return false;
    }




}
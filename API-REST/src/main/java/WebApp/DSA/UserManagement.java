package WebApp.DSA;

import Model.Classes.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/UserManagement")
public class UserManagement {
    static Information info= Information.getInstance();
    private static List<User> registeredUsers = info.getUserLists().getRegisteredUsers();
    private static List<User> onlineUsers = info.getUserLists().getOnlineUsers();
    MySQLAccess DB = new MySQLAccess();


    @GET
    @Path("/Register/{Email}/{Username}/{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response registerUser(@PathParam("Username") String Email, @PathParam("Email") String Username,@PathParam("Email") String Password) {
        try {

            if (this.IsRegisteredByEmail(Email)){
                return Response.ok() //200
                        .entity(-1)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS").build();
            }
            else {

                User newUser= new User(Email, Username, Password);
                this.AddRegisteredUser(newUser);
                int id =DB.insertUserIntoDB(newUser);
                newUser.setId(id);

                this.IsRegisteredByEmail(newUser.getEmail());
                return Response.ok() //200
                        .entity(0)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS").build();
            }
        }
        catch(Exception ex){
            return Response.ok() //200
                    .entity(-1)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @GET
    @Path("/Test")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response test() {
        try {
            return Response.ok() //200
                    .entity(0)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            return Response.ok() //200
                    .entity(-1)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @GET
    @Path("/GetRegisteredUsers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/GetUserInfo/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetUserInfo(@PathParam("Email") String UserEmail) {
        try {

            User output = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();

            return   Response.ok() //200
                    .entity(output)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @GET
    @Path("/GetUserGames/{Email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response GetUserGames(@PathParam("Email") String UserEmail) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();

            List<Game> games = info.getGamesFinished().stream()
                    .filter(item -> item.getPlayers().contains(user))
                    .collect(Collectors.toList());
            List<String> gamesString= new ArrayList<>();
            for(Game game : games){
                gamesString.add(game.toString());
            }
            return   Response.ok() //200
                    .entity(String.join(";", gamesString))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/GetUserWins/{Email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response GetUserWins(@PathParam("Email") String UserEmail) {
        try {
            List<Game> games = info.getGamesFinished().stream()
                    .filter(item -> item.getWinner().equals(UserEmail))
                    .collect(Collectors.toList());
            List<String> gamesString= new ArrayList<>();
            for(Game game : games){
                gamesString.add(game.toString());
            }
            return   Response.ok() //200
                    .entity(String.join(";", gamesString))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/ChangePassword/{Email}/{Password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ChangePassword(@PathParam("Email") String UserEmail, @PathParam("Password") String Password) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            user.setPassword(Password);
            DB.updateField(user,"password",Password);
            return
                    Response.ok() //200
                            .entity(0)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS").build();
        }
        catch(Exception ex){
            return Response.ok() //200
                    .entity(-1)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @GET
    @Path("/ChangeImage/{Email}/{imageUrl}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ChangeImage(@PathParam("Email") String UserEmail, @PathParam("imageUrl") String imageUrl) {
        try {
            imageUrl = URLDecoder.decode(imageUrl, "UTF-8");
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            user.setImagePath(imageUrl);
            DB.updateField(user, "Imagepath",imageUrl );
            return
                    Response.ok() //200
                            .entity(0)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS").build();
        }
        catch(Exception ex){
            return Response.ok() //200
                    .entity(-1)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
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
    private void AddRegisteredUser(User newUser){
        if (this.registeredUsers==null){
            this.registeredUsers= new ArrayList<>();
        }
        this.registeredUsers.add(newUser);

    }

    private boolean IsRegisteredByEmail(String Email){
        for (User registeredUser : registeredUsers) {
            if (registeredUser.getEmail().equals( Email)) {
                return true;
            }
        }
        return false;
    }
    private boolean CheckLogin(String Email, String Password){

        for (User registeredUser : registeredUsers) {
            if (registeredUser.getEmail().equals(Email) && registeredUser.getPassword().equals(Password)) {
                return true;
            }
        }
        return false;
    }




}
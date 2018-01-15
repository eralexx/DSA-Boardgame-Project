package WebApp.DSA;

import Model.Classes.Game;
import Model.Classes.Information;
import Model.Classes.User;
import Model.Classes.UserLists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/UserManagement")
public class UserManagement {
    static Information info= Information.getInstance();
    private static List<User> registeredUsers = info.getUserLists().getRegisteredUsers();
    private static List<User> onlineUsers = info.getUserLists().getOnlineUsers();

    @GET
    @Path("/InitUserData")
    @Produces(MediaType.APPLICATION_JSON)
    public void InitializeSomeData(){
        try {
            User user1 = new User("Alex", "Alex@hahaha.com", "1234");
            User user2 = new User("Daniel", "Daniel@hahaha.com", "1234");
            User user3 = new User("Antonio", "Antonio@hahaha.com", "1234");
            User user4 = new User("test", "test@test.com", "test");
            this.AddRegisteredUser(user1);
            this.AddRegisteredUser(user2);
            this.AddRegisteredUser(user3);
            this.AddRegisteredUser(user4);
            List<Game> finishedGames = new ArrayList<>();
            Game game1 = new Game(this.GetRegisteredUsers());
            Game game2 = new Game(this.GetRegisteredUsers());
            finishedGames.add(game2);
            finishedGames.add(game1);
            game2.setWinner(user4.getEmail());
            game1.setWinner(user1.getEmail());
            this.info.setGamesFinished(finishedGames);
            System.out.println("Data initialized.");
        }
        catch(Exception ex){
            throw ex;
        }
    }

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response ChangePassword(@PathParam("Email") String UserEmail, @PathParam("Password") String Password) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            user.setPassword(Password);
            return
                    Response.ok() //200
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @POST
    @Path("/ChangeImage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ChangeImage(User userIn) {
        try {
            String UserEmail= userIn.getEmail();
            String ImageSource = userIn.getImagePath();
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            user.setImagePath(ImageSource);
            return
                    Response.ok() //200
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS").build();
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
    private void AddRegisteredUser(User newUser){
        if (this.registeredUsers==null){
            this.registeredUsers= new ArrayList<>();
        }
        this.registeredUsers.add(newUser);

    }

    private boolean IsRegisteredByEmail(String Email){
        for (User registeredUser : registeredUsers) {
            if (registeredUser.getEmail() == Email) {
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
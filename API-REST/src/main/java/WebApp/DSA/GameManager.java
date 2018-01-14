package WebApp.DSA;


import Model.Classes.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/Game")
public class GameManager {

    private Information info= Information.getInstance();

    @GET
    @Path("/GetRandomGame")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRandomGame() {
        try {
            List<User> asd= new ArrayList<>();
            asd.add(info.getUserLists().getRegisteredUsers().get(0));
            asd.add(info.getUserLists().getRegisteredUsers().get(1));
            Game sampleGame= new Game(asd);

            return Response.ok() //200
                    .entity(sampleGame)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @GET
    @Path("/GetRandomBoard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRandomBoard() {
        try {
            Board newBoard = new Board(info.getUserLists().getRegisteredUsers());
            return Response.ok() //200
                    .entity(newBoard)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @GET
    @Path("/GetRandomCell")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRandomCell() {
        try {
            Cell newCell =  new Cell(0,1, 2, 1);
            return Response.ok() //200
                    .entity(newCell)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/JoinQueue/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response JoinQueue(@PathParam("userEmail") String UserEmail ) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            this.info.getQueueManager().UserJoinQueue(user);
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/AttemptToGetGame/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AttemptToGetGame(@PathParam("userEmail") String UserEmail ) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();

            Game newGame = this.info.getQueueManager().getGame(user);

            return Response.ok()
                .entity(newGame)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
        }
        catch(Exception ex){
            return Response.ok()
                    .entity(null)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }
    @GET
    @Path("/DestroyGame/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DestroyGame(@PathParam("userEmail") String UserEmail ) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            Game finishedGame =  info.getQueueManager().getGame(user);

            this.info.getGamesFinished().add(finishedGame);
            this.info.getQueueManager().resetGame(user);

            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @GET
    @Path("/Move/{userEmail}/{Move}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Move(@PathParam("userEmail") String UserEmail,@PathParam("Move") Character move ) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();

            Game newGame = this.info.getQueueManager().getGame(user);
            newGame.Move(move,user);
            return Response.ok()
                    .entity(newGame)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
}

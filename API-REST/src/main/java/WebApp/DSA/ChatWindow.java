package WebApp.DSA;

import Model.Classes.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/ChatWindow")
public class ChatWindow {

     Information info= Information.getInstance();

    @GET
    @Path("/AddMessageTest")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String AddMessageTest() {
        try {
            String input= "Lorem Ipsum";
            User nullUser = new User("NullUser", "NullUser@gmail.com", "null");
            Message newMessage= new Message(input, nullUser);
            Info.chatInfo.AddMessage(newMessage.toString());
            return "ok";
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/AddMessage/{userEmail}/{input}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response AddMessage(@PathParam("userEmail") String UserEmail ,@PathParam("input") String input) {
        try {
            User user = UserLists.RegisteredUsers.stream()
                    .filter(item -> item.getEmail().equals(UserEmail))
                    .findFirst().get();
            //User nullUser = new User("NullUser", "NullUser@gmail.com", "null");

                //user = nullUser;

            Message newMessage= new Message(input, user);
            info.getChat().AddMessage(newMessage.toString());
            return Response.ok() //200
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/GetAllMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAllMessages() {
        try {
            return Response.ok() //200
                .entity(info.getChat())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
}

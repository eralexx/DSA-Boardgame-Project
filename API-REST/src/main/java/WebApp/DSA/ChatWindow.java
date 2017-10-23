package WebApp.DSA;

import Model.Classes.Chat;
import Model.Classes.Info;
import Model.Classes.Message;
import Model.Classes.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/ChatWindow")
public class ChatWindow {

    private Chat chat= Info.chatInfo;
    private Message MessageTest;

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
    @Path("/AddMessage/{userId}/{input}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response AddMessage(@PathParam("userId") int idUser ,@PathParam("input") String input) {
        try {
            User user = new User("NullUser", "NullUser@gmail.com", "null");
            User nullUser = new User("NullUser", "NullUser@gmail.com", "null");
            if (idUser==0) {
                user = nullUser;
            }
            Message newMessage= new Message(input, user);
            Info.chatInfo.AddMessage(newMessage.toString());
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
                .entity(chat)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
        }
        catch(Exception ex){
            throw ex;
        }
    }
}

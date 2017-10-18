package WebApp.DSA;

import Model.Classes.Chat;
import Model.Classes.Info;
import Model.Classes.Message;
import Model.Classes.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public void AddMessage(@PathParam("userId") int idUser ,@PathParam("input") String input) {
        try {
            User user = new User("NullUser", "NullUser@gmail.com", "null");
            User nullUser = new User("NullUser", "NullUser@gmail.com", "null");
            if (idUser==0) {
                user = nullUser;
            }
            Message newMessage= new Message(input, user);
            Info.chatInfo.AddMessage(newMessage.toString());
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @GET
    @Path("/GetAllMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Chat GetAllMessages() {
        try {
            return chat;
        }
        catch(Exception ex){
            throw ex;
        }
    }
}

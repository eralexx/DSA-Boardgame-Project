package WebApp.DSA;

import Model.Classes.Chat;
import Model.Classes.Message;
import Model.Classes.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/ChatWindow")
public class ChatWindow {

    private Chat chat;

    public ChatWindow() {
        try {
            chat = new Chat();
            User newUser = new User("admin", "admin@gmail.com", "admin");
            Message newMessage1 = new Message("hello world1", newUser);
            Message newMessage2 = new Message("hello world2", newUser);
            chat.AddMessage(newMessage1);
            chat.AddMessage(newMessage2);
        }
        catch(Exception ex){
            throw ex;
        }
    }

    @GET
    @Path("/GetAllMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Chat GetOnlineUsers() {
        try {
            return chat;
        }
        catch(Exception ex){
            throw ex;
        }
    }
}
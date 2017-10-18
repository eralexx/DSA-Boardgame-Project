package Model.Classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
     String Time;
     String Content;
     User User;

     public String getTime(){
         return this.Time;

     }
    public String getContent(){
        return this.Content;

    }
    public User getUser(){
        return this.User;

    }


    public Message(String Content, User User) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.now();
        this.Time = dtf.format(localDate);
        this.Content = Content;
        this.User = User;
    }
}

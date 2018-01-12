package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int Id = 0;
    private String UserName = "null";
    private String Email = "null";
    private String Password = "null";
    private List<Game> GamesPlayed = new ArrayList<>(10);
    private List<Game> GamesWon = new ArrayList<>(10);

    public List<Game> getGamesPlayed() {
        return GamesPlayed;
    }

    public void setGamesPlayed(List<Game> gamesPlayed) {
        GamesPlayed = gamesPlayed;
    }

    public List<Game> getGamesWon() {
        return GamesWon;
    }

    public void setGamesWon(List<Game> gamesWon) {
        GamesWon = gamesWon;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User (){

    }
    public User(String UserName, String Email, String Password){
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
    }



}

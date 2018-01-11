package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    int Id;
    String UserName;
    String Email;
    String Password;
    List<Game> GamesPlayed=new ArrayList<>();
    List<Game> GamesWon=new ArrayList<>();


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




    public User(){}
    public User(int Id, String UserName, String Email, String Password){
        this.Id=Id;
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
    }
    public User(String UserName, String Email, String Password){
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
    }

    public User(int id, String userName, String email, String password, List<Game> gamesPlayed, List<Game> gamesWon) {
        Id = id;
        UserName = userName;
        Email = email;
        Password = password;
        GamesPlayed = gamesPlayed;
        GamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return "Username= "+ UserName;
    }
    //End of Constructors
}

package Model.Classes;


import java.util.List;


public class User {

    //Start of Attributes

     int Id;
     String UserName;
     String Email;
     String Password;
     int Vida;
     List<Item> Items;
     List<Game> GamesPlayed;
     List<Game> GamesWon;


    //End of Attributes
    //Start of Getters And Setters

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

    public int getVida() {
        return Vida;
    }

    public void setVida(int vida) {
        Vida = vida;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }


    //End of Getters And Setters
    //Start of Constructors

    public User(int Id, String UserName, String Email, String Password, List<Item> Items){
        this.Id=Id;
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
        this.Items=Items;
    }
    public User(String UserName, String Email, String Password){
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
    }

    //End of Constructors
}

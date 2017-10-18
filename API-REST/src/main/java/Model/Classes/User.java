package Model.Classes;

import java.util.List;

public class User {

    //Start of Attributes

     int Id;
     String UserName;
     String Email;
     String Password;
     int Nivel;
     int Ataque;
     int Defensa;
     int Vida;
     List<Item> Items;


    //End of Attributes
    //Start of Getters And Setters

    public  int getIdetId(){
        return this.Id;
    }
    public  String getetUserName(){
        return this.UserName;
    }
    public  String getEmail(){
        return this.Email;
    }
    public  String getPassword(){
        return this.Password;
    }
    public  int getNivel(){
        return this.Nivel;
    }
    public  int getAtaque(){
        return this.Ataque;
    }
    public  int getDefensa(){
        return this.Defensa;
    }
    public  int getVida(){
        return this.Vida;
    }

    public  List<Item> getItems(){
        return this.Items;
    }
    public void setId(int Id){
        this.Id= Id;
    }
    public void setUserName(String UserName){
        this.UserName= UserName;
    }
    public void setEmail(String Password){
        this.Password= Password;
    }
    public void setNivel(int Nivel){
        this.Nivel= Nivel;
    }
    public void setAtaque(int Ataque){
        this.Ataque= Ataque;
    }
    public void SetDefensa(int Defensa){
        this.Defensa= Defensa;
    }
    public void SetVida(int Vida){
        this.Vida= Vida;
    }
    public void SetPassword( List<Item> Items){
        this.Items= Items;
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

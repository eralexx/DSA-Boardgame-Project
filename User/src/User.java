import java.util.List;

public class User {

    //Start of Attributes

    private int Id;
    private String UserName;
    private String Email;
    private String Password;
    private int Nivel;
    private int Ataque;
    private int Defensa;
    private int Vida;
    private List<Item> Items;


    //End of Attributes
    //Start of Getters And Setters

    public  int GetId(){
        return this.Id;
    }
    public  String GetUserName(){
        return this.UserName;
    }
    public  String GetEmail(){
        return this.Email;
    }
    public  String GetPassword(){
        return this.Password;
    }
    public  int GetNivel(){
        return this.Nivel;
    }
    public  int GetAtaque(){
        return this.Ataque;
    }
    public  int GetDefensa(){
        return this.Defensa;
    }
    public  int GetVida(){
        return this.Vida;
    }

    public  List<Item> GetItems(){
        return this.Items;
    }
    public void SetId(int Id){
        this.Id= Id;
    }
    public void SetUserName(String UserName){
        this.UserName= UserName;
    }
    public void SetEmail(String Password){
        this.Password= Password;
    }
    public void SetNivel(int Nivel){
        this.Nivel= Nivel;
    }
    public void SetAtaque(int Ataque){
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

    //End of Constructors
}

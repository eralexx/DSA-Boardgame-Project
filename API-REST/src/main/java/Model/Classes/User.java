package Model.Classes;

public class User {

    private int Id = 0;
    private String UserName = "null";
    private String Email = "null";
    private String Password = "null";
    private int GamesPlayed = 0;
    private int GamesWon = 0;
    private String imagePath ="";

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getGamesPlayed() {
        return GamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        GamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return GamesWon;
    }

    public void setGamesWon(int gamesWon) {
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

    public User(int id, String userName, String email, String password, int gamesPlayed, int gamesWon, String imagePath) {
        Id = id;
        UserName = userName;
        Email = email;
        Password = password;
        GamesPlayed = gamesPlayed;
        GamesWon = gamesWon;
        this.imagePath = imagePath;
    }

    public User (){

    }
    public User(String UserName, String Email, String Password){
        this.UserName=UserName;
        this.Email=Email;
        this.Password=Password;
        this.imagePath="http://www.free-icons-download.net/images/anonymous-user-icon-80332.png";
    }
}

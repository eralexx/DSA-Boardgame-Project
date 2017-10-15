package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class UserLists {
    private List<User> RegisteredUsers = new ArrayList<User>();
    private List<User> OnlineUsers = new ArrayList<User>();


    public List<User> getRegisteredUsers(){
        return this.RegisteredUsers;
    }

    public List<User> getOnlineUsers(){
        return this.OnlineUsers;
    }

    public  void AddRegisteredUser(User newUser){
        this.RegisteredUsers.add(newUser);

    }

    public boolean IsRegisteredByEmail(String Email){
        for(int i=0; i<RegisteredUsers.size(); i++) {
            if (RegisteredUsers.get(i).GetEmail()==Email){
                return true;
            }
        }
        return false;
    }
    public boolean CheckLogin(String Email, String Password){
        for(int i=0; i<RegisteredUsers.size(); i++) {
            if (RegisteredUsers.get(i).GetEmail()==Email && RegisteredUsers.get(i).GetPassword()==Password){
                return true;
            }
        }
        return false;
    }

}

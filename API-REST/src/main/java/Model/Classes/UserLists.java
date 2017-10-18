package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class UserLists {
     List<User> RegisteredUsers = new ArrayList<User>();
     List<User> OnlineUsers = new ArrayList<User>();


    public void setRegisteredUsers(List<User> registeredUsers) {
        RegisteredUsers = registeredUsers;
    }

    public void setOnlineUsers(List<User> onlineUsers) {
        OnlineUsers = onlineUsers;
    }

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
            if (RegisteredUsers.get(i).getEmail()==Email){
                return true;
            }
        }
        return false;
    }
    public boolean CheckLogin(String Email, String Password){
        for(int i=0; i<RegisteredUsers.size(); i++) {
            if (RegisteredUsers.get(i).getEmail()==Email && RegisteredUsers.get(i).getPassword()==Password){
                return true;
            }
        }
        return false;
    }

}

package Model.Classes;

public class Information {
    private static Information ourInstance = new Information();

    private Chat chat= new Chat();
    private UserLists userLists = new UserLists();

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UserLists getUserLists() {
        return userLists;
    }

    public void setUserLists(UserLists userLists) {
        this.userLists = userLists;
    }

    public static Information getInstance() {
        return ourInstance;
    }

    private Information() {

    }
}

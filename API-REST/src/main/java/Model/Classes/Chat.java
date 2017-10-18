package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private List<Message> Messages = new ArrayList<Message>(20);

    public void AddMessage(Message NewMessage) {
        try {
            if (NewMessage != null)
                Messages.add(NewMessage);
        } catch (Exception ex) {
            throw ex;
        }
    }
    public void setMessages(List<Message> Messages){
        this.Messages= Messages;
    }
    public List<Message> getMessages(){
            return this.Messages;

    }
    private void ShiftRight(Message newMessage) {
        try {
            for (int i = 19; i > 0; i--) {
                if (Messages.get(i-1) !=null) {
                    Messages.set(i, Messages.get(i - 1));
                }
            }
            Messages.set(0, newMessage);
        }
        catch(Exception ex){
            throw ex;
        }
    }
    @Override
    public String toString() {
        return "Chat [Messages=" + Messages+"]";
    }


}

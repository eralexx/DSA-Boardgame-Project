package Model.Classes;

import java.util.*;

public class QueueManager extends TimerTask {

    private Queue<User> UsersInQueue;
    int Delay;

    public void UserJoinQeue(User player){
        if (this.UsersInQueue ==null){
            this.UsersInQueue=  new PriorityQueue<>();
        }
        this.UsersInQueue.add(player);
    }
    public void run(){
        int Nqueue= this.UsersInQueue.size();
        List<User> players = new ArrayList<>();
        switch(Nqueue){
            case 2:
                User p1= this.UsersInQueue.remove();
                User p2= this.UsersInQueue.remove();
                players.add(p1);
                players.add(p2);
                break;
            case 3:
                p1= this.UsersInQueue.remove();
                p2= this.UsersInQueue.remove();
                User p3= this.UsersInQueue.remove();
                players.add(p1);
                players.add(p2);
                players.add(p3);
                break;
            case 4:
                p1= this.UsersInQueue.remove();
                p2= this.UsersInQueue.remove();
                p3= this.UsersInQueue.remove();
                User p4= this.UsersInQueue.remove();
                players.add(p1);
                players.add(p2);
                players.add(p3);
                players.add(p4);
                break;
        }
        if (Nqueue >4){
            User p1= this.UsersInQueue.remove();
            User p2= this.UsersInQueue.remove();
            User p3= this.UsersInQueue.remove();
            User p4= this.UsersInQueue.remove();
            players.add(p1);
            players.add(p2);
            players.add(p3);
            players.add(p4);
        }
        Game newGame = new Game(players);
    }
}


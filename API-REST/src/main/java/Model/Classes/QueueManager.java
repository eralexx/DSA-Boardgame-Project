package Model.Classes;

import java.util.*;

public class QueueManager extends TimerTask {

    private Queue<User> UsersInQueue;
    int Delay;
    static Information info= Information.getInstance();
    List<Game> games = new ArrayList<>();
    public int getDelay() {
        return Delay;
    }

    public Game getGame(User user) {
        Game game = this.games.stream()
                .filter(item -> item.getPlayers().contains(user))
                .findFirst().get();
        return game;
    }

    public void setDelay(int delay) {
        Delay = delay;
    }

    public void UserJoinQueue(User player){
        if (this.UsersInQueue ==null){
            this.UsersInQueue=  new PriorityQueue<>();
        }
        if (this.UsersInQueue.contains(player)==false) {
            this.UsersInQueue.add(player);
        }
    }
    public void run(){
        if (this.UsersInQueue== null){
            this.UsersInQueue= new ArrayDeque<>();
        }
        int Nqueue= this.UsersInQueue.size();
        List<User> players = new ArrayList<>();
        switch(Nqueue){
            case 2:
                User p1= this.UsersInQueue.remove();
                User p2= this.UsersInQueue.remove();
                players.add(p1);
                players.add(p2);
                Game game  = new Game(players);
                this.games.add(game);
                this.UsersInQueue.remove(p1);
                this.UsersInQueue.remove(p2);
                break;
            case 3:
                p1= this.UsersInQueue.remove();
                p2= this.UsersInQueue.remove();
                User p3= this.UsersInQueue.remove();
                players.add(p1);
                players.add(p2);
                players.add(p3);
                game = new Game(players);
                this.games.add(game);
                this.UsersInQueue.remove(p1);
                this.UsersInQueue.remove(p2);
                this.UsersInQueue.remove(p3);
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
                game = new Game(players);
                this.games.add(game);
                this.UsersInQueue.remove(p1);
                this.UsersInQueue.remove(p2);
                this.UsersInQueue.remove(p3);
                this.UsersInQueue.remove(p4);
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
            Game game = new Game(players);
            this.games.add(game);
        }
    }
    public void addGame(Game game){
        this.games.add(game);
    }

    public void resetGame(User winner) {
        Game game = this.games.stream()
                .filter(item -> item.getPlayers().contains(winner))
                .findFirst().get();
        this.games.remove(game);
    }

    public void removeUserFromQueue(User iuser) {
        this.UsersInQueue.remove(iuser);
    }
}


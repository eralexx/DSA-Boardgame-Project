package Model.Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Game {

    private int Id;
    private int nPlayers;
    private User PlayerTurn;
    private List<User> Players;
    private User Winner;
    private String timeStamp ;
    private Board Board;

    public Game( List<User> Players){
        this.Players= Players;
        this.nPlayers= Players.size();
        this.timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(Calendar.getInstance().getTime());
        this.Board = new Board(Players);
        this.PlayerTurn=this.Players.get(0);
        for(User user: Players){
            user.GamesPlayed.add(this);
        }
    }

    public boolean Move(char Direction , User player ){
        if (CheckMovementPosible(Direction , player)){
            boolean moved= false;
            while (moved==false) {
                switch (Direction) {
                    case 'S':
                        this.Board.MoveSouth(player);
                        moved=true;
                        break;
                    case 'N':
                        this.Board.MoveNorth(player);
                        moved=true;
                        break;
                    case 'E':
                        this.Board.MoveEast(player);
                        moved=true;
                        break;
                    case 'W':
                        this.Board.MoveWest(player);
                        moved=true;
                        break;
                }
            }
            NextTurn();
            return true;
        }
        else return false;
    }

    private boolean CheckMovementPosible(char Direction , User player ){
        if (this.Board.checkPosibleMoves(player) !=null){
            if (this.Board.checkPosibleMoves(player).contains(Direction) && this.PlayerTurn==player){
                return true;
            }
        }
        return false;
    }

    private void NextTurn(){
        for(int i=0; i< this.Players.size(); i++){
            if (this.Players.get(i) == this.PlayerTurn){
                if (i==this.Players.size())
                    this.PlayerTurn=this.Players.get(0);
                else{
                    this.PlayerTurn=this.Players.get(i+1);
                }
            }
        }
    }

}

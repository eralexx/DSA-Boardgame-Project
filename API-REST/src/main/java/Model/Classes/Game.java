package Model.Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            if(user.GamesPlayed==null) {
                user.GamesPlayed = new ArrayList<>();
            }
            user.GamesPlayed.add(this);
        }
    }

    public boolean Move(char Direction , User player ){
        boolean moved= false;
        if (CheckMovementPosible(Direction , player)){
            while (moved==false) {
                switch (Direction) {
                    case 'S':
                        this.Board.MoveSouth(player);
                        moved=true;
                        if (!isGameWon())
                            NextTurn();
                        break;
                    case 'N':
                        this.Board.MoveNorth(player);
                        moved=true;
                        if (!isGameWon())
                            NextTurn();
                        break;
                    case 'E':
                        this.Board.MoveEast(player);
                        moved=true;
                        if (!isGameWon())
                            NextTurn();
                        break;
                    case 'W':
                        this.Board.MoveWest(player);
                        moved=true;
                        if (!isGameWon())
                            NextTurn();
                        break;
                }
            }
        }
        return moved;
    }
    private boolean isGameWon(){
     Cell EndCell=this.Board.getWinningCell();
     Cell CurrentCell=this.Board.getPlayerPosition(this.PlayerTurn); //Esto no se actualiza
     if (EndCell==CurrentCell){
         this.Winner=PlayerTurn;
         if ( this.PlayerTurn.GamesWon==null){
             this.PlayerTurn.GamesWon= new ArrayList<>();
         }
         this.PlayerTurn.GamesWon.add(this);
         System.out.println("Player "+this.PlayerTurn.UserName + "has won the game.");
         return true;
     }
     else {
         return false;
     }
    }
    private void EndGame(){

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
                if (i==this.Players.size()-1) {
                    this.PlayerTurn = this.Players.get(0);
                    return;
                }
                else{
                    this.PlayerTurn=this.Players.get(i+1);
                    return;
                }
            }
        }
    }

}

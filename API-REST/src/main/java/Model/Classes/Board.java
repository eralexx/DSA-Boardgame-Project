package Model.Classes;

import java.util.List;
import java.util.Random;

public class Board {

    private int Id;
    private int sizeY;
    private int sizeX;
    private List<User> Users;
    private Cell[][] Cells;
    private List<Cell> Positions;

     public int getId() {
          return Id;
     }

     public void setId(int id) {
          Id = id;
     }

     public List<User> getUsers() {
          return Users;
     }

     public void setUsers(List<User> users) {
          Users = users;
     }

     public Cell[][] getCells() {
          return Cells;
     }

     public void setCells(Cell[][] cells) {
          Cells = cells;
     }

     public List<Character> checkPosibleMoves(User player){
         for (int i=0; i<this.Users.size();i++){
            if (this.Users.get(i)== player);
            return Positions.get(i).Moves;
         }
         return null;
     }
    public void MoveSouth(User player){
        for (int i=0; i<this.Users.size();i++){
            if (this.Users.get(i)== player);
             this.Positions.get(i).PosX++;
        }
    }
    public void MoveNorth(User player){
        for (int i=0; i<this.Users.size();i++){
            if (this.Users.get(i)== player);
            this.Positions.get(i).PosX--;
        }
    }
    public void MoveEast(User player){
        for (int i=0; i<this.Users.size();i++){
            if (this.Users.get(i)== player);
            this.Positions.get(i).PosY++;
        }
    }
    public void MoveWest(User player){
        for (int i=0; i<this.Users.size();i++){
            if (this.Users.get(i)== player);
            this.Positions.get(i).PosY--;
        }
    }
     public boolean canMoveWest(int x, int y) {
         if (x>0 && x<this.sizeX & y>0 & y<this.sizeY){
             return this.Cells[x][y].Moves.contains('W');
         }
         else return false;
     }
     public boolean canMoveEast(int x, int y) {
         if (x>0 && x<this.sizeX & y>0 & y<this.sizeY){
             return this.Cells[x][y].Moves.contains('E');
         }
         else return false;
     }
     public boolean canMoveNorth(int x, int y) {
         if (x>0 && x<this.sizeX & y>0 & y<this.sizeY){
             return this.Cells[x][y].Moves.contains('N');
         }
         else return false;
     }
     public boolean canMoveSouth(int x, int y) {
         if (x>0 && x<this.sizeX & y>0 & y<this.sizeY){
             return this.Cells[x][y].Moves.contains('S');
         }
         else return false;
     }

     public Board(List<User> Players){
         int nPlayers= Players.size();
         this.Users=Players;
          if (nPlayers==2){
               this.sizeX=5; this.sizeY=5;
          }
          else if (nPlayers==3){
               this.sizeX=7; this.sizeY=5;
          }
          else {
               this.sizeX=10; this.sizeY=10;
          }
          MazeGenerator newMaze =(new MazeGenerator(this.sizeX, this.sizeY));
          this.Cells = new Cell[this.sizeY][this.sizeX];
          newMaze.display();
          this.Cells = newMaze.getCells();
          int finishX; int finishY;
          Random rn = new Random();
          if (nPlayers==2){
               finishX= rn.nextInt(this.sizeX );
               finishY= rn.nextInt(this.sizeY );
               this.Cells[finishX][finishY].CellType = 1 ;
               finishX= rn.nextInt(this.sizeX) ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
               this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX)  ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
          }
          else if (nPlayers==3){
               finishX= rn.nextInt(this.sizeX );
               finishY= rn.nextInt(this.sizeY );
               this.Cells[finishX][finishY].CellType = 1 ;
               finishX= rn.nextInt(this.sizeX ) ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX) ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX) ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
          }
          else {
               finishX= rn.nextInt(this.sizeX ) + 1;
               finishY= rn.nextInt(this.sizeY ) + 1;
               this.Cells[finishX][finishY].CellType = 1 ;
               finishX= rn.nextInt(this.sizeX);
               finishY= rn.nextInt(this.sizeY );
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX ) ;
               finishY= rn.nextInt(this.sizeY) ;
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX ) ;
               finishY= rn.nextInt(this.sizeY );
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
               finishX= rn.nextInt(this.sizeX);
               finishY= rn.nextInt(this.sizeY);
               this.Cells[finishX][finishY].CellType = 2 ;
              this.Positions.add(this.Cells[finishX][finishY]);
          }
     }
}


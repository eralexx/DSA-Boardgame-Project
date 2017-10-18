package Model.Classes;

import java.util.List;

public class Board {

     int Id;
     int Difficulty;
     List<User> Users;
     List<Cell> Cells;

     public int getId() {
          return Id;
     }

     public void setId(int id) {
          Id = id;
     }

     public int getDifficulty() {
          return Difficulty;
     }

     public void setDifficulty(int difficulty) {
          Difficulty = difficulty;
     }

     public List<User> getUsers() {
          return Users;
     }

     public void setUsers(List<User> users) {
          Users = users;
     }

     public List<Cell> getCells() {
          return Cells;
     }

     public void setCells(List<Cell> cells) {
          Cells = cells;
     }
}


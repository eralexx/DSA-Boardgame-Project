package Model.Classes;

import java.util.List;

public class Cell {

     int CellType; //0 = normal, 1= objective, 2= player start
     int PosX;
     int PosY;
     int GeneratorNum;
     User UserInCell;
     List<Character> Moves;

     public Cell(int cellType, int posX, int posY, int generatorNum) {
          CellType = cellType;
          PosX = posX;
          PosY = posY;
          GeneratorNum = generatorNum;
     }
     Item ItemInCell;
     List<User> UsersVisible;

     public int getCellType() {
          return CellType;
     }

     public void setCellType(int cellType) {
          CellType = cellType;
     }

     public int getPosX() {
          return PosX;
     }

     public void setPosX(int posX) {
          PosX = posX;
     }

     public int getPosY() {
          return PosY;
     }

     public void setPosY(int posY) {
          PosY = posY;
     }
}

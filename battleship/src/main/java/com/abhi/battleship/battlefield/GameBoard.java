package com.abhimanyu.battleship.battlefield;

import com.abhimanyu.battleship.helper.BattleFieldHelper;
import com.abhimanyu.battleship.ship.Ship;
import com.abhimanyu.battleship.ship.ShipDetail;
import java.util.Scanner;
import javafx.util.Pair;


public class GameBoard {
  private static final int BOARD_SIZE = 10;
  private static final String HORIZONTAL = "H";
  private static final String VERTICAL = "V";

  private Scanner scanner;
  private char[][] shipBoard;
  private int[][] enemyHitBoard;
  private  Ship[] ships;

 /* static {
    ships = new Ship[] {
        new Ship(ShipDetail.CARRIER),
        new Ship( ShipDetail.BATTLESHIP),
        new Ship(ShipDetail.CRUISER ),
        new Ship(ShipDetail.SUBMARINE),
        new Ship(ShipDetail.DESTROYER)
    };
  }*/

  public GameBoard() {
    this.scanner = new Scanner(System.in);
    this.shipBoard = new char[BOARD_SIZE][BOARD_SIZE];
    this.enemyHitBoard = new int[BOARD_SIZE][BOARD_SIZE];
    for(int i = 0;i< BOARD_SIZE;i++){
      for(int j = 0;j< BOARD_SIZE;j++){
        shipBoard[i][j] = '0';
      }
    }
    ships = new Ship[] {
        new Ship(ShipDetail.CARRIER),
        new Ship( ShipDetail.BATTLESHIP),
        new Ship(ShipDetail.CRUISER ),
        new Ship(ShipDetail.SUBMARINE),
        new Ship(ShipDetail.DESTROYER)
    };
  }

  public void placeShipsOnBoard() {
    for(Ship ship : ships) {
      boolean horizontal = askShipDirection(ship.getName());
      Pair<Integer,Integer> startingPoint = askShipLocation(ship, horizontal);
      placeShip(ship, startingPoint, horizontal);
    }
  }

  public char getField(int x, int y) {
    if(!isInsideBoard(x, y)) {
      throw new IllegalArgumentException("Outside board - try again: ");
    }
    return shipBoard[x][y];
  }



  public void printShipBoard() {
    for(int i = 0; i < BOARD_SIZE; i++) {
      for(int j = 0; j < BOARD_SIZE; j++) {
        System.out.print(shipBoard[i][j] + "\t");
      }
      System.out.println();
    }
  }

  private boolean askShipDirection(String shipName) {
    System.out.printf("%nPress H or V to place %s ship Horizontally or Vertically?",shipName);
    String direction;
    do {
      direction = scanner.nextLine().trim();
    }while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

    return HORIZONTAL.equals(direction);
  }


  private Pair<Integer,Integer> askShipLocation(Ship ship, boolean horizontal) {
    String from;
    do {
      System.out.printf("%nEnter position of %s (length  %d): ", ship.getName(), ship.getSize());
      from = scanner.nextLine().trim();
    } while(!isValidStartingPoint(from, ship.getSize(), horizontal));
    char fromArray[] = from.toCharArray();

    return new Pair<Integer, Integer>(fromArray[0] - 'A',Character.getNumericValue(fromArray[1]- 1));
  }

  private boolean isValidStartingPoint(String from, int length, boolean horizontal) {
    Pair<Integer,Integer> coordinates = BattleFieldHelper.calculateCoordinate(from);
    int x = coordinates.getKey();
    int y = coordinates.getValue();
    if(!isInsideBoard(x, y) || (!isInsideBoard(x ,y+length) && horizontal) || (!isInsideBoard(x + length, y) && !horizontal)) {
      System.out.println("Ship starting location is not valid.");
      System.out.println("Please Consult the below mentioned gameboard");
      printShipBoard();
      return false;
    }
    if(horizontal) {
      for (int i = 0; i < length; i++) {
        if (shipBoard[x]
            [y+i] != '0') {
          System.out.println("Ship cannot be placed here.Other ship already present.");
          System.out.println("Please Consult the below mentioned gameboard");
          printShipBoard();
          return false;
        }
      }
    }
    else{
      for (int i = 0; i < length; i++) {
        if (shipBoard[x+i]
            [y] != '0') {
          System.out.println("Ship cannot be placed here.Other ship already present.");
          System.out.println("Please Consult the below mentioned gameboard");
          printShipBoard();
          return false;
        }
      }
    }
    return true;
  }

  private void placeShip(Ship ship, Pair<Integer,Integer> startingPoint, boolean horizontal) {

    int x = startingPoint.getKey();
    int y = startingPoint.getValue();
    if (horizontal) {
      for (int i = 0; i < ship.getSize(); i++) {
        shipBoard[x]
            [y+i] = ship.getAbbrivation();
      }
    }
    else{
      for (int i = 0; i < ship.getSize(); i++) {
        shipBoard[x+i]
            [y] = ship.getAbbrivation();
      }
    }
  }
  private boolean isInsideBoard(int x, int y){
    return x <= BOARD_SIZE && x >= 0
        && y <= BOARD_SIZE && y >= 0;
  }


  public Ship getShip(char abbrivation){
    switch(abbrivation){
      case 'C' :
        return ships[0];
      case 'B' :
        return ships[1];
      case 'c' :
        return ships[2];
      case 'S' :
        return ships[3];
      case 'D' :
        return ships[4];
    }
    return null;
  }

  public void markGameBoard(int x,int y,char value){
    shipBoard[x][y] = value;
  }

  public void markEnemyBoard(int x, int y, char value){
    enemyHitBoard[x][y] = value;
  }


}

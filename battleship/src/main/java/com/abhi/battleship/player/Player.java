package com.abhimanyu.battleship.player;


import com.abhimanyu.battleship.battlefield.GameBoard;
import com.abhimanyu.battleship.helper.BattleFieldHelper;
import com.abhimanyu.battleship.ship.ShootResult;
import com.abhimanyu.battleship.ship.Ship;
import java.util.Scanner;
import javafx.util.Pair;


public class Player implements IPlayer {


  private int id;
  private GameBoard gameBoard;
  private Scanner scanner;
  private int totalShipScoreLeft;

  private static final char HIT = '1' ;
  private static final char MISS = '2';

  public Player(int id) {
    this.id = id;
    this.gameBoard = new GameBoard();
    this.scanner = new Scanner(System.in);
    this.totalShipScoreLeft = 17;
  }

  public int getId() {
    return id;
  }

  public GameBoard getGameBoard() {
    return gameBoard;
  }


  public void getBoardMatirx( ){
    gameBoard.printShipBoard();
  }

  @Override
  public void placeShips() {
    System.out.println("Player: "+id+"  .Please place out all your ships(Carrier,BattleShip,Cruiser,Submarine,Destroyer)");
    gameBoard.placeShipsOnBoard();
  }

  @Override
  public void fireAt(IPlayer opponent) {
    System.out.printf("%n Player %d - Enter coordinates for your attack: ", id);
    boolean isPointValid = false;
    while(!isPointValid) {
      try {
        String point = scanner.nextLine().trim();
        Pair<Integer,Integer> coordinate =  BattleFieldHelper.calculateCoordinate(point);
        int x = coordinate.getKey();
        int y = coordinate.getValue();
        char boardValue = ((Player) opponent).getGameBoard().getField(x,y);
        ShootResult result;
        processHitResult((Player) opponent, x, y, boardValue);
        isPointValid = true;
      } catch(IllegalArgumentException e) {
        System.out.printf(e.getMessage());
      }
    }
  }

  private void processHitResult(Player opponent, int x, int y, char boardValue) {
    ShootResult result;
    if(boardValue == '0' || boardValue == HIT || boardValue == MISS  ){
      result = ShootResult.NO_HIT;
      if(boardValue == '0') {
        populateBoards(opponent, x, y, MISS);
      }
      System.out.println("No Ship of opponent player hit");
    }
    else{
      Ship ship = opponent.getGameBoard().getShip(boardValue);
      ship.hit();
      result = ship.getState();
    }
    if(result == ShootResult.PARTIAL_HIT ||  result == ShootResult.DESTROYED) {
      populateBoards(opponent, x, y, HIT);
      opponent.decreaseLive();

    }
  }

  private void populateBoards(Player opponent, int x, int y, char miss) {
    gameBoard.markEnemyBoard(x, y, miss);
    opponent.getGameBoard().markGameBoard(x, y, miss);
  }

  @Override
  public int getTotalShipScoreLeft() {
    return totalShipScoreLeft;
  }

  private void decreaseLive(){
    this.totalShipScoreLeft --;
  }



}

package com.abhimanyu.battleship;

import com.abhimanyu.battleship.player.Player;

public class Game {
  private Player player1;
  private Player player2;

  public Game() {

    this.player1 = new Player(1);
    this.player2 = new Player(2);
  }

  public void start() {

    player1.placeShips();
    player2.placeShips();


    while(player1.getTotalShipScoreLeft() > 0 &&
        player2.getTotalShipScoreLeft() > 0) {

      player1.fireAt(player2);
      if(player2.getTotalShipScoreLeft() == 0){
        System.out.println("Congrats Player "+ player1.getId() +", you won!");
      }
      if(player1.getTotalShipScoreLeft() > 0 &&
          player2.getTotalShipScoreLeft() > 0) {
        player2.fireAt(player1);
        if (player1.getTotalShipScoreLeft() == 0) {
          System.out.printf("Congrats Player " + player2.getId() + ", you won!");
        }
      }

    }


  }
}

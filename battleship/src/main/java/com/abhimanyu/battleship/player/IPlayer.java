package com.abhimanyu.battleship.player;

public interface IPlayer {

  void placeShips();

  void fireAt(IPlayer opponent);

  int getTotalShipScoreLeft();

}

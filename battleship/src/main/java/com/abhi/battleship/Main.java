package com.abhimanyu.battleship;

import com.abhimanyu.battleship.Game;

public class Main {



  public static void main(String[] args) {
    System.out.println("Welcome to Battlefield.");
    pressAnyKeyToContinue();
    Game game = new Game();
    game.start();
  }

  private static void pressAnyKeyToContinue()
  {
    System.out.println("Press Enter key to continue...");
    try
    {
      System.in.read();
    }
    catch(Exception e)
    {}
  }

}

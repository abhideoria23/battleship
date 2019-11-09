package com.abhimanyu.battleship.helper;

import javafx.util.Pair;

public class BattleFieldHelper {

  public static Pair<Integer,Integer> calculateCoordinate(String point){
    char pointArray[] = point.toCharArray();
    int x = pointArray[0] - 'A';
    int y = Character.getNumericValue(pointArray[1] - 1);
    return new Pair<Integer, Integer>(x,y);
  }


}

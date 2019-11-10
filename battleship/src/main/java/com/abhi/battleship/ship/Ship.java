package com.abhimanyu.battleship.ship;

public class Ship {
  private final String name;
  private final int size;
  private int lives;
  private char abbrivation;

  public Ship(ShipDetail shipDetail) {
    this.name = shipDetail.getName();
    this.size = shipDetail.getSize();
    this.lives = shipDetail.getLife();
    this.abbrivation = shipDetail.getAbbreviation();
  }

  public void hit() {
    lives--;
    if(lives > 0) {
      System.out.printf("%nGood shot! The %s was hit", name);
    } else {
      System.out.println("Ship "+ name + " is destroyed");
    }
  }

  public ShootResult getState() {
    if(lives == 0) {
      return ShootResult.DESTROYED;
    } else if(lives < size) {
      return ShootResult.PARTIAL_HIT;
    } else {
      return ShootResult.NO_HIT;
    }
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public char getAbbrivation(){
    return abbrivation;
  }
}
package com.abhimanyu.battleship.ship;

public enum ShipDetail {
  CARRIER("Carrier", 5,5,'C'),
  BATTLESHIP("Battleship", 4,4,'B'),
  CRUISER("Cruiser", 3,3,'c'),
  SUBMARINE("Submarine", 3,3,'S'),
  DESTROYER("Destroyer", 2,2,'D');

  private String name;
  private int size;
  private int life;
  private char abbreviation;

  ShipDetail(String name, int size,int life,char abbrivation) {
    this.name = name;
    this.size = size;
    this.life = life;
    this.abbreviation = abbrivation;
  }


  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }

  public int getLife(){
    return life;
  }

  public char getAbbreviation(){
    return abbreviation;
  }

}

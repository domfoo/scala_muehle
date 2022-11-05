package de.htwg.se.muehle.model

enum Stone(stoneAsString: String):
  override def toString = stoneAsString
  case X extends Stone("X")
  case O extends Stone("O")
  case Empty extends Stone("+")

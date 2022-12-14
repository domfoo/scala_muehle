package de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl

enum Stone(stoneAsString: String):
    override def toString = stoneAsString
    case X extends Stone("X")
    case O extends Stone("O")
    case Empty extends Stone("+")

package de.htwg.se.muehle.model

enum Stone(stoneAsString: String):
    override def toString = stoneAsString
    def toStone(stoneAsString: String) = stoneAsString match {
        case "X" => Stone.X
        case "O" => Stone.O
        case "+" => Stone.Empty
    }
    case X extends Stone("X")
    case O extends Stone("O")
    case Empty extends Stone("+")

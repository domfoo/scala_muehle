package de.htwg.se.muehle.model

case class Player(name: String, stoneType: Stone, stones: Int = 9):
    override def toString: String = s"Name: $name, stone type: $stoneType, stones left: $stones"
    override def equals(player: Any): Boolean = {
        player match {
            case p:Player => p.name.equals(name) && p.stones.equals(stones)
            case _ => false
        }
    }

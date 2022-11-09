package de.htwg.se.muehle.model

case class Player(name: String, stones: Int = 9, stoneType: Stone):
    override def toString: String = s"Name: $name, stones left: $stones, stone type: $stoneType"
    override def equals(player: Any): Boolean = {
        player match {
            case p:Player => p.name.equals(name) && p.stones.equals(stones)
            case _ => false
        }
    }

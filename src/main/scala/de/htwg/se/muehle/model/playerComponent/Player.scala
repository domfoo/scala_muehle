package de.htwg.se.muehle.model.playerComponent

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone

case class Player(name: String, stoneType: Stone, var stones: Int = 9):
    override def toString: String = name
    override def equals(player: Any): Boolean = {
        player match {
            case p:Player => p.name.equals(name) && p.stones.equals(stones)
            case _ => false
        }
    }

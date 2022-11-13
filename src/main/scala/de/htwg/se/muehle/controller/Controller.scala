package de.htwg.se.muehle
package controller

import model.Field
import model.Move
import model.Player
import model.Stone
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field) extends Observable:
/*
    val players = new Array[Player](2)
    def addPlayerOne(name: String, stones: Int = 9) = {
        players(0) = Player(name, Stone.X, stones)
    }
    def addPlayerTwo(name: String, stones: Int = 9) = {
        players(1) = Player(name, Stone.O, stones)
    }
    def set(position: Int, stone: Stone): Option[Field] =
        field.setStone(position, stone) match
            case None => None
            case Some(f) => 
                field = f
                activePlayer = if (activePlayer == 1) 0 else 1
                notifyObservers
                Some(f)
        
    def move(oldPos: Int, newPos: Int): Option[Field] =
        field.moveStone(oldPos, newPos) match
            case None => None
            case Some(f) => 
                field = f
                activePlayer = if (activePlayer == 1) 0 else 1
                notifyObservers
                Some(f)
*/
    var player1: Player = _
    var player2: Player = _

    def nextPlayer(oldPlayer: Player): Player =
        if (oldPlayer == player1) player2 else player1

    def doAndPublish(doThis: Move => Field, move: Move): Unit =
        field = doThis(move)
        notifyObservers

    def execMove(move: Move): Field =
        field.execMove(move.stone, move.oldPosition, move.newPosition)
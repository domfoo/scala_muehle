package de.htwg.se.muehle
package controller

import model.Field
import model.Move
import model.Player
import model.Stone
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field) extends Observable:
    var player1: Player = _
    var player2: Player = _

    def nextPlayer(oldPlayer: Player): Player =
        if (oldPlayer == player1) player2 else player1

    def doAndPublish(doThis: Move => Field, move: Move): Unit =
        field = doThis(move)
        notifyObservers

    def execMove(move: Move): Field =
        field.execMove(move.stone, move.oldPosition, move.newPosition)
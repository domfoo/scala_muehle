package de.htwg.se.muehle
package controller

import model.Field
import model.Move
import model.Player
import model.Stone
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field, var player1: Option[Player] = None, var player2: Option[Player] = None) extends Observable:

    def nextPlayer(oldPlayer: Player): Player =
        if (oldPlayer == player1.get) player2.get else player1.get

    def initPlayers(player1: String, player2: String) =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))

    def doAndPublish(doThis: Move => Field, move: Move): Unit =
        field = doThis(move)
        notifyObservers

    def execMove(move: Move): Field =
        field.execMove(move)
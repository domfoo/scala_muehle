package de.htwg.se.muehle
package controller

import model.Field
//import model.Move
import model.Player
import model.Stone
import model.{PlayStrategy, Move, Put}
import util.{ControllerState, Player1State, Player2State}
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field, var player1: Option[Player] = None, var player2: Option[Player] = None, var state: ControllerState = Player1State()) extends Observable:
    /// changes the player state and returns the new active player 
    def nextPlayer(): Player =
        state match
            case first: Player1State => 
                state = Player2State()
                player2.get
            case second: Player2State =>
                state = Player1State()
                player1.get

    def initPlayers(player1: String, player2: String) =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))
/*
    def doAndPublish(doThis: Move => Field, move: Move): Unit =
        field = doThis(move)
        notifyObservers

    def execMove(move: Move): Field =
        field.execMove(move)*/
    
    def executeStrategy(strategy: PlayStrategy): Unit =
        field = strategy.execute(field)
        notifyObservers
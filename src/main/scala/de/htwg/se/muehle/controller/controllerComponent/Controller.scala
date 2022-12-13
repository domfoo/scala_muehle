package de.htwg.se.muehle
package controller

import controller.controllerComponent.IController
import model.impl.Field
import model.Player
import model.Stone
import model.{PlayStrategy, Move, Put}
import util.{ControllerState, Player1State, Player2State}
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field, var player1: Option[Player] = None, var player2: Option[Player] = None,
                 var state: ControllerState = Player1State()) extends IController with Observable:
                    
    private var undoStack: List[PlayStrategy] = Nil
    private var redoStack: List[PlayStrategy] = Nil

    // changes the player state and returns the new active player
    def nextPlayer(): Player =
        state match
            case first: Player1State =>
                state = Player2State()
                player2.get
            case second: Player2State =>
                state = Player1State()
                player1.get

    def initPlayers(player1: String, player2: String): Unit =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))

    def executeStrategy(strategy: PlayStrategy): Unit =
        strategy match {
            case p: Put => 
                state match {
                    case Player1State() => player1.get.stones -= 1
                    case Player2State() => player2.get.stones -= 1
            }
            case _ =>
        }
        undoStack = strategy :: undoStack
        field = strategy.execute(field)
        notifyObservers

    def undo(): Unit =
        undoStack match
            case Nil =>
            case head :: stack =>
                println("some undo stack")
                field = head.undo(field)
                undoStack = stack
                redoStack = head :: redoStack
        notifyObservers

    def redo(): Unit =
        redoStack match
            case Nil =>
            case head :: stack =>
                field = head.redo(field)
                redoStack = stack
                undoStack = head :: undoStack
        notifyObservers

    def newGame(): Unit =
        field = Field()
        state = Player1State()
        notifyObservers
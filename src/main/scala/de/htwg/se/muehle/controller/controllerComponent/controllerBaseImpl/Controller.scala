package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Move, Put}
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.util.{ControllerState, Player1State, Player2State}
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field, var state: ControllerState = Player1State(),
                 var player1: Option[Player] = None, var player2: Option[Player] = None)
                 extends IController with Observable:
                    
    private var undoStack: List[PlayStrategy] = Nil
    private var redoStack: List[PlayStrategy] = Nil

    // changes the player state and returns the new active player
    override def nextPlayer(): Player =
        state match
            case first: Player1State =>
                state = Player2State()
                player2.get
            case second: Player2State =>
                state = Player1State()
                player1.get

    override def initPlayers(player1: String, player2: String): Unit =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))

    override def executeStrategy(strategy: PlayStrategy): Unit =
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

    override def undo(): Unit =
        undoStack match
            case Nil =>
            case head :: stack =>
                println("some undo stack")
                field = head.undo(field)
                undoStack = stack
                redoStack = head :: redoStack
        notifyObservers

    override def redo(): Unit =
        redoStack match
            case Nil =>
            case head :: stack =>
                field = head.redo(field)
                redoStack = stack
                undoStack = head :: undoStack
        notifyObservers

    override def newGame(): Unit =
        field = Field()
        state = Player1State()
        notifyObservers
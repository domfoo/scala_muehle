package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Move, Put}
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.util.{ControllerState, Player1State, Player2State}
import de.htwg.se.muehle.util.Observable
import de.htwg.se.muehle.MuehleModule

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._


class Controller @Inject() (
        var field: Field, var state: ControllerState = Player1State(),
        var player1: Option[Player] = None, var player2: Option[Player] = None
    ) extends IController with Observable:
                    
    val injector = Guice.createInjector(new MuehleModule)
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
    
    // initializes the two players with a name and a stone type
    override def initPlayers(player1: String, player2: String): Unit =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))

    // undo the last command
    override def undo(): Unit =
        undoStack match
            case Nil =>
            case head :: stack =>
                println("some undo stack")
                field = head.undo(field)
                undoStack = stack
                redoStack = head :: redoStack
        notifyObservers

    // redo the last command
    override def redo(): Unit =
        redoStack match
            case Nil =>
            case head :: stack =>
                field = head.redo(field)
                redoStack = stack
                undoStack = head :: undoStack
        notifyObservers

    // create a new game with an empty board
    override def newGame(): Unit =
        field = Field()
        state = Player1State()
        notifyObservers
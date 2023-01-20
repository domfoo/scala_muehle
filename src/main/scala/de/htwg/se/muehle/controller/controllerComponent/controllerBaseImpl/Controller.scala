package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Move, Put}
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fileIO
import de.htwg.se.muehle.util.{ControllerState, Player1State, Player2State}
import de.htwg.se.muehle.util.Observable
import de.htwg.se.muehle.MuehleModule

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._


class Controller @Inject() (
        var field: Field, var fileHandler: fileIO.IFileIO, var state: ControllerState = Player1State(),
        var player1: Option[Player] = None, var player2: Option[Player] = None
    ) extends IController with Observable:

    private var undoStack: List[PlayStrategy] = Nil
    private var redoStack: List[PlayStrategy] = Nil

    // changes the player state and returns the new active player
    override def nextPlayer(): Option[Player] =
        state match
            case first: Player1State =>
                state = Player2State()
                player2
            case second: Player2State =>
                state = Player1State()
                player1

    override def currentPlayer(): Option[Player] = 
        state match
            case first: Player1State =>
                player1
            case second: Player2State =>
                player2
        

    // initializes the two players with a name and a stone type
    override def initPlayers(player1: String, player2: String): Unit =
        this.player1 = Some(Player(player1, Stone.X))
        this.player2 = Some(Player(player2, Stone.O))
        notifyObservers

    override def executeStrategy(strategy: PlayStrategy): Unit =
        undoStack = strategy :: undoStack
        field = strategy.execute(field)
        this.nextPlayer()
        notifyObservers

    // undo the last command
    override def undo(): Unit =
        undoStack match
            case Nil =>
            case head :: stack =>
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

    // no move
    override def stay(): Unit = notifyObservers

    // create a new game with an empty board
    override def newGame(): Unit =
        field = Field()
        state = Player1State()
        notifyObservers

    override def save(): Unit = 
        fileHandler.save(field, currentPlayer().get)
        notifyObservers
    override def load(): Unit = 
        val (f, p) = fileHandler.load()
        this.field = f
        p.stoneType match
            case Stone.X => 
                this.player1 = Some(p)
                this.state = Player1State()
            case Stone.O => 
                this.player2 = Some(p)
                this.state = Player1State()
            case _ =>
        
        notifyObservers

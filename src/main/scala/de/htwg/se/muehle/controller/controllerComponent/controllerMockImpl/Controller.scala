package de.htwg.se.muehle.controller.controllerComponent.controllerMockImpl

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.PlayStrategy
import de.htwg.se.muehle.util.{ControllerState, Player1State, Player2State}

class Controller(var field: Field, var state: ControllerState = Player1State(),
                 var player1: Option[Player] = None, var player2: Option[Player] = None)
                 extends IController:
    
    field = Field()
    
    override def nextPlayer(): Option[Player] = player2
    override def currentPlayer(): Option[Player] = player1
    override def initPlayers(player1: String, player2: String): Unit = {}
    override def executeStrategy(strategy: PlayStrategy): Unit = {}
    override def undo(): Unit = {}
    override def redo(): Unit = {}
    override def stay(): Unit = {}
    override def newGame(): Unit = {}
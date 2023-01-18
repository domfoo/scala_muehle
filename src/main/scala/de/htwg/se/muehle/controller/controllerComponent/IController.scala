package de.htwg.se.muehle.controller.controllerComponent

import de.htwg.se.muehle.util.Observable
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.PlayStrategy
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.util.ControllerState

trait IController extends Observable:
    def field: Field
    def state: ControllerState
    var player1: Option[Player]
    var player2: Option[Player]
    def nextPlayer(): Player
    def currentPlayer(): Player
    def initPlayers(player1: String, player2: String): Unit
    def executeStrategy(strategy: PlayStrategy): Unit
    def undo(): Unit
    def redo(): Unit
    def stay(): Unit
    def newGame(): Unit

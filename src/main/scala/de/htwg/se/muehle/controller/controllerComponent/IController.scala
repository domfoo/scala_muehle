package de.htwg.se.muehle.controller.controllerComponent

import de.htwg.se.muehle.util.Observable
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.PlayStrategy

trait IController extends Observable:
    def nextPlayer(): Player
    def initPlayers(player1: String, player2: String): Unit
    def executeStrategy(strategy: PlayStrategy): Unit
    def undo(): Unit
    def redo(): Unit
    def newGame(): Unit

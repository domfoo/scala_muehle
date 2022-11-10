package de.htwg.se.muehle
package controller

import model.Field
import model.Player
import model.Stone
import de.htwg.se.muehle.util.Observable

class Controller(var field: Field) extends Observable:
    val players = new Array[Player](2)
    def addPlayerOne(name: String, stones: Int = 9) = {
        players(0) = Player(name, Stone.X, stones)
    }
    def addPlayerTwo(name: String, stones: Int = 9) = {
        players(1) = Player(name, Stone.O, stones)
    }
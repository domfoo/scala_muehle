package de.htwg.se.muehle
package controller

import model.Matrix
import model.Player
import de.htwg.se.muehle.util.Observable

class Controller(var field: Matrix) extends Observable:
    val players = new Array[Player](2)
    def addPlayerOne(name: String, stones: Int = 9) = {
        players(0) = Player(name, stones)
    }
    def addPlayerTwo(name: String, stones: Int = 9) = {
        players(1) = Player(name, stones)
    }
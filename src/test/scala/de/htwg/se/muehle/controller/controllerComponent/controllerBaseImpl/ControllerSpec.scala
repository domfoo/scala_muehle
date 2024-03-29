package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fileIO.fileIOJSON.FileIO

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class ControllerSpec extends AnyWordSpec {
    "A Controller" should {
        val controller = Controller(Field(), FileIO())
        controller.initPlayers("Tom", "Max")
        "switch from players" in {
            controller.nextPlayer() should be(controller.player2)
            controller.nextPlayer() should be(controller.player1)
        }
        "create a new game" in {
            controller.newGame()
            controller.field should be(Field())
        }
    }
}
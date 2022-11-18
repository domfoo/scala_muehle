package de.htwg.se.muehle
package controller

import controller.Controller
import model.Field
import model.Player
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class ControllerSpec extends AnyWordSpec {
    "A Controller" should {
        val controller = Controller(new Field())
        controller.player1 = Player("Tom", Stone.X)
        controller.player2 = Player("Max", Stone.O)
        "switch from players" in {
            controller.nextPlayer(controller.player1) should be(controller.player2)
            controller.nextPlayer(controller.player2) should be(controller.player1)
        }
    }
}
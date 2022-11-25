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
        val controller = Controller(Field())
        controller.initPlayers("Tom", "Max")
        "switch from players" in {
            controller.nextPlayer() should be(controller.player2.get)
            controller.nextPlayer() should be(controller.player1.get)
        }
    }
}
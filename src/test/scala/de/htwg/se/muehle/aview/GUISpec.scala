package de.htwg.se.muehle
package aview

import aview.GUI
import controller.Controller
import model.Field
import model.{PlayStrategy, Put, Move}
import model.Player
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GUISpec extends AnyWordSpec {
    "The GUI" should {
        val controller = Controller(Field())
        val gui = GUI(controller)
        "create a frame" in {
            
        }
    }
}
package de.htwg.se.muehle
package aview

import aview.TUI
import controller.Controller
import model.Field
import model.{PlayStrategy, Put, Move}
import model.Player
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TUISpec extends AnyWordSpec {
    "The TUI" should {
        val controller = Controller(Field())
        val tui = TUI(controller)
        "reading and handling input" should {
            "recognize the input 'set 2' as setting the stone X to cell 2" in {
                val strat = Put(2, Stone.X)
                tui.handleInput("set 2", Stone.X) should be(Left(strat))
                controller.executeStrategy(strat)
            }
            "recognize the input 'move 2 3' as moving the stone X from cell 2 to cell 3" in {
                val strat = Move(2, 3, Stone.X)
                tui.handleInput("move 2 3", Stone.X) should be(Left(strat))
                controller.executeStrategy(strat)
            }
            "recognize invalid input" in {
                tui.handleInput("hello world", Stone.X) should be(Right("hello"))
            }
            "recognize the input 'h' as returning a help signal" in {
                tui.handleInput("h", Stone.X) should be(Right("h"))
            }
            "recognize the input 'q' as returning a quit signal" in {
                tui.handleInput("q", Stone.X) should be(Right("q"))
            }
        }
    }
}
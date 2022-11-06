package de.htwg.se.muehle
package aview

import aview.TUI
import model.Matrix
import model.Player
import model.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TUISpec extends AnyWordSpec {
    
    "A new TUI" when {
        "while reading and handling input" should {
            /**"recognize the input 'q' as ending the program" in {
                val tui = TUI()
                tui.handleInput("q") should be(None)
            }*/
            "recognize the input 'set' as placing a stone on the game field" in {
                val tui = TUI()
                val field = tui.field
                tui.handleInput("set 99X") should be(Some(field.setStone(9, 9, Stone.X)))
                tui.handleInput("set 00Y") should be(Some(field.setStone(0, 0, Stone.Empty)))
                tui.handleInput("set 00X") should be(Some(field.setStone(0, 0, Stone.X)))
                tui.handleInput("set 23O") should be(Some(
                    field.setStone(0, 0, Stone.X).setStone(2, 3, Stone.O)
                ))
            }
            "recognize the input 'move' as moving a stone on the game field" in {
                val tui = TUI()
                val field = tui.field
                tui.handleInput("set 00X")
                tui.handleInput("move 0003") should be(Some(
                    field.setStone(0, 0, Stone.X).moveStone(0, 0, 0, 3)
                ))
            }
            "recognize an unvalid command" in {
                val tui = TUI()
                tui.handleInput("abc 123") should be(None)
            }
        }
    }
}
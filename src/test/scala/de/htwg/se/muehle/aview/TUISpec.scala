package de.htwg.se.muehle.aview

import de.htwg.se.muehle.aview.TUI
import de.htwg.se.muehle.controller.controllerComponent.controllerMockImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Put, Move}
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fileIO.fileIOMockImpl.FileIO

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class TUISpec extends AnyWordSpec {
    "The TUI" should {
        val controller = Controller(Field())
        val tui = TUI(controller)
        "read and handle input" should {
            "recognize the input 'undo' as not undoing anything if there are no prior commands" in {
                tui.handleInput("undo", Stone.X) should be(Right("undo"))
                controller.undo()
            }
            "recognize the input 'redo' as not redoing anything if there are no next commands" in {
                tui.handleInput("redo", Stone.X) should be(Right("redo"))
                controller.redo()
            }
            "recognize the input 'set 2' as setting the stone X to cell 2" in {
                val strat = Put(2, Stone.X)
                tui.handleInput("set 2", Stone.X) should be(Left(strat))
                controller.executeStrategy(strat)
            }
            "recognize the input 'undo' as undoing a set command" in {
                tui.handleInput("undo", Stone.X) should be(Right("undo"))
                controller.undo()
            }
            "recognize the input 'redo' as redoing a set command" in {
                tui.handleInput("redo", Stone.X) should be(Right("redo"))
                controller.redo()
            }/*
            "recognize the input 'move 2 3' as moving the stone X from cell 2 to cell 3" in {
                val strat = Move(2, 3, Stone.X)
                tui.handleInput("move 2 3", Stone.X) should be(Left(strat))
                controller.executeStrategy(strat)
            }*/
            "recognize the input 'undo' as undoing a move command" in {
                tui.handleInput("undo", Stone.X) should be(Right("undo"))
                controller.undo()
            }
            "recognize the input 'redo' as redoing a move command" in {
                tui.handleInput("redo", Stone.X) should be(Right("redo"))
                controller.redo()
            }
            "recognize the input 'new' as creating a new game" in {
                tui.handleInput("new", Stone.X) should be(Right("new"))
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
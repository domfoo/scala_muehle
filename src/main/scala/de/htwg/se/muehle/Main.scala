package de.htwg.se.muehle

import aview.TUI
import controller.Controller
import model.Stone
import model.Field

@main def run: Unit =
    val field = new Field()
    val controller = Controller(field)
    val tui = TUI(controller)
    tui.run

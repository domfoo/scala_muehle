package de.htwg.se.muehle

import aview.TUI
import aview.SwingGUI
import controller.Controller
import model.Stone
import model.Field

@main def run: Unit =
    val field = Field()
    val controller = Controller(field)
    val tui = TUI(controller)
    val gui = new SwingGUI(controller)
    tui.run

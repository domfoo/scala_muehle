package de.htwg.se.muehle

import aview.TUI
import aview.GUI
import controller.Controller
import model.Stone
import model.Field

@main def run: Unit =
    val field = Field()
    val controller = Controller(field)
    val tui = TUI(controller)
    val gui = new GUI(controller)
    tui.run

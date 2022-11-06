package de.htwg.se.muehle

import aview.TUI
import controller.Controller
import model.Matrix
import model.Stone

@main def run: Unit =
    val field = new Matrix(7, Stone.Empty)
    val controller = Controller(field)
    val tui = TUI(controller)
    tui.run

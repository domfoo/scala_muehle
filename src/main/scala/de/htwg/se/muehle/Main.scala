package de.htwg.se.muehle

import de.htwg.se.muehle.aview.TUI
import de.htwg.se.muehle.aview.GUI
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field

@main def run: Unit =
    val field = Field()
    val controller = Controller(field)
    val tui = TUI(controller)
    val gui = new GUI(controller)
    tui.run

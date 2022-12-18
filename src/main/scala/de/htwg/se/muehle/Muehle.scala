package de.htwg.se.muehle

import de.htwg.se.muehle.aview.TUI
import de.htwg.se.muehle.aview.GUI
import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.IField
import com.google.inject.Guice


object Muehle {
    val injector = Guice.createInjector(new MuehleModule)
    val controller = injector.getInstance(classOf[IController])

    @main
    def main(): Unit =
        val tui = TUI(controller)
        val gui = GUI(controller)
        tui.run
}

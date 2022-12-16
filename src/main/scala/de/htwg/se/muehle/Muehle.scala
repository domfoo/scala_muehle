package de.htwg.se.muehle

import de.htwg.se.muehle.aview.TUI
import de.htwg.se.muehle.aview.GUI
import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import com.google.inject.Guice


object Muehle {
    //val injector = Guice.createInjector(new MuehleModule)
    val field = Field()
    given IField = field
    //val controller = injector.getInstance(classOf[IController])
    val controller = new Controller(field)
    given IController = controller
    val tui = TUI(controller)
    val gui = new GUI(controller)
    
    @main
    def main(): Unit =
        tui.run
}

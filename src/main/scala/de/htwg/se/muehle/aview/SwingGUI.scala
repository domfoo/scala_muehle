package de.htwg.se.muehle
package aview

import model.Field
import model.Player
import model.Stone
import model.{PlayStrategy, Put, Move}
import controller.Controller
import util.Observer
import scala.swing._
import scala.swing.event._
import java.awt.Dimension
import scala.swing.Swing.EmptyBorder
import javax.swing.SwingUtilities
import scala.util.Try

class SwingGUI(controller: Controller) extends Frame with Observer:
  controller.add(this)
  title = "Nine men's morris"
  preferredSize = new Dimension(800, 600)
  resizable = true

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        // implement newGame() method in Controller (unwichtig)
      })
      contents += new Separator
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo()
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo()
      })
    }
  }

  contents = new BorderPanel {
    add(new Label("Welcome to Nine Men's Morris".toUpperCase()), BorderPanel.Position.North)
    add(new CellPanel(), BorderPanel.Position.Center)
  }

  pack()
  centerOnScreen()
  open()

  def update: Unit =
    // TODO: effizientere Lösung finden anstatt jedes mal ein neues Frame zu erzeugen
    this.dispose()
    new SwingGUI(controller)
    visible = true

  class CellPanel() extends GridPanel(7, 7):
    border = EmptyBorder(20,20,20,20)
    printField

    def printField: IndexedSeq[Matchable] =
      var i = 0
      val list = fieldToList()
      (1 to 49).map(x =>
        if (cellIsDefined(x)) { contents += new CellButton((i+1).toInt, list(i).toString); i += 1}
        else contents += new Button(""))

    def fieldToList(): List[Stone] =
      controller.field.cells.values.toList
    def definedCells: List[Int] =
      List(1,4,7,9,11,13,17,18,19,22,23,24,26,27,28,31,32,33,37,39,41,43,46,49)
    def cellIsDefined(pos: Int): Boolean =
      if (definedCells.contains(pos)) true else false

  class CellButton(pos: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    reactions += {
      case ButtonClicked(button) =>
        controller.executeStrategy(Put(pos, controller.nextPlayer().stoneType))
      // TODO: move implementieren
      /*case ButtonClicked(button) =>
        case ButtonClicked(button2) =>
          controller.executeStrategy(Move(, , controller.nextPlayer().stoneType))
          button.location
        case _ =>*/
    }

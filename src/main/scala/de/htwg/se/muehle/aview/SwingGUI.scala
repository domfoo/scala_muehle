package de.htwg.se.muehle
package aview

import model.Field
import model.Player
import model.Stone
import model.PlayStrategy
import controller.Controller
import util.Observer
import scala.swing._
import scala.swing.event._
import java.awt.Dimension
import scala.swing.Swing.EmptyBorder

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGUI(controller: Controller) extends Frame with Observer:
  controller.add(this)
  title = "Nine men's morris"
  preferredSize = new Dimension(600, 600)
  resizable = false
  visible = true

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

  val label = new Label("Welcome to Nine Men's Morris".toUpperCase())
  val board = new CellPanel()

  contents = new BorderPanel {
    add(label, BorderPanel.Position.North)
    add(board, BorderPanel.Position.Center)
  }
  //reactions += {}

  pack()
  centerOnScreen()
  open()

  def update: Unit = repaint()

  class CellPanel() extends GridPanel(7, 7):
    border = EmptyBorder(20,20,20,20)
    val list = fieldToList()
    // TODO: in jedem button soll jeweils das richtige listenelement stehen
    (1 to 49).map(x => if (cellIsDefined(x)) contents += new Button(list.toString) else contents += new Button(""))

    /*list.map(y =>
      (1 until 49).map(x =>
        if (cellIsDefined(x)) contents += new Button(list.toString) else contents += new Button("")
    ))*/

    def button(stone: Stone) = new Button(stone.toString)

  class CellButton(pos: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, props) => {
        // TODO: case cell ist leer, dann set
        // TODO: case cell ist besetzt, dann move möglich
      }
    }

  def definedCells: List[Int] = List(1,4,7,9,11,13,17,18,19,22,23,24,26,27,28,31,32,33,37,39,41,43,46,49)
  def cellIsDefined(pos: Int): Boolean =
        if (definedCells.contains(pos)) true else false

  def fieldToList(): List[Stone] =
        controller.field.cells.values.toList

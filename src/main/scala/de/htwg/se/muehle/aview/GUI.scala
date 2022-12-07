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
import javax.swing.border.EmptyBorder

class GUI(controller: Controller) extends Frame with Observer:
  controller.add(this)
  title = "Nine men's morris"
  preferredSize = new Dimension(800, 600)
  resizable = true

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.newGame()
        MoveTextField.moveTextField.text = ""
      })
      contents += new Separator
      contents += new MenuItem(Action("Quit") {
        println("Bye!")
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
  contents = contentPanel

  pack()
  centerOnScreen()
  open()


  // singleton which makes it possible to read the input of the textfield (from CellButton)
  object MoveTextField {
    var moveTextField = new TextField("", 2)
  }
  def movePanel: GridPanel = new GridPanel(1,2) {
          border = Swing.EmptyBorder(0,100,0,100)
          contents += new Label("Move to position (1-24): ")
          contents += MoveTextField.moveTextField
        }
  def contentPanel = new BorderPanel {
    add(new Label("Welcome to Nine Men's Morris".toUpperCase()), BorderPanel.Position.North)
    add(new CellPanel(), BorderPanel.Position.Center)
    add(movePanel, BorderPanel.Position.South)
  }
  // this overwrites the contents of the GUI with a new BorderPanel every time the data structure is changed
  def update: Unit = contents = contentPanel

  // class for creating a panel consisting of buttons
  class CellPanel() extends GridPanel(7, 7):
    border = Swing.EmptyBorder(20,20,20,20)
    printField

    def printField: IndexedSeq[Matchable] =
      var i = 0
      val list = fieldToList()
      (1 to 49).map(x =>
        if (cellIsDefined(x)) {contents += new CellButton((i+1).toInt, list(i).toString); i += 1}
        else contents += new Button(""))

    // auxiliary methods for printing the playfield
    def fieldToList(): List[Stone] =
      controller.field.cells.values.toList
    def definedCells: List[Int] =
      List(1,4,7,9,11,13,17,18,19,22,23,24,26,27,28,31,32,33,37,39,41,43,46,49)
    def cellIsDefined(pos: Int): Boolean =
      if (definedCells.contains(pos)) true else false

  // class for buttons with a specific behavior
  class CellButton(pos: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    listenTo(keys)
    reactions += {
      case ButtonClicked(button) =>
        val input = MoveTextField.moveTextField.text
        // check if players are initialized
        if (controller.player1 == None || controller.player2 == None)
          println("Please enter your names first!")
        // if textfield is empty, put a stone on the playfield
        else if (input == "")
          if (
            Try(pos).isSuccess &&
            controller.field.fieldRange.contains(pos) &&
            controller.field.isEmptyCell(pos)
          )
          controller.executeStrategy(Put(pos, controller.nextPlayer().stoneType))
        // if textfield contains a number (position), move a stone on the playfield
        else if (input.matches("[1-9]|(1[0-9])|(2[0-4])"))
          val newPos = input.toInt
          if (
            Try(pos).isSuccess &&
            Try(newPos).isSuccess &&
            controller.field.fieldRange.contains(pos) &&
            controller.field.fieldRange.contains(newPos) &&
            controller.field.isMovableToPosition(pos, newPos, toStone(stone))
          )
          controller.executeStrategy(Move(pos, newPos, toStone(stone)))
        else
          println("Input has to be a number from 1 to 24!")
    }
  def toStone(stoneAsString: String) = stoneAsString match {
      case "X" => Stone.X
      case "O" => Stone.O
      case "+" => Stone.Empty
  }

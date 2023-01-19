package de.htwg.se.muehle
package aview

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Put, Move}
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.util.Observer
import com.google.inject.{Guice, Inject}

import scala.swing._
import scala.swing.event._
import scala.util.Try
import java.awt.Dimension
import javax.swing.border.EmptyBorder


class GUI(@Inject controller: IController) extends Frame with Observer:
  controller.add(this)
  title = "Nine men's morris"
  preferredSize = new Dimension(800, 800)
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


  // singeltons which make it possible to read the player names (from CellButton)
  object Player1TextField { val player1TextField = new TextField() }
  object Player2TextField { val player2TextField = new TextField() }

  // creating a panel for game title and player names
  def titlePanel: Label = new Label("Welcome to Nine Men's Morris".toUpperCase())
  def playerNamesPanel: GridPanel = new GridPanel(2,2) {
    border = new EmptyBorder(10,20,10,20)
    contents += new Label("Player 1:")
    contents += Player1TextField.player1TextField
    contents += new Label("Player 2:")
    contents += Player2TextField.player2TextField
  }
  def currentPlayerPanel: Label = new Label(s"It is ${controller.currentPlayer().getOrElse("")}'s move!")
  def titleAndPlayerNamesPanel: GridPanel = new GridPanel(2,1) {
    contents += titlePanel
    contents += playerNamesPanel
    contents += currentPlayerPanel
  }

  // singleton which makes it possible to read a position (from CellButton)
  object MoveTextField { var moveTextField = new TextField() }

  // creating a panel for moving stones on the playfield
  def movePanel: GridPanel = new GridPanel(1,2) {
    border = Swing.EmptyBorder(20,100,20,100)
    contents += new Label("Move to position (1-24): ")
    contents += MoveTextField.moveTextField
  }

  // put all panels together
  def contentPanel = new BorderPanel {
    add(titleAndPlayerNamesPanel, BorderPanel.Position.North)
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


  // class for buttons with which to interact
  class CellButton(pos: Int, stone: String) extends Button(stone):
    listenTo(mouse.clicks)
    listenTo(keys)
    reactions += {
      case ButtonClicked(button) =>
        val input = MoveTextField.moveTextField.text
        val player1 = Player1TextField.player1TextField.text
        val player2 = Player2TextField.player2TextField.text
        controller.player1 = player1 match {
          case "" => None
          case _ => Some(new Player(Player1TextField.player1TextField.text, Stone.X, 9))
        }
        controller.player2 = player2 match {
          case "" => None
          case _ => Some(new Player(Player2TextField.player2TextField.text, Stone.O, 9))
        }
        // check if players are initialized
        if (controller.player1 == None || controller.player2 == None)
          println("Please enter your names first!")
        // if textfield is empty, put a stone on the playfield
        else
          if (input == "")
            if (
              Try(pos).isSuccess &&
              controller.field.fieldRange.contains(pos) &&
              controller.field.isEmptyCell(pos)
            )
            controller.executeStrategy(Put(pos, controller.currentPlayer().get.stoneType))
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

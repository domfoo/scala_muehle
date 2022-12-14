package de.htwg.se.muehle
package aview

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.{PlayStrategy, Put, Move}
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.util.{Player1State, Player2State}
import de.htwg.se.muehle.util.Observer
import scala.io.StdIn.readLine
import scala.util.Try

class TUI(controller: Controller) extends Observer:
    controller.add(this)
    val eol = sys.props("line.separator")
    val welcomeMessage = "---WELCOME TO MILL!---"
    val helpMessage =
        "Type 'set 3' to place a stone on the third position." + eol +
        "Type 'move 2 3' to move a stone from position 2 to position 3." + eol +
        "Type 'undo' to undo your last command." + eol +
        "Type 'redo' to redo your last command." + eol +
        "Type 'h' or 'help' for this help message." + eol +
        "Type 'q' or 'quit' to close the game."
    val wrongInputMessage = "Invalid command. Please use 'help' to see available commands."
    val exitMessage = "Bye!"
    val newGameMessage = "\n\n\n\n\n\n\n\n\n\n---NEW GAME---"

    def run = {
        println(welcomeMessage + eol + "First, please enter the name of the first player:")
        val player1Name = readLine()

        println(eol + "Now, please enter the name of the second player:")
        val player2Name = readLine()

        controller.initPlayers(player1Name, player2Name)

        println(eol + helpMessage)
        println(controller.field.fieldNumberOverview + eol)
        update

        gameLoop(controller.player1.get)
    }

    def gameLoop(player: Player): Unit =
        println("Player " + player.name + " (" + player.stoneType + "):")
        print("> ")
        handleInput(readLine, player.stoneType) match
            case Left(strategy) =>
                controller.executeStrategy(strategy)
                gameLoop(controller.nextPlayer())
            case Right(command) if command == "undo" =>
                controller.undo()
                gameLoop(player)
            case Right(command) if command == "redo" =>
                controller.redo()
                gameLoop(player)
            case Right(command) if command == "new" =>
                controller.newGame()
                gameLoop(player)
            case Right(command) if command == "h" => gameLoop(player)
            case Right(command) if command == "q" =>
            case _ => gameLoop(player)

    // returns a Move when the input is a valid move or the first element of the input list as a String which can be used handle the help and quit command
    def handleInput(input: String, stone: Stone): Either[PlayStrategy, String] = {
        val inputList = input.split(" ").toList
        inputList match
            case "new" :: Nil =>
                println(newGameMessage)
                Right(inputList.head)
            case "q" :: Nil | "quit" :: Nil =>
                println(exitMessage)
                Right(inputList.head)
            case "h" :: Nil | "help" :: Nil =>
                println(eol + helpMessage)
                println(controller.field.fieldNumberOverview + eol)
                Right(inputList.head)
            case "redo" :: Nil => Right(inputList.head)
            case "undo" :: Nil => Right(inputList.head)
            case "set" :: newPos :: Nil if (
                Try(newPos.toInt).isSuccess &&
                controller.field.fieldRange.contains(newPos.toInt) &&
                controller.field.isEmptyCell(newPos.toInt))
                =>
                Left(Put(newPos.toInt, stone))
            case "move" :: oldPos :: newPos :: Nil if (
                Try(oldPos.toInt).isSuccess &&
                Try(newPos.toInt).isSuccess &&
                controller.field.fieldRange.contains(oldPos.toInt) &&
                controller.field.fieldRange.contains(newPos.toInt) &&
                controller.field.isMovableToPosition(oldPos.toInt, newPos.toInt, stone))
                =>
                Left(Move(oldPos.toInt, newPos.toInt, stone))
            case _ =>
                println(eol + wrongInputMessage + eol)
                Right(inputList.head)
    }

    override def update: Unit = println(controller.field)

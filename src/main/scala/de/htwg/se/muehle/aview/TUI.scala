package de.htwg.se.muehle
package aview

import model.Field
import model.Move
import model.Player
import model.Stone
import controller.Controller
import util.Observer
import scala.io.StdIn.readLine
import scala.util.Try

class TUI(controller: Controller) extends Observer:
    controller.add(this)
    val eol = sys.props("line.separator")
    val welcomeMessage = "---WELCOME TO MILL!---"
    val helpMessage = 
        "Type 'set 3' to place a stone on the third position." + eol +
        "Type 'move 2 3' to move a stone from position 2 to position 3." + eol +
        "Type 'h' or 'help' for this help message." + eol +
        "Type 'q' or 'quit' to close the game." 
    val wrongInputMessage = "Invalid command. Please use 'help' to see available commands."
    val exitMessage = "Bye!"

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
            case Left(move) => 
                controller.doAndPublish(controller.execMove, move)
                gameLoop(controller.nextPlayer(player))
            case Right(command) if command == "h" => gameLoop(player)
            case Right(command) if command == "q" => 
            case _ => gameLoop(player)
        
    /// returns a Move when the input is a valid move or the first element of the input list as a String which can be used handle the help and quit command
    def handleInput(input: String, stone: Stone): Either[Move, String] = {
        val inputList = input.split(" ").toList
        inputList match
            case "q" :: Nil | "quit" :: Nil =>
                println(exitMessage)
                Right(inputList.head)
            case "h" :: Nil | "help" :: Nil => 
                println(eol + helpMessage)
                println(controller.field.fieldNumberOverview + eol)
                Right(inputList.head)
            case "set" :: newPos :: Nil if (
                Try(newPos.toInt).isSuccess && 
                controller.field.fieldRange.contains(newPos.toInt)) &&
                controller.field.isEmptyCell(newPos.toInt) 
                =>
                Left(Move(stone, None, newPos.toInt))
            case "move" :: oldPos :: newPos :: Nil if (
                Try(oldPos.toInt).isSuccess && 
                Try(newPos.toInt).isSuccess && 
                controller.field.fieldRange.contains(oldPos.toInt) && 
                controller.field.fieldRange.contains(newPos.toInt)) && 
                controller.field.isMovableToPosition(oldPos.toInt, newPos.toInt)
                =>
                Left(Move(stone, Some(oldPos.toInt), newPos.toInt))
            case _ => 
                println(eol + wrongInputMessage + eol)
                Right(inputList.head)
    }

    override def update: Unit = println(controller.field)

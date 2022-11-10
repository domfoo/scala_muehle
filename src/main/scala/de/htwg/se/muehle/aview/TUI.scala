package de.htwg.se.muehle
package aview

import model.Field
import model.Player
import model.Stone
import controller.Controller
import util.Observer
import scala.annotation.newMain
import scala.io.StdIn.readLine
import scala.util.Try

class TUI(controller: Controller) extends Observer:
    val eol = sys.props("line.separator")
    val helpMessage = 
        "Type 'set 3' to place a stone on the third position.\n" +
        "Type 'move 2 3' to move a stone from position 2 to position 3.\n" +
        "Type 'h' or 'help' for this help message.\n" +
        "Type 'q' or 'quit' to close the game." 
    val welcomeMessage = "---WELCOME TO MILL!---\n"
    val wrongInputMessage = "Wrong command. Please use 'help' to see available commands."
    val wrongSetMessage = "Cannot set to position."
    val wrongMoveMessage = "Cannot move to position."
    val exitMessage = "Bye!"

    controller.add(this)
    var field = controller.field

    def run = {
        println(welcomeMessage + "First, please enter the name of the first player:")
        controller.addPlayerOne(readLine)
        
        println("Now, please enter the name of the second player:")
        controller.addPlayerTwo(readLine)

        println(eol + helpMessage)
        println(field.fieldNumberOverview + eol)
        println(eol + field)
        
        gameLoop()
    }

    def gameLoop(): Unit = {
        var test = true
        while (true)
            val playerIndex = if (!test) 1 else 0
            println("Player " + controller.players(playerIndex).name + " (" + controller.players(playerIndex).stoneType + "):")
            print("> ")
            // change turn if the player's move was valid
            handleInput(readLine, controller.players(playerIndex).stoneType) match
                case Some(newField) => 
                    test = !test
                    field = newField
                    print(field)
                case None =>
    }

    def handleInput(input: String, stone: Stone): Option[Field] = {
        input.split(" ").toList match
            case "q" :: Nil | "quit" :: Nil =>
                println(exitMessage)
                System.exit(0)
                None
            case "h" :: Nil | "help" :: Nil => 
                println(eol + helpMessage)
                println(field.fieldNumberOverview + eol)
                None
            case "set" :: newPos :: Nil if (Try(newPos.toInt).isSuccess && field.fieldRange.contains(newPos.toInt)) =>
                field.setStone(newPos.toInt, stone) match
                    case Some(x) => Some(x)
                    case None =>
                        println(eol + wrongSetMessage + eol)
                        None

            case "move" :: oldPos :: newPos :: Nil if (Try(oldPos.toInt).isSuccess && Try(newPos.toInt).isSuccess && field.fieldRange.contains(oldPos.toInt) && field.fieldRange.contains(newPos.toInt)) =>
                field.moveStone(oldPos.toInt, newPos.toInt) match
                    case Some(x) => Some(x)
                    case None =>
                        println(eol + wrongMoveMessage + eol)
                        None
            case _ => 
                println(eol + wrongInputMessage + eol)
                None
    }

    override def update: Unit = println(controller.field)

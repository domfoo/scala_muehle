package de.htwg.se.muehle
package aview

import model.Matrix
import model.Player
import model.Stone
import controller.Controller
import scala.io.StdIn.readLine
import de.htwg.se.muehle.util.Observer

class TUI(controller: Controller) extends Observer:
    val helpMessage = "Type 'set 33X' to place a stone X at the third row and third column.\nType 'move 2223' to move a stone from (row=2,col=2) to (row=2,col=3)."

    controller.add(this)
    var field = controller.field
    var counter = 0
    def run = {
        println("---WELCOME TO MILL!---\n" + "First, please enter the name of the first player:")
        controller.addPlayerOne(readLine)
        
        println("Now, please enter the name of the second player:")
        controller.addPlayerTwo(readLine)

        println(helpMessage)
        println(field)
        
        gameLoop()
    }

    def gameLoop(): Unit = {
        var test = true
        while (true)
            val playerIndex = if (!test) 1 else 0
            println("Player " + controller.players(playerIndex).name + " (" + controller.players(playerIndex).stoneType + "):")
            print("> ")
            if (handleInput(readLine).isDefined) {
                println(field.toString)
                test = !test
            }
    }

    def handleInput(input: String): Option[Matrix] = {
        input match {
            case "q" | "quit" =>
                println("Bye!")
                System.exit(0)
                None
            case _ =>
                val command: Array[String] = input.split(" ")
                val cmd: String = command(0)
                val args: String = command(1)
                cmd match {
                    case "set" =>
                        val x = args(0).asDigit
                        val y = args(1).asDigit
                        val stone =
                            if (args(2).equals('X')) Stone.X
                            else if (args(2).equals('O')) Stone.O
                            else Stone.Empty
                        field = field.setStone(x, y, stone)
                        Some(field)
                    case "move" =>
                        val old_x = args(0).asDigit
                        val old_y = args(1).asDigit
                        val new_x = args(2).asDigit
                        val new_y = args(3).asDigit
                        field = field.moveStone(old_x, old_y, new_x, new_y)
                        Some(field)
                    case _ =>
                        println(s"invalid command: ${input}")
                        None
                }
        }
    }

    override def update: Unit = println(controller.field)

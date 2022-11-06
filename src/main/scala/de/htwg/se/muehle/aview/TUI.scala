package de.htwg.se.muehle
package aview

import model.Matrix
import model.Stone
import scala.io.StdIn.readLine

case class TUI():
    var field = new Matrix(7, Stone.Empty)
    def run = {
        println("---WELCOME TO MILL!---\n" +
                "Type 'set 33X' to place a stone X at the third row and third column.\n" +
                "Type 'move 2223' to move a stone from (row=2,col=2) to (row=2,col=3).\n" +
                field)
        gameLoop()
    }
    def gameLoop(): Unit = {
        while (true)
            print("> ")
            handleInput(readLine)
            println(field.toString)
    }
    def handleInput(input: String): Option[Matrix] = {
        // example: 'set 24X' <=> cmd="set", args=["2","4","X"]
        input match {
            /**case "q" =>
                println("Bye!")
                System.exit(0)
                None*/
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
                        println(s"Unvalid command: ${input}")
                        None
                }
        }
    }

package de.htwg.se.muehle
package aview

import model.Matrix
import model.Stone
import scala.io.StdIn.readLine

case class TUI():
    var field = new Matrix(7, Stone.Empty)
    def run = {
        println(field)
        gameLoop()
    }
    def gameLoop() = {
        while(true)
            print("> ")
            handleInput(readLine)
            println(field.toString)
    }
    def handleInput(input: String): Option[Matrix] = {
        input match {
            case "set" =>
                println("Type row (0-6)")
                val x = readLine.toInt
                println("Type column (0-6)")
                val y = readLine.toInt
                println("Type X or O")
                val input_stone = readLine
                val stone =
                    if (input_stone.equals("X")) Stone.X
                    else if (input_stone.equals("O")) Stone.O
                    else Stone.Empty
                field = field.setStone(x, y, stone) // TODO: auf Typ prüfen
                Some(field)
            case "move" =>
                println("Type old row (0-6)")
                val old_x = readLine.toInt
                println("Type old column (0-6)")
                val old_y = readLine.toInt
                println("Type new row (0-6)")
                val new_x = readLine.toInt
                println("Type new column (0-6)")
                val new_y = readLine.toInt
                field = field.moveStone(old_x, old_y, new_x, new_y)
                // set now empty cell to Stone.Empty
                field = field.removeStone(old_x, old_y)
                Some(field)
            case "q" =>
                println("Bye!")
                System.exit(0)
                None
            case _ =>
                println(s"Unvalid command: ${input}")
                None
        }
    }

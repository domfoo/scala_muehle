package de.htwg.se.muehle
package aview

import model.Move
import model.Stone
import model.Player
import scala.io.StdIn.readLine
import de.htwg.se.muehle.model.PlayField


class TUI():

    val field = new PlayField(3)
    val greeting = "-----WELCOME TO MILL-----"
    val playfield_info =
        "This is how the playfield looks like.\n\n" +
        "0 --------- 1 --------- 2"+ "\n" +
        "|           |           |"+ "\n" +
        "|   3 ----- 4 ----- 5   |"+ "\n" +
        "|   |       |       |   |"+ "\n" +
        "|   |   6 - 7 - 8   |   |"+ "\n" +
        "|   |   |       |   |   |"+ "\n" +
        "9 - 10- 11      12- 13- 14"+ "\n" +
        "|   |   |       |   |   |"+ "\n" +
        "|   |   15- 16- 17  |   |"+ "\n" +
        "|   |       |       |   |"+ "\n" +
        "|   18----- 19----- 20  |"+ "\n" +
        "|           |           |"+ "\n" +
        "21--------- 22--------- 23"+ "\n\n" +
        "Type X/O or x/o and two numbers to place a stone.\n" +
        "Press q to exit.\n"

    def run =
        println(greeting)
        println(playfield_info)
        gameLoop

    def gameLoop =
        while(true)
            print("> ")
            handleInput(readLine)
            print(field)

    def handleInput(input: String): Option[Move] =
        input match
            case "q" =>
                println("Bye!")
                System.exit(0)
                None
            case _ => {
                val chars = input.toCharArray
                val stone =  chars(0) match
                        case 'x' => Stone.X
                        case 'X' => Stone.X
                        case 'o' => Stone.O
                        case 'O' => Stone.O
                        case _   => Stone.Empty
                val x = chars(1).toInt
                val y = chars(2).toInt
                Some(Move(stone, x, y))
            }

    def update(player: Player, millsCount: Int, gameEnded: Boolean): String = {
        if (gameEnded)
            player.getName + " has won!"
        else if (millsCount > 0)
            player.getName + " has " + millsCount + " mills, remove " + millsCount + " stones!"
        else
            player.getName + ", it's your turn!"
    }

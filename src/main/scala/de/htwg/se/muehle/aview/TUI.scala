package de.htwg.se.muehle
package aview

import model.Move
import model.Stone
import model.Player
import model.Stones
import scala.io.StdIn.readLine
import de.htwg.se.muehle.model.PlayField


class TUI():

    val field = new PlayField(3)
    val mills = new Stones().stones
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
        "Please type set OR move.\n" +
        "Press q to exit.\n"
    val playfield =
        "\n%s-----------%s-----------%s\n"+
        "|           |           |\n"+
        "|   %s-------%s-------%s   |\n"+
        "|   |       |       |   |\n"+
        "|   |   %s---%s---%s   |   |\n"+
        "|   |   |       |   |   |\n"+
        "%s---%s---%s       %s---%s---%s\n"+
        "|   |   |       |   |   |\n"+
        "|   |   %s---%s---%s   |   |\n"+
        "|   |       |       |   |\n"+
        "|   %s-------%s-------%s   |\n"+
        "|           |           |\n"+
        "%s-----------%s-----------%s\n"
    val log = playfield.format(mills(0),mills(1),mills(2),mills(3),mills(4),mills(5),
                            mills(6),mills(7),mills(8),mills(9),mills(10),mills(11),
                            mills(12),mills(13),mills(14),mills(15),mills(16),mills(17),
                            mills(18),mills(19),mills(20),mills(21),mills(22),mills(23))

    def run =
        println(greeting)
        println(playfield_info)
        gameLoop

    def gameLoop =
        while(true)
            print("> ")
            handleInput(readLine)
            print(log)


    def handleInput(input: String): Move =
        input match
            case "q" =>
                println("Bye!")
                System.exit(0)
                Move()
            case "set" =>
                println("Type a number from 0-23")
                val newPos = getPosition()
                Move()
            case "move" =>
                println("Type old position (0-23)")
                val oldPos = getPosition()
                println("Type new position (0-23)")
                val newPos = getPosition()
                Move()

    def getPosition(): Option[Int] = {
        readLine().toInt match
            case n: Int if n >= 0 && n <= 23 =>
                Some(n)
            case _ =>
                println("Error: number has to be from 0-23!")
                None
    }

    /**def update(player: Player, millsCount: Int, gameEnded: Boolean): String = {
        if (gameEnded)
            player.getName + " has won!"
        else if (millsCount > 0)
            player.getName + " has " + millsCount + " mills, remove " + millsCount + " mills!"
        else
            player.getName + ", it's your turn!"
    }*/

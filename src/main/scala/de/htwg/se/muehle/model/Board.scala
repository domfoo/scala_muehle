package de.htwg.se.muehle.model

case class Board(stones: Matrix):
    val size = stones.size
    def isValidPosition(row: Int, col: Int): Boolean = {
        stones.isValidCell(row, col)
    }
    def cell(row: Int, col: Int): Stone = {
        stones.getCell(row, col)
    }
    override def toString: String = {
        "\n" +
        (0 until size).map(
            x => (0 until size).map(
                y => if (isValidPosition(x, y)) s" ${this.cell(x,y)} " else "   ")
                .mkString(""))
            .mkString("\n")
        + "\n"
    }

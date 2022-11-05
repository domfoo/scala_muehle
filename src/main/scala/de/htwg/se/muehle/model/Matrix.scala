package de.htwg.se.muehle.model

case class Matrix(matrix: Vector[Vector[Stone]]):
    def this(size: Int, filling: Stone) = {
        this(Vector.tabulate(size, size) {(_, _) => filling})
    }
    val size = matrix.size
    val cells = List(
    (0, 0), (0, 3), (0, 6), (1, 1), (1, 3), (1, 5),
    (2, 2), (2, 3), (2, 4), (3, 0), (3, 1), (3, 2),
    (3, 4), (3, 5), (3, 6), (4, 2), (4, 3), (4, 4),
    (5, 1), (5, 3), (5, 5), (6, 0), (6, 3), (6, 6)
    )
    def getCell(row: Int, col: Int): Stone = {
        matrix(row)(col)
    }
    def isValidCell(row: Int, col: Int): Boolean = {
        cells.contains((row, col))
    }
    def replaceCell(row: Int, col: Int, cell: Stone): Matrix = {
        copy(matrix.updated(row, matrix(row).updated(col, cell)))
    }
    def checkIfEmpty(x: Int, y: Int): Boolean = {
        if (getCell(x, y) == Stone.Empty) true else false
    }
    def setStone(x: Int, y: Int, stone: Stone): Option[Matrix] = {
        if (checkIfEmpty(x, y))
            Some(replaceCell(x, y, stone))
        else
            println("Cell is not empty!")
            None
    }
    def moveStone(old_x: Int, old_y: Int, new_x: Int, new_y: Int): Matrix = {
        val moving_stone = getCell(old_x, old_y)
        replaceCell(new_x, new_y, moving_stone)
    }
    def removeStone(x: Int, y: Int): Matrix = {
        replaceCell(x, y, Stone.Empty)
    }
    override def toString: String = {
        "\n" +
        "    0  1  2  3  4  5  6\n" +
        "   _____________________\n" +
        (0 until size).map(
            x => (0 until size).map(
                y => if (isValidCell(x, y)) s" ${getCell(x,y)} " else "   ")
                .mkString(s"$x |", "", ""))
            .mkString("\n")
        + "\n"
    }

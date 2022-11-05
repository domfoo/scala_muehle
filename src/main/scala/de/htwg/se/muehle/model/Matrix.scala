package de.htwg.se.muehle.model

case class Matrix(matrix: Vector[Vector[Stone]]):
    def this(size: Int, filling: Stone) = {
        this(Vector.tabulate(size, size) {(_, _) => filling})
    }

    val size = matrix.size
    // coordinates of all cells in form of (row,col)
    val cells = List(
    (0, 0), (0, 3), (0, 6), (1, 1), (1, 3), (1, 5),
    (2, 2), (2, 3), (2, 4), (3, 0), (3, 1), (3, 2),
    (3, 4), (3, 5), (3, 6), (4, 2), (4, 3), (4, 4),
    (5, 1), (5, 3), (5, 5), (6, 0), (6, 3), (6, 6)
    )
    // a list of all neighbours for each cell
    val neighbours = Map(
    (0, 0) -> Set((0, 3), (3, 0)),
    (0, 3) -> Set((0, 0), (0, 6), (1, 3)),
    (0, 6) -> Set((0, 3), (3, 6)),
    (1, 1) -> Set((1, 3), (3, 1)),
    (1, 3) -> Set((1, 1), (1, 5), (0, 3), (2, 3)),
    (1, 5) -> Set((1, 3), (3, 5)),
    (2, 2) -> Set((3, 2), (2, 3)),
    (2, 3) -> Set((2, 2), (2, 4), (1, 3)),
    (2, 4) -> Set((2, 3), (3, 4)),
    (3, 0) -> Set((0, 0), (6, 0), (3, 1)),
    (3, 1) -> Set((3, 0), (3, 2), (1, 1), (5, 1)),
    (3, 2) -> Set((2, 2), (4, 2), (3, 1)),
    (3, 4) -> Set((2, 4), (4, 4), (3, 5)),
    (3, 5) -> Set((3, 4), (3, 6), (1, 5), (5, 5)),
    (3, 6) -> Set((0, 6), (6, 6), (3, 5)),
    (4, 2) -> Set((3, 2), (4, 3)),
    (4, 3) -> Set((4, 2), (4, 4), (5, 3)),
    (4, 4) -> Set((4, 3), (3, 4)),
    (5, 1) -> Set((3, 1), (5, 3)),
    (5, 3) -> Set((5, 1), (5, 5), (4, 3), (6, 3)),
    (5, 5) -> Set((5, 3), (3, 5)),
    (6, 0) -> Set((3, 0), (6, 3)),
    (6, 3) -> Set((6, 0), (6, 6), (5, 3)),
    (6, 6) -> Set((6, 3), (3, 6)))

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
    def setStone(x: Int, y: Int, stone: Stone): Matrix = {
        if (checkIfEmpty(x, y))
            replaceCell(x, y, stone)
        else
            println("\nError: Cell is already occupied!")
            this
    }
    def checkMove(old_x: Int, old_y: Int, new_x: Int, new_y: Int): Boolean = {
        // check if new cell is a neighbour to old cell and if new cell is not occupied
        if (neighbours(old_x, old_y).contains((new_x, new_y)) &&
            getCell(new_x, new_y) == Stone.Empty)
            true
        else
            false
    }
    def moveStone(old_x: Int, old_y: Int, new_x: Int, new_y: Int): Matrix = {
        val moving_stone = getCell(old_x, old_y)
        // check if move is okay and if old cell is occupied
        if (checkMove(old_x, old_y, new_x, new_y) && getCell(old_x, old_y) != Stone.Empty)
            // set old cell to Stone.Empty and move stone to new cell
            copy().removeStone(old_x, old_y).replaceCell(new_x, new_y, moving_stone)
        else
            println("\nError: Old cell is empty, new cell is already occupied or new cell is not a neighbour of old cell!")
            this
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

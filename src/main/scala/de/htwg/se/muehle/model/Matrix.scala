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
    def replaceCell(x1: Int, x2: Int, elem: Stone): Matrix = {
        copy(matrix.updated(x1, matrix(x1).updated(x2, elem)))
    }

package de.htwg.se.muehle.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {

    "A Matrix is a immutable data type that contains a two-dimensional vector of Stones. A Matrix" when {
        "empty " should {
            "be created by using a size and a Stone as filling" in {
                val matrix = new Matrix(3, Stone.X)
                matrix.size should be(3)
            }
            "for test purposes can only be created with a size of 7 and Stone.Empty as filling" in {
                val testMatrix = new Matrix(7, Stone.Empty)
            }
        }
        "filled" should {
            val matrix = new Matrix(7, Stone.Empty)
            "give access to its cells" in {
                matrix.getCell(0, 0) should be(Stone.Empty)
            }
            "check if a cell is a valid cell" in {
                val x = (0, 1)
                val y = (2, 3)
                matrix.isValidCell(x._1, x._2) should be(false)
                matrix.isValidCell(y._1, y._2) should be(true)
            }
            "replace cells and return a new Matrix" in {
                val newMatrix = new Matrix(7, Stone.Empty).replaceCell(0, 0, Stone.X)
                matrix.getCell(0, 0) should be(Stone.Empty)
                newMatrix.getCell(0, 0) should be(Stone.X)
            }
            "check if a cell is empty" in {
                val matrix = new Matrix(7, Stone.Empty).setStone(0, 0, Stone.O)
                matrix.checkIfEmpty(0, 0) should be(false)
                matrix.checkIfEmpty(0, 1) should be(true)
            }
            "set a stone if cell is valid and empty" in {
                val matrix = new Matrix(7, Stone.Empty).setStone(0, 0, Stone.O)
                matrix.getCell(0, 0) should be(Stone.O)
                matrix.setStone(0, 0, Stone.X) should be(matrix)
                matrix.setStone(0, 1, Stone.X) should be(matrix)
            }
            "check if a stone can move from one cell to another" in {
                val matrix = new Matrix(7, Stone.Empty)
                matrix.checkMove(0, 0, 0, 3) should be(true)
                matrix.checkMove(0, 0, 2, 3) should be(false)
                matrix.setStone(0, 3, Stone.O).checkMove(0, 0, 0, 3) should be(false)
            }
            "move a stone from one cell to another if move is valid and old cell is not empty" in {
                val matrix = new Matrix(7, Stone.Empty).setStone(0, 0, Stone.X)
                val matrix2 = matrix.moveStone(0, 0, 0, 3)
                matrix2.getCell(0, 0) should be(Stone.Empty)
                matrix2.getCell(0, 3) should be(Stone.X)
                matrix.moveStone(0, 0, 6, 6) should be(matrix) // case: cell is not a neighbour
                matrix.moveStone(3, 0, 6, 0) should be(matrix) // case: old cell is empty
            }
            "remove a stone" in {
                val matrix = new Matrix(7, Stone.Empty).setStone(0, 0, Stone.X)
                matrix.removeStone(0, 0).getCell(0, 0) should be(Stone.Empty)
            }
            "return a string representation of the game field" in {
                matrix.toString should be(
                    "\n" +
                    "    0  1  2  3  4  5  6\n" +
                    "   _____________________\n" +
                    "0 | +        +        + "+"\n"+
                    "1 |    +     +     +    "+"\n"+
                    "2 |       +  +  +       "+"\n"+
                    "3 | +  +  +     +  +  + "+"\n"+
                    "4 |       +  +  +       "+"\n"+
                    "5 |    +     +     +    "+"\n"+
                    "6 | +        +        + "+"\n"
                )
            }
        }
    }
}
package de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl

import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import scala.collection.immutable.SortedMap
import com.google.inject.name.Named
import com.google.inject.{Guice, Inject}


case class Field @Inject() (cells: SortedMap[Int, Stone]) extends IField:

    // a map of all neighbours for each cell
    val neighbours = Map(
        1 -> Set(2, 10),
        2 -> Set(1, 3, 5),
        3 -> Set(2, 15),
        4 -> Set(5, 11),
        5 -> Set(2, 4, 6, 8),
        6 -> Set(5, 14),
        7 -> Set(8, 12),
        8 -> Set(5, 7, 9),
        9 -> Set(8, 13),
        10 -> Set(1, 11, 22),
        11 -> Set(4, 10, 12, 19),
        12 -> Set(7, 11, 16),
        13 -> Set(9, 14, 18),
        14 -> Set(6, 13, 15, 21),
        15 -> Set(3, 14, 24),
        16 -> Set(12, 17),
        17 -> Set(16, 18, 20),
        18 -> Set(9, 17),
        19 -> Set(11, 20),
        20 -> Set(17, 19, 21, 23),
        21 -> Set(14, 20),
        22 -> Set(10, 23),
        23 -> Set(20, 22, 24),
        24 -> Set(15, 23)
    )

    /// a list containing all possible mill combinations
    val millNeighbours = List(
        Set(1, 2, 3),
        Set(4, 5, 6),
        Set(7, 8, 9),
        Set(10, 11, 12),
        Set(13, 14, 15),
        Set(16, 17, 18),
        Set(19, 20, 21),
        Set(22, 23, 24),
        Set(1, 10, 22),
        Set(4, 11, 19),
        Set(7, 12, 16),
        Set(2, 5, 8),
        Set(17, 20, 23),
        Set(9, 13, 18),
        Set(6, 14, 21),
        Set(3, 15, 24)
    )
    
    val eol = sys.props("line.separator")
    val fieldNumberOverview = "1-----------2-----------3" + eol +
        "|           |           |" + eol +
        "|   4-------5-------6   |" + eol +
        "|   |       |       |   |" + eol +
        "|   |   7---8---9   |   |" + eol +
        "|   |   |       |   |   |" + eol +
        "10--11--12      13--14--15" + eol +
        "|   |   |       |   |   |" + eol +
        "|   |   16--17--18  |   |" + eol +
        "|   |       |       |   |" + eol +
        "|   19------20------21  |" + eol +
        "|           |           |" + eol +
        "22----------23----------24" + eol

    val size = cells.size / 8
    val fieldRange = (1 to size * 8)

    override def replaceCell(position: Int, stone: Stone): Field =
        Field(cells.updated(position, stone))

    override def cleanCell(position: Int): Field =
        replaceCell(position, Stone.Empty)

    override def isEmptyCell(position: Int): Boolean =
        cells(position) == Stone.Empty

    override def isMovableToPosition(oldPosition: Int, newPosition: Int, stone: Stone): Boolean =
        neighbours(oldPosition).contains(newPosition) && isEmptyCell(newPosition) && cells(oldPosition) == stone
        
    // checks if a row or column contains only stones of the same type
    override def isSetOfStone(set: Set[Int], stone: Stone): Boolean =
        set.map(cells).filterNot(_ == stone).size == 0

    // checks if a move produces a mill
    override def isFullMill(position: Int, stone: Stone) =
        millNeighbours.filter(_ contains position).filter(isSetOfStone(_, stone)).size == 1

    // methods for printing the field as a string
    override def line(i: Int): String =
        "|   " * (size - 1 - i ) + "#" + "-" * (4 * i + 3) + "#" + "-" * (4 * i + 3) + "#" + "   |" * (size - 1 - i ) + eol
    override def space(i: Int): String =
        "|   " * (size - 1 - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (size - 1 - i ) + eol
    override def middle(): String =
        "#---" * (size - 1) + "#" + " " * 7 + "#" + "---#" * (size - 1) + eol
    override def fieldPlaceholder(): String =
        ((size - 1) to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("") +
        middle() +
        (0 until size).map((x: Int) => space(x) + line(x)).mkString("")

    override def toString: String = {
        if (size < 1) return "" + eol
        (fieldPlaceholder().split("#").zipAll(cells.values,"","") flatMap { case (a, b) => Seq(a, b) }).mkString("")
    }

// companion object containing factory methods for creating a field instance
object Field:
    def apply(cells: SortedMap[Int, Stone]): Field = new Field(cells)
    def apply(size: Int = 3): Field = new Field(SortedMap((1 to size * 8).map(k => (k -> Stone.Empty)).toSeq:_*))
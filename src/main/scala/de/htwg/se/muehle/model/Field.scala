package de.htwg.se.muehle.model

import scala.collection.immutable.SortedMap

// size is the number of size of the mill field. Must be bigger than 0
case class Field(cells: SortedMap[Int, Stone]):
    def this(size: Int = 3) = this(SortedMap((1 to size * 8).map(k => (k -> Stone.Empty)).toSeq:_*))

    // a Map of all neighbours for each cell
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
    
    def isEmpty(position: Int): Boolean =
        cells(position) == Stone.Empty

    def replaceCell(position: Int, stone: Stone): Field =
        Field(cells.updated(position, stone))
        
    def removeStone(position: Int): Field =
        replaceCell(position, Stone.Empty)
            
    def isMovable(oldPosition: Int, newPosition: Int): Boolean =
        neighbours(oldPosition).contains(newPosition) && cells(newPosition) == Stone.Empty && cells(oldPosition) != Stone.Empty
        
    def setStone(position: Int, stone: Stone): Option[Field] = 
        if (fieldRange.contains(position) && isEmpty(position))
            Some(replaceCell(position, stone))
        else 
            None

    def moveStone(oldPosition: Int, newPosition: Int): Option[Field] =
        if (isMovable(oldPosition, newPosition))
            Some(removeStone(oldPosition).replaceCell(newPosition, cells(oldPosition)))
        else
            None

    def line(i: Int): String = 
        "|   " * (size - 1 - i ) + "#" + "-" * (4 * i + 3) + "#" + "-" * (4 * i + 3) + "#" + "   |" * (size - 1 - i ) + eol
    def space(i: Int): String = 
        "|   " * (size - 1 - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (size - 1 - i ) + eol
    def middle(): String = 
        "#---" * (size - 1) + "#" + " " * 7 + "#" + "---#" * (size - 1) + eol
    def fieldPlaceholder(): String =
        ((size - 1) to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("") +
        middle() +
        (0 until size).map((x: Int) => space(x) + line(x)).mkString("")

    override def toString: String = {
        if (size < 1) return "" + eol
        (fieldPlaceholder().split("#").zipAll(cells.values,"","") flatMap { case (a, b) => Seq(a, b) }).mkString("")
    }


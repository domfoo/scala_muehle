package de.htwg.se.muehle.model

// size is the number of size of the mill field. Must be bigger than 0
case class Field(size: Int = 3):
    val eol = sys.props("line.separator")
    val fieldRange = (0 until size * 8)
    val cells = fieldRange.map(k => Stone.Empty).toList

    // a list of all neighbours for each cell
    val neighbours = Map(
        1 -> Set(2, 8, 9),
        2 -> Set(1, 3),
        3 -> Set(2, 4, 11),
        4 -> Set(3, 6),
        5 -> Set(4, 6, 13),
        6 -> Set(5, 7),
        7 -> Set(6, 8, 15),
        8 -> Set(7, 9),
        9 -> Set(1, 10, 16, 17),
        10 -> Set(9, 11),
        11 -> Set(3, 10, 12, 19),
        12 -> Set(11, 13),
        13 -> Set(5, 12, 14, 21),
        14 -> Set(13, 15),
        15 -> Set(7, 14, 16, 23),
        16 -> Set(15, 17),
        17 -> Set(9, 18, 24),
        18 -> Set(17, 19),
        19 -> Set(11, 18, 20),
        20 -> Set(19, 21),
        21 -> Set(13, 20, 22),
        22 -> Set(21, 23),
        23 -> Set(15, 22, 24),
        24 -> Set(17, 23)
    )

    
    def line(i: Int): String = 
        "|   " * (size - 1 - i ) + "#" + "-" * (4 * i + 3) + "#" + "-" * (4 * i + 3) + "#" + "   |" * (size - 1 - i ) + eol
    def space(i: Int): String = 
        "|   " * (size - 1 - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (size - 1 - i ) + eol
    def middle(): String = 
        "#---" * (size - 1) + "#" + " " * 7 + "#" + "---#" * (size - 1) + eol
    def fieldPlaceholder(): String =
        ((size - 1) to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("") + middle() + (0 until size).map((x: Int) => space(x) + line(x)).mkString("")

    override def toString: String = {
        if (size < 1) return "" + eol
        (fieldPlaceholder().split("#").zipAll(cells,"","") flatMap { case (a, b) => Seq(a, b) }).mkString("")
    }


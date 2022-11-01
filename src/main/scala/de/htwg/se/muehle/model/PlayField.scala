package de.htwg.se.muehle.model

case class PlayField(val rings: Int = 3) {
    /**
      * Creates a String that represents a mill play field.
      * Returns an empty String for a ring size less than 1
      *
      * @param rings is the number of rings that the play field should have.
      * @return the play field
      */
    val eol = sys.props("line.separator")

    def line(i: Int): String =
        "|   " * (rings - 1 - i ) + "+" + "-" * (4 * i + 3) + "+" + "-" * (4 * i + 3) + "+" + "   |" * (rings - 1 - i ) + eol
    def space(i: Int): String =
        "|   " * (rings - 1 - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (rings - 1 - i ) + eol
    def middle(): String =
        "+---" * (rings - 1) + "+" + " " * 7 + "+" + "---+" * (rings - 1) + eol

    override def toString(): String = {
        if (rings < 1) return "" + eol
        ((rings - 1) to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("") + middle() + (0 until rings).map((x: Int) => space(x) + line(x)).mkString("")
    }
}



package de.htwg.se.muehle

case class PlayField() {
    /**
      * Creates a String that represents a mill play field.
      * Returns an empty String for a ring size less than 1
      *
      * @param rings is the number of rings that the play field should have.
      * @return the play field
      */
    def fieldAsString(rings: Int = 3): String = {
        val eol = sys.props("line.separator")

        if (rings < 1) {
            println("number of rings has to be > 0")
            return ""
        }

        val scale = rings - 1
        def line(i: Int): String = "|   " * (scale - i ) + "+" + "-" * (4 * i + 3) + "+" + "-" * (4 * i + 3) + "+" + "   |" * (scale - i ) + eol
        def space(i: Int): String = "|   " * (scale - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (scale - i ) + eol
        def middle(): String = "+---" * scale + "+" + " " * 7 + "+" + "---+" * scale + eol

        (scale to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("")
        + middle()
        + (0 until scale + 1).map((x: Int) => space(x) + line(x)).mkString("")
    }
}



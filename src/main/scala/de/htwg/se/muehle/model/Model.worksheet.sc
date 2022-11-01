val eol = sys.props("line.separator")

def test(rings: Int): String = {
    if (rings < 1) return ""
    val scale = rings - 1
    def space(i: Int): String = "|   " * (scale - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (scale - i ) + eol
    def line(i: Int): String = "|   " * (scale - i ) + "+" + "-" * (4 * i + 3) + "+" + "-" * (4 * i + 3) + "+" + "   |" * (scale - i ) + eol
    def middle(): String = "+---" * scale + "+" + " " * 7 + "+" + "---+" * scale + eol

    (scale to 0 by -1).map((x: Int) => line(x) + space(x)).mkString("")
        + middle()
        + (0 until scale + 1).map((x: Int) => space(x) + line(x)).mkString("")
}

test(3)

/*
def fieldAsString(): String = {
    "+-----------+-----------+" + eol +
    "|           |           |" + eol +
    "|   +-------+-------+   |" + eol +
    "|   |       |       |   |" + eol +
    "|   |   +---+---+   |   |" + eol +
    "|   |   |       |   |   |" + eol +
    "+---+---+       +---+---+" + eol +
    "|   |   |       |   |   |" + eol +
    "|   |   +---+---+   |   |" + eol +
    "|   |       |       |   |" + eol +
    "|   +-------+-------+   |" + eol +
    "|           |           |" + eol +
    "+-----------+-----------+" + eol
}
*/
val rings = 3
val number = 8
val mills = (0 until rings*number).toList
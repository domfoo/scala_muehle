
val eol = sys.props("line.separator")

def test(rings: Int): String = {
    var res = ""
    if (rings < 1) return res
    val scale = rings - 1
    def space(i: Int): String = "|   " * (scale - i ) + "|" + " " * (4 * i + 3) + (if (i == 0) " " else "|") + " " * (4 * i + 3) + "|" + "   |" * (scale - i ) + eol
    def line(i: Int): String = "|   " * (scale - i ) + "+" + "-" * (4 * i + 3) + "+" + "-" * (4 * i + 3) + "+" + "   |" * (scale - i ) + eol
    def middle(): String = "+---" * scale + "+" + " " * 7 + "+" + "---+" * scale + eol

    for (i <- scale to 0 by -1) {
        res += line(i) + space(i)
    }

    res += middle()

    for (i <- 0 to scale by 1) {
        res += space(i) + line(i)
    }

    res
}
/*
def test(rings: Int): String = {
    if (rings < 1) return ""
    val scale = rings - 1
    
    (scale until 0).map()
}
*/

print(test(5))

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
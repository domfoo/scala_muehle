case class PlayField() {
    private val eol = sys.props("line.separator")
    
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
}



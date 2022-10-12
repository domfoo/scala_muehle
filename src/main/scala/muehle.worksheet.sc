
val eol = sys.props("line.separator")

def line(scale: Int = 0) = "+" + "-" * (2 + scale * 3)
def fill(scale: Int = 0) = "|" + " " * (2 + scale * 3)

def test(): String = {
    line(2) + "+" + line(2).reverse + eol +
    fill(2) + "|" + fill(2).reverse + eol +
    fill() + line(1) + "+" + line(1).reverse + fill().reverse + eol +
    fill() + fill(1) + "|" + fill(1).reverse + fill().reverse + eol +
    fill() + fill() + line() + "+" + line().reverse + fill() + eol +
    fill() + fill() + fill() + " " + fill().reverse + fill().reverse + fill().reverse + eol

}

def test2(): String = {
    "+--------+--------+" + eol +
    "|        |        |" + eol +
    "|  +-----+-----+  |" + eol +
    "|  |     |     |  |" + eol +
    "|  |  +--+--+  |  |" + eol +
    "|  |  |     |  |  |" + eol +
    "+--+--+     +--+--+" + eol +
    "|  |  |     |  |  |" + eol +
    "|  |  +--+--+  |  |" + eol +
    "|  |     |     |  |" + eol +
    "|  +-----+-----+  |" + eol +
    "|        |        |" + eol +
    "+--------+--------+" + eol
}



print(fill())
print(line())
print(test2())


/*
"+--------+--------+" + eol +
"|        |        |" + eol +
"|  +-----+-----+  |" + eol +
"|  |     |     |  |" + eol +
"|  |  +--+--+  |  |" + eol +
"|  |  |     |  |  |" + eol +
"+--+--+     +--+--+" + eol +
"|  |  |     |  |  |" + eol +
"|  |  +--+--+  |  |" + eol +
"|  |     |     |  |" + eol +
"|  +-----+-----+  |" + eol +
"|        |        |" + eol +
"+--------+--------+" + eol
*/




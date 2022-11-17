[![Build Status](https://github.com/domfoo/scala_muehle/actions/workflows/scala.yml/badge.svg)](https://github.com/domfoo/scala_muehle/actions/workflows/scala.yml)
[![Coverage Status](https://coveralls.io/repos/github/domfoo/scala_muehle/badge.svg?branch=master)](https://coveralls.io/github/domfoo/scala_muehle?branch=master)

# scala_muehle
The game [Nine men's morris](https://en.wikipedia.org/wiki/Nine_men%27s_morris) as software project for Software Engineering at HTWG Konstanz

## Command Line Interface
Start a new game by running
```
sbt run
```
Once both players' names are entered, a help message can be displayed by typing
```
> help

Type 'set 3' to place a stone on the third position.
Type 'move 2 3' to move a stone from position 2 to position 3.
Type 'h' or 'help' for this help message.
Type 'q' or 'quit' to close the game.
1-----------2-----------3
|           |           |
|   4-------5-------6   |
|   |       |       |   |
|   |   7---8---9   |   |
|   |   |       |   |   |
10--11--12      13--14--15
|   |   |       |   |   |
|   |   16--17--18  |   |
|   |       |       |   |
|   19------20------21  |
|           |           |
22----------23----------24
``` 
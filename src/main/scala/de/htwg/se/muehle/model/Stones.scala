package de.htwg.se.muehle.model

case class Stones() {
    val numberVertexes = 24
    val stones = (0 until numberVertexes).map(i => Stone.X).toList
    val neighbors = Vector(
        Vector(1, 9),
        Vector(0, 2, 4),
        Vector(1, 14),
        Vector(4, 10),
        Vector(1, 3, 5, 7),
        Vector(4, 13),
        Vector(7, 11),
        Vector(4, 6, 8),
        Vector(7, 12),
        Vector(0, 21),
        Vector(3, 9, 11, 18),
        Vector(6, 10, 15),
        Vector(8, 13, 17),
        Vector(5, 12, 14, 20),
        Vector(2, 13, 23),
        Vector(11, 16),
        Vector(15, 17, 19),
        Vector(12, 16),
        Vector(10, 19),
        Vector(16, 18, 20, 22),
        Vector(13, 19),
        Vector(9, 22),
        Vector(21, 19, 23),
        Vector(14, 22)
    )
}

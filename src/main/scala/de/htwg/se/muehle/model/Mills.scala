package de.htwg.se.muehle.model

class Mills {
    val numberVertexes = 24
    var millsArray = new Array[MillsList](numberVertexes)
    (0 to millsArray.length).map(i => millsArray(i) = new MillsList())
}

private class MillsList {
    var millList1 : List[Int] = List()
    var millList2 : List[Int] = List()
}
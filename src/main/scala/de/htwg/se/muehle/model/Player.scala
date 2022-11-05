package de.htwg.se.muehle.model

case class Player(name: String, stones: Int):
    def this(name: String) = {
        this(name = name, stones = 9)
    }
    override def toString: String = s"Name: $name, stones left: $stones"

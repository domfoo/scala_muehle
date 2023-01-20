package de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl

import scala.xml.Elem

enum Stone(stoneAsString: String):
    override def toString = stoneAsString        
    def toXml(): Elem = <stone>{ stoneAsString }</stone>
    case X extends Stone("X")
    case O extends Stone("O")
    case Empty extends Stone("+")

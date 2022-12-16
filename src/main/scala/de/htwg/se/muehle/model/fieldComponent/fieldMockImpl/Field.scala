package de.htwg.se.muehle.model.fieldComponent.fieldMockImpl

import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import scala.collection.immutable.SortedMap

case class Field(var cells: SortedMap[Int, Stone]) extends IField:
    
    cells = SortedMap()
    
    override def replaceCell(position: Int, stone: Stone): IField = this
    override def cleanCell(position: Int): IField = this
    override def isEmptyCell(position: Int): Boolean = false
    override def isMovableToPosition(oldPosition: Int, newPosition: Int, stone: Stone): Boolean = false
    
    override def isSetOfStone(set: Set[Int], stone: Stone): Boolean = false
    override def isFullMill(position: Int, stone: Stone): Boolean = false
    //override def isMill(pos: Int, stone: Stone): Boolean = false
    override def line(i: Int): String = ""
    override def space(i: Int): String = ""
    override def middle(): String = ""
    override def fieldPlaceholder(): String = ""
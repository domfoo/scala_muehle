package de.htwg.se.muehle.model.fieldComponent

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import scala.xml.Elem
import play.api.libs.json.*

trait IField:
    def replaceCell(position: Int, stone: Stone): IField
    def cleanCell(position: Int): IField
    def isEmptyCell(position: Int): Boolean
    def isMovableToPosition(oldPosition: Int, newPosition: Int, stone: Stone): Boolean
    def isSetOfStone(set: Set[Int], stone: Stone): Boolean
    def isFullMill(position: Int, stone: Stone): Boolean
    //def isMill(pos: Int, stone: Stone): Boolean
    def line(i: Int): String
    def space(i: Int): String 
    def middle(): String
    def fieldPlaceholder(): String
    def toXml(): Elem
    def toJson(): JsValue
    override def toString: String

package de.htwg.se.muehle.model.fieldComponent

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone

trait IField:
    def isSetOfStone(set: Set[Int], stone: Stone): Boolean
    def isFullMill(position: Int, stone: Stone): Unit
    def replaceCell(position: Int, stone: Stone): IField
    def cleanCell(position: Int): IField
    def isEmptyCell(position: Int): Boolean
    def isMovableToPosition(oldPosition: Int, newPosition: Int, stone: Stone): Boolean
    def line(i: Int): String
    def space(i: Int): String 
    def middle(): String
    def fieldPlaceholder(): String
    override def toString: String

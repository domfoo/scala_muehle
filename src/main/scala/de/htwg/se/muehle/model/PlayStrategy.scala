package de.htwg.se.muehle
package model

trait PlayStrategy:
    def execute(field: Field): Field

case class Move(oldPosition: Int, newPosition: Int, stone: Stone) extends PlayStrategy:
    override def execute(field: Field): Field = field.cleanCell(oldPosition).replaceCell(newPosition, stone)

case class Put(newPosition: Int, stone: Stone) extends PlayStrategy:
    override def execute(field: Field): Field = field.replaceCell(newPosition, stone)
package de.htwg.se.muehle.model.playerComponent

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import scala.xml.Elem
import play.api.libs.json.*

case class Player(name: String, stoneType: Stone, var stones: Int = 9):
    override def toString: String = name
    override def equals(player: Any): Boolean = {
        player match {
            case p:Player => p.name.equals(name) && p.stones.equals(stones)
            case _ => false
        }
    }
    def toXml(): Elem = <player><name>{ this.name }</name><stoneType>{ this.stoneType }</stoneType><stones>{ this.stones }</stones></player>
    def toJson(): JsObject = 
        Json.obj(
            "player" -> Json.obj(
                "name" -> name,
                "stoneType" -> stoneType.toJson(),
                "stones" -> stones
            )
        )
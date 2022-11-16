package de.htwg.se.muehle.model

case class Move(stone: Stone, oldPosition: Option[Int], newPosition: Int)
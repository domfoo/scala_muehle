package de.htwg.se.muehle.model.fileIO.fileIOMockImpl

import de.htwg.se.muehle.model.fileIO.IFileIO
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import de.htwg.se.muehle.model.playerComponent.Player
import scala.util.Try
import scala.collection.immutable.SortedMap
import play.api.libs.json.*
import java.io._
import scala.io.Source

class FileIO extends IFileIO:
    override def save(field: Field, currentPlayer: Player): Unit = ()
    override def load(): (Field, Player) = (Field(), Player("Max", Stone.X))

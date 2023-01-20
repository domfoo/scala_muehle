package de.htwg.se.muehle.model.fileIO.fileIOJSON

import de.htwg.se.muehle.model.fileIO.IFileIO
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.playerComponent.Player

import play.api.libs.json.*
import java.io._

class FileIO extends IFileIO:
    override def save(field: Field, currentPlayer: Player): Unit = 
        val pw = new PrintWriter(new File("muehle.json"))
        val json = Json.obj(
            "muehle" -> Json.obj(
                "currentPlayer" -> currentPlayer.toJson(),
                "field" -> field.toJson(),
            )
        )
        pw.write(Json.prettyPrint(json))
        pw.close

    override def load(): (Field, Player) = ???

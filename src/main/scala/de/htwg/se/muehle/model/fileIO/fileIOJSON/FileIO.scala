package de.htwg.se.muehle.model.fileIO.fileIOJSON

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

    override def load(): (Field, Player) = 
        val source: String = Source.fromFile("muehle.json").getLines.mkString
        val json: JsValue = Json.parse(source)

        val cells = (json \ "muehle" \ "field" \\ "cell")

        val field = Field(
            SortedMap(
                cells.map(cell => (Try((cell \ "index").get.toString.toInt).get -> Stone.valueOf((cell \ "stone").get.as[String].replace("+", "Empty")))).toSeq:_*
            )
        )

        val p = json \ "muehle" \ "currentPlayer" \ "player"
        val player = Player(
            (p \ "name").get.as[String], 
            Stone.valueOf((p \ "stoneType").get.as[String]), 
            Try((p \ "stones").get.toString.toInt).get
        )

        (field, player)

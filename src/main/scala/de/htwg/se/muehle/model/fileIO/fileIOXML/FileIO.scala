package de.htwg.se.muehle.model.fileIO.fileIOXML

import de.htwg.se.muehle.model.fileIO.IFileIO
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import scala.util.Try
import scala.collection.immutable.SortedMap
import java.io._

class FileIO extends IFileIO:
    override def save(field: Field, currentPlayer: Player): Unit = 
        val pw = new PrintWriter(new File("muehle.xml" ))
        val xml = <muehle>{ currentPlayer.toXml() }{ field.toXml() }</muehle>
        val prettyPrinter = new scala.xml.PrettyPrinter(80, 2)
        val prettyXml = prettyPrinter.format(xml)
        pw.write(prettyXml)
        pw.close

    override def load(): (Field, Player) = 
        val file = scala.xml.XML.loadFile("muehle.xml")
        val field = Field(
            SortedMap(
                (file \\ "field" \\ "cell").map(cell => (Try((cell \\ "index").text.toInt).get -> Stone.valueOf((cell \\ "stone").text.replace("+", "Empty")))).toSeq:_*
            )
        )

        val p = file \\ "player"
        val player = Player((p \\ "name").text, Stone.valueOf((p \\ "stoneType").text), Try((p \\ "stones").text.toInt).get)
        (field, player)

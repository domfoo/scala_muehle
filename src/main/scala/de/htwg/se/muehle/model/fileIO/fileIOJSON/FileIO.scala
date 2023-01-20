package de.htwg.se.muehle.model.fileIO.fileIOJSON

import de.htwg.se.muehle.model.fileIO.IFileIO
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.playerComponent.Player

class FileIO extends IFileIO:
    override def save(field: Field, currentPlayer: Player): Unit = ???
    override def load(): (Field, Player) = ???

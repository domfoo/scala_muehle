package de.htwg.se.muehle.model.fileIO

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.playerComponent.Player

trait IFileIO:
    def save(field: Field, currentPlayer: Player): Unit
    def load(): (Field, Player)
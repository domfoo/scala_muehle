package de.htwg.se.muehle

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fileIO.IFileIO
import de.htwg.se.muehle.model.fileIO.fileIOXML.FileIO
//import de.htwg.se.muehle.model.fileIO.fileIOJSON.FileIO
import de.htwg.se.muehle.model.fileIO


class MuehleModule extends AbstractModule:
    override def configure(): Unit =
        bind[IFileIO](new TypeLiteral[IFileIO] {}).to(classOf[fileIO.fileIOXML.FileIO])
        //bind[IFileIO](new TypeLiteral[IFileIO] {}).to(classOf[fileIO.fileIOJSON.FileIO])
        bind(classOf[IController]).toInstance(Controller(Field(), FileIO()))
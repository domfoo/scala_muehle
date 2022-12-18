package de.htwg.se.muehle

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field


class MuehleModule extends AbstractModule:
    override def configure(): Unit =
        bind[IField](new TypeLiteral[IField] {}).toInstance(Field())
        //bind[IController](new TypeLiteral[IController] {}).toInstance(new Controller(Field()))
        bind(classOf[IController]).toInstance(Controller(Field()))

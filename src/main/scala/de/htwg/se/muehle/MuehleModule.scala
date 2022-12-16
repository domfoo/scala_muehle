package de.htwg.se.muehle

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.muehle.controller.controllerComponent._
import de.htwg.se.muehle.model.fieldComponent.IField
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field


class MuehleModule extends AbstractModule with ScalaModule:
    def configure() =
        bind[IField].to[Field]
        bind[IController].to[controllerBaseImpl.Controller]

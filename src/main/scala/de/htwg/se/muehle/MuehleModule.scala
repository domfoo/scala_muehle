package de.htwg.se.muehle

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.muehle.controller.controllerComponent._
import de.htwg.se.muehle.model.fieldComponent._
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field


/*class MuehleModule extends AbstractModule with ScalaModule:
    
    val defaultSize: Int = 3
    
    def configure() = 
        bind[IField].to[fieldBaseImpl.Field]
        bind[IController].to[controllerBaseImpl.Controller] */

package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
    
    "A Field" should {
        val field = Field(0)
        val field2 = Field()
        "have an empty string for field size 0" in {
            field.toString should be("" + field.eol)
        }
        "have a row or column filled with stones of a specific type" in {
            field2.replaceCell(1, Stone.X).replaceCell(2, Stone.X).replaceCell(3, Stone.X)
            field2.isFullMill(3, Stone.X)
            field2.isFullMill(3, Stone.O)
            field2.isFullMill(4, Stone.X)
        }
    }
}
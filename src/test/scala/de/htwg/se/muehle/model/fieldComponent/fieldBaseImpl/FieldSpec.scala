package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.muehle.model.fieldComponent.fieldBaseImpl.Stone
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
    
    "A Field" should {
        val field0 = Field(0)
        "have an empty string for field size 0" in {
            field0.toString should be("" + field0.eol)
        }
        "have a row or column filled with stones of a specific type" in {
            field0.replaceCell(1, Stone.X)
            field0.replaceCell(2, Stone.X)
            field0.replaceCell(3, Stone.X)
            field0.isFullMill(3, Stone.X) should be(true)
            field0.isFullMill(3, Stone.O) should be(false)
            field0.isFullMill(4, Stone.X) should be(false)
        }
    }
}
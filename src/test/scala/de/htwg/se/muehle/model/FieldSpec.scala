package de.htwg.se.muehle.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
    
    "A Field" should {
        val field0 = Field(0)
        "have an empty string for field size 0" in {
            field0.toString should be("" + field0.eol)
        }
    }
}
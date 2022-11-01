package de.htwg.se.muehle

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class PlayFieldSpec extends AnyWordSpec {
    "Muehle" should {
        val eol = sys.props("line.separator")
        val field3 = new PlayField()
        val field1 = new PlayField(1)
        val field0 = new PlayField(0)

        "have a line as String of form '|   |   +---+---+   |   |'" in {
            field3.line(0) should be("|   |   +---+---+   |   |" + eol)
        } 

        "have a space as String of form '|   |   |       |   |   |'" in {
            field3.space(0) should be("|   |   |       |   |   |" + eol)
        }

        "have a space as String of form '|   |       |       |   |'" in {
            field3.space(1) should be("|   |       |       |   |" + eol)
        }

        "have a middle as String of form '+---+---+       +---+---+'" in {
            field3.middle() should be("+---+---+       +---+---+" + eol)
        }

        "have a field as String of form ''" in {
            field0.toString() should be("" + eol)
        }

        "have a field with one ring" in {
            field1.toString() should be("""#+---+---+
                #|       |
                #+       +
                #|       |
                #+---+---+
                #""".stripMargin('#'))
        }
    }
}
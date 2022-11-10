package de.htwg.se.muehle.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
    
    "A Player" when {
        "created" should {
            val player = Player("Max", Stone.X)
            "have a name" in {
                player.name should be("Max")
            }
            "have a stone" in {
                player.stoneType should be(Stone.X)
            }
            "have a default amount of stones" in {
                player.stones should be(9)
            }
            "have a string representation" in {
                player.toString should be("Name: Max, stone type: X, stones left: 9")
            }
            "equal a player with same name and number of stones" in {
                val player2 = Player("Max", Stone.X)
                val player3 = Player("Tom", Stone.O)
                player.equals(player2) should be(true)
                player.equals(player3) should be(false)
                player.equals(3) should be(false)
            }
        }
    }
}
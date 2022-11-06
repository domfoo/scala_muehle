package de.htwg.se.muehle.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
    
    "A Player" when {
        "created" should {
            val player = Player("Max")
            "have a name" in {
                player.name should be("Max")
            }
            "have a default amount of stones" in {
                player.stones should be(9)
            }
            "have a string representation" in {
                player.toString should be("Name: Max, stones left: 9")
            }
            "equal a player with same name and number of stones" in {
                val player2 = Player("Max")
                val player3 = Player("Tom")
                player.equals(player2) should be(true)
                player.equals(player3) should be(false)
            }
        }
    }
}
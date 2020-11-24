package day19

import Solution
import java.util.*

class Day19(day: Int): Solution(day) {
    private val amountOfElves = inputString.toInt()

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        var allElves = IntArray(amountOfElves){ it+1 }.toList()
        while(allElves.size != 1){
            allElves = allElves.filterIndexed { index, _ -> index%2 == 0 }.let{
                if (allElves.size%2 == 0) it else it.drop(1)
            }

        }
        return allElves.toString()
    }

    // not 2907672
    private fun two(): String {
        val amount = amountOfElves
        var allElves = (1..amount).toList().let{
            it.drop((amount/2)) + it.take((amount/2))
        }
        var counter = amount%2
        while (allElves.size > 2){
            //println(allElves)
            allElves = allElves.filter{ counter++%3 == 2  }

        }
        return allElves.last().toString()

    }
}
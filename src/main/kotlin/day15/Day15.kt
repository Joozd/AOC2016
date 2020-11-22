package day15

import Solution
import utils.extensions.getDigits
import utils.extensions.splitToWords

/**
 * There is probably a much faster way involving lost of typing, but this completed in under 0.1 seconds so meh
 */
class Day15(day: Int): Solution(day) {
    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val wheels = inputLines.map{line ->
            line.splitToWords().let{
                Wheel(it[3].toInt(), it.last().getDigits().toInt())
            }
        }
        val firstTry = wheels[0].positions - wheels[0].startPosition - 1
        val interval = wheels[0].positions

        for (i in firstTry..Int.MAX_VALUE step interval){
            if (wheels.mapIndexed { index, wheel ->
                    wheel.hole(index+i+1)}
                    .all{ it }
            ) return i.toString()
        }
        return "Nothing found"
    }

    private fun two(): String {
        val wheels = inputLines.map{line ->
            line.splitToWords().let{
                Wheel(it[3].toInt(), it.last().getDigits().toInt())
            }
        } + Wheel(11, 0)
        val firstTry = wheels[0].positions - wheels[0].startPosition - 1
        val interval = wheels[0].positions

        for (i in firstTry..Int.MAX_VALUE step interval){
            // println("i: $i - ${wheels[0].hole(i)}, ${wheels[1].hole(i)}, ${wheels[2].hole(i)}, ${wheels[3].hole(i)}, ${wheels[4].hole(i)}, ${wheels[5].hole(i)}, ")
            if (wheels.mapIndexed { index, wheel ->
                    wheel.hole(index+i+1)
                }.all{ it })
                return i.toString()
        }
        return "NEEN"
    }

    private data class Wheel(val positions: Int, val startPosition: Int){
        fun hole(time: Int): Boolean = (startPosition + time) % positions == 0
    }
}
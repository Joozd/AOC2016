package day8

import Solution

class Day8(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = "${one()} pixels illuminated!"
    override val second: String
        get() = two()

    var d: TinyDisplayUnit? = null

    //166 is too high
    private fun one(): String {
        d = TinyDisplayUnit(6,50).apply{
            inputLines.forEach { execute(it) }
        }
        return d?.illuminatedPixels.toString()
    }

    private fun two() = d.toString()

}
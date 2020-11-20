package day12

import Solution

class Day12(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val m = IntArray(5)
        return AssembunnyComputer.create(m, inputLines).apply{run()}.result
    }

    private fun two(): String  {
        val m = IntArray(5)
        m[AssembunnyComputer.getRegister('c')] = 1
        return AssembunnyComputer.create(m, inputLines).apply{run()}.result
    }
}
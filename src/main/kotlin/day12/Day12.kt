package day12

import Solution
import shared.assembunny.AssemBunnyVM
import shared.assembunny.AssembunnyComputer

class Day12(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val m = IntArray(5)
        return AssemBunnyVM.create(m, inputString).apply{run()}.result
    }

    private fun two(): String  {
        val m = IntArray(5)
        m[AssembunnyComputer.getRegister('c')] = 1
        return AssemBunnyVM.create(m, inputString).apply{run()}.result
    }

    companion object{
        val test = "cpy 41 a\n" +
                "inc a\n" +
                "inc a\n" +
                "dec a\n" +
                "jnz a 2\n" +
                "dec a"
    }
}
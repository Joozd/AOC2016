package day23

import Solution
import shared.assembunny.AssemBunnyVM
import utils.getExtraInputForDay

class Day23(day: Int): Solution(day) {
    val extraInput = getExtraInputForDay(dayNumber, "b")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val m = IntArray(5)
        m[1] = 7
        val vm = AssemBunnyVM.create(m, extraInput)
        vm.run(dump = false)
        return vm.result
    }

    private fun two(): String {
        val m = IntArray(5)
        m[1] = 12
        val vm = AssemBunnyVM.create(m, extraInput)
        vm.run(dump = false)
        return vm.result
    }

    companion object{
        val test = ("cpy 2 a\n" +
                "tgl a\n" +
                "tgl a\n" +
                "tgl a\n" +
                "cpy 1 a\n" +
                "dec a\n" +
                "dec a")
    }
}
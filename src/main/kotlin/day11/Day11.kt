package day11

import Solution
import utils.extensions.reversed

class Day11(day: Int): Solution(day) {
    // all items %2 == 0 is a chip, every chip +1 is it's generator
    private val equipment = HashMap<Int, Equipment>()
    private val eqIndex: Map<Equipment, Int>
        get() = equipment.reversed()

    var answer1: Int? = null

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        fillTables()
        return DijkstraInspiredSolver(initialElevatorState()).getDistance().also{answer1 = it}.toString()
    }

    //not 59 because that is with one extra pair
    private fun two(): String {
        val extraItems = 4
        // taking an extra item to top takes 6 moves when elevator is at top floor (which is is after solving #1)
        // eg. chip1 down, pair x up, gen1 down, pair1 up, total 12 moves for 2 items
        return (answer1!! + 6 * extraItems).toString()
    }


    private fun fillTables(){
        val materialsGrabber = "( [a-z]*? )generator".toRegex()
        materialsGrabber.findAll(inputString).forEach{
            equipment[equipment.size] = Equipment(it.groupValues[1].trim(), true)
            equipment[equipment.size] = Equipment(it.groupValues[1].trim(), false)
        }
    }


    private fun initialElevatorState(): ElevatorState {
        val floors = List(4){ArrayList<Int>()}
        floors.forEachIndexed { i, f ->
            equipment.forEach {
                if (it.value.toString() in inputLines[i]) f.add(it.key)
                if (it.value.toString() !in inputString) floors[0].add (it.key)
            }
        }
        return ElevatorState(floors.map{it.toSet()}, 0)
    }
}
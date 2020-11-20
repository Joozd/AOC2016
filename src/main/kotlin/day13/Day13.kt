package day13

import Solution

/**
 * I am pretty happy with this one! Don't think it'll get much cleaner, whole thing ran in 29 milliseconds :)
 * Basically just mapping out the whole site from start until i reach the target
 */
class Day13(day: Int): Solution(day) {
    init{
        extra = inputString.toInt() // 10 for test
    }
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val target = Day13Coordinate(31,39)
        val startPoint = Day13Coordinate(1,1)
        val foundCoordinates = HashSet<Day13Coordinate>().apply { add(startPoint) }
        var visitingList = setOf(startPoint)
        var steps = 0
        while (target !in visitingList) {
            foundCoordinates.addAll(visitingList)
            visitingList = visitingList.map {
                // it.visited = true
                it.neighbours
            }.flatten().filter { it !in foundCoordinates }.toSet()
            steps++
            if (visitingList.isEmpty()) return "ERROR AUB"
        }
        return steps.toString()




    }
    private fun two(): String {
        val target = 50 // steps
        val startPoint = Day13Coordinate(1,1)
        val foundCoordinates = HashSet<Day13Coordinate>().apply { add(startPoint) }
        var visitingList = setOf(startPoint)
        repeat(target) {
            foundCoordinates.addAll(visitingList)
            visitingList = visitingList.map {
                // it.visited = true
                it.neighbours
            }.flatten().filter { it !in foundCoordinates }.toSet()
            if (visitingList.isEmpty()) return "ERROR AUB"
        }
        foundCoordinates.addAll(visitingList)
        return foundCoordinates.size.toString()
    }

    companion object{
        var extra: Int? = null
    }
}
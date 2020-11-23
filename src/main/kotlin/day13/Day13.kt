package day13

import Solution
import utils.dijkstra.Dijkstra

/**
 * I am pretty happy with this one! Don't think it'll get much cleaner, whole thing ran in 29 milliseconds :)
 * Basically just mapping out the whole site from start until i reach the target
 */
class Day13(day: Int): Solution(day) {
    init{
        extra = inputString.toInt() // 10 for test
    }
    private val startPoint = Day13Coordinate(1,1)

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    /**
     * Flood the maze until reaching target
     */
    private fun one(): String {
        val target = Day13Coordinate(31,39)
        val foundCoordinates = HashSet<Day13Coordinate>().apply { add(startPoint) }
        var visitingList = setOf(startPoint)
        var steps = 0
        while (target !in visitingList) {
            foundCoordinates.addAll(visitingList)
            visitingList = visitingList.map {
                it.neighbours
            }.flatten().filter { it !in foundCoordinates }.toSet()
            steps++
        }
        return steps.toString()
    }

    /**
     * Flood the maze for 50 steps
     */
    private fun two(): String {
        val target = 50 // steps
        val foundCoordinates = HashSet<Day13Coordinate>().apply { add(startPoint) }
        var visitingList = setOf(startPoint)
        repeat(target) {
            foundCoordinates.addAll(visitingList)
            visitingList = visitingList.map {
                it.neighbours
            }.flatten().filter { it !in foundCoordinates }.toSet()
            if (visitingList.isEmpty()) return "ERROR AUB"
        }
        foundCoordinates.addAll(visitingList)
        return foundCoordinates.size.toString()
    }
/*
    /**
     * Test function to test my dijkstra algorithm
     */
    private fun bonusOne(): String{
        val startNode = BonusNode(1,1)
        val target = BonusNode(31,39)
        val route = Dijkstra.shortestRoute(startNode, target, nodesAreGenerated = true) as List<BonusNode>?
        return route?.last()?.distanceToStart.toString()
    }

 */

    companion object{
        var extra: Int? = null
    }
}
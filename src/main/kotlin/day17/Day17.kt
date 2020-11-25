package day17

import Solution
import utils.dijkstra.Pathfinding

@ExperimentalUnsignedTypes
class Day17(day: Int): Solution(day) {

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        val start = Day17Node(0,0, inputString, "")
        val result = Pathfinding.dijkstraRoute(start, true){
            (it as Day17Node).goal
        }
        return (result?.last() as Day17Node?)?.route.toString()
    }

    private fun two(): String {
        val start = Day17Node(0,0,inputString, "")
        return (getLongest(listOf(start))!!.size - 1).toString()

    }

    fun getLongest(currentRoute: List<Day17Node>): List<Day17Node>?{
        currentRoute.last().let{ node ->
            if (node.goal) return currentRoute
            return node.getNeighbours().map {
                getLongest(currentRoute + (it.node as Day17Node))
            }.filterNotNull().maxByOrNull { it.size }?.takeIf { it.isNotEmpty() }
        }
    }
}
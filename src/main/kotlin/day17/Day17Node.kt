package day17

import utils.dijkstra.BasicDijkstraNode
import utils.dijkstra.DijkstraNode
import utils.md5HashString

@ExperimentalUnsignedTypes
data class Day17Node(val x: Int, val y: Int, val passcode: String, val route: String): BasicDijkstraNode() {
    val goal = x==3 && y == 3

    private val keys: String by lazy { (passcode + route).md5HashString().take(4) } // do this lazy so it only gets calculated when needed
    private val up: Day17Node?
        get() = if (check(0) && y > 0) Day17Node(x, y - 1, passcode, route + "U") else null

    private val down: Day17Node?
        get() = if (check(1) && y < 3) Day17Node(x, y + 1, passcode, route + "D") else null

    private val left: Day17Node?
        get() = if (check(2) && x > 0) Day17Node(x - 1, y, passcode, route + "L") else null

    private val right: Day17Node?
        get() = if (check(3) && x < 3) Day17Node(x + 1, y, passcode, route + "R") else null

    private fun check(pos: Int) = keys[pos] in "bcdef"

    override fun getNeighbours(): List<DijkstraNode.Neighbour<Int>> = listOfNotNull(left, right, up, down).map {
        DijkstraNode.Neighbour(it, 1)
    }

    override fun toString(): String =
        "Node ($x, $y) - keys = $keys - input = ${ (passcode + route) } - v:${isUnvisited()} - neighbours = ${getNeighbours()}"


}
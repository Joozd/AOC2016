package day13

import utils.dijkstra.BasicDijkstraNode
import utils.dijkstra.DijkstraNode

data class BonusNode(val x: Int, val y: Int): BasicDijkstraNode() {
    private val open: Boolean = x >= 0 && y >= 0 && (x * x + 3 * x + 2 * x * y + y + y * y + Day13.extra!!).toString(2).filter { it == '1' }.length % 2 == 0

    override fun getNeighbours(): List<DijkstraNode.Neighbour<Int>> = listOf(
            DijkstraNode.Neighbour(BonusNode(x - 1, y), 1),
            DijkstraNode.Neighbour(BonusNode(x + 1, y), 1),
            DijkstraNode.Neighbour(BonusNode(x, y + 1), 1),
            DijkstraNode.Neighbour(BonusNode(x, y - 1), 1),
    ).filter { (it.node as BonusNode).open }

    override fun toString(): String {
        return "BonusNode ($x, $y) --- distance is $distanceToStart - unvisited: ${isUnvisited()}"
    }
}
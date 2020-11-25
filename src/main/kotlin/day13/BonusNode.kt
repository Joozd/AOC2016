package day13

import utils.dijkstra.BasicGridNode

data class BonusNode(override val x: Int, override val y: Int): BasicGridNode() {
    private val open: Boolean = x >= 0 && y >= 0 && (x * x + 3 * x + 2 * x * y + y + y * y + Day13.extra!!).toString(2).filter { it == '1' }.length % 2 == 0

    override fun getNeighbours(): List<BonusNode> = listOf(
            BonusNode(x - 1, y),
            BonusNode(x + 1, y),
            BonusNode(x, y + 1),
            BonusNode(x, y - 1)
    ).filter { it.open }

    override fun toString(): String {
        return "BonusNode ($x, $y) --- distance is $distanceToStart - unvisited: ${isUnvisited()}"
    }
}
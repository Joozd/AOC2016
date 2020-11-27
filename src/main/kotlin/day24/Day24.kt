package day24

import Solution
import utils.Permutations
import utils.dijkstra.Pathfinding

class Day24(day: Int): Solution(day) {
    private val gridLines by lazy {
        inputLines.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Node.make(x, y, c)
            }
        }
    }

    private val grid by lazy { gridLines.flatten() }

    private val numbers by lazy {
        grid.filter { it.content != null }.sortedBy { it.content }
    }

    private val pairs by lazy {
        numbers.mapIndexed { index, c1 ->
            numbers.drop(index + 1).map { c2 ->
                c1 to c2
            }
        }.flatten()
    }

    private val pairsWithDistances by lazy {
        pairs.map {
            PairWithDistance(it.toList(), Pathfinding.gridDistance(it.first, it.second, false))
        }
    }

    private val perms by lazy { Permutations(numbers.drop(1)).toList() }


    override val first: String
        get() = one()
    override val second: String
        get() = two()

    // 1435ms without maze optimizing
    // 1674ms with maze optimizing.
    //NOTE: This maze does not have a whole lot of dead ends.
    private fun one(): String {
        grid.forEach {
            it.grid = grid
        }
        // This takes more time than it saves for this problem
        // Pathfinding.simplifyMaze(grid)
        // next line prints map with dead ends marked as '@'
        // gridLines.forEach { println(it.map { it.character }.joinToString("") ) }

        val shortest = perms.minByOrNull {
            findDistanceForRoute(it, numbers.first(), pairsWithDistances)
        }
        return findDistanceForRoute(shortest!!, numbers.first(), pairsWithDistances).toString()
    }

    // not 700 or 701 (too low)
    private fun two(): String {
        val shortest = perms.minByOrNull {
            findDistanceForRoute(it, numbers.first(), pairsWithDistances, numbers.first())
        }
        println(shortest)
        return findDistanceForRoute(shortest!!, numbers.first(), pairsWithDistances, numbers.first()).toString()
    }

    private fun findDistanceForRoute(route: List<Node>, startPoint: Node, pairs: List<PairWithDistance>, endpoint: Node? = null): Int {
        val l = listOf(startPoint) + route + listOfNotNull(endpoint)
        return (route + listOfNotNull(endpoint)).mapIndexed { index, node ->
            findDistanceForPair(l[index], node, pairs)!!
        }.sum()
    }


    private fun findDistanceForPair(p1: Node, p2: Node, pairs: List<PairWithDistance>) = pairs.firstOrNull { p1 in it.values && p2 in it.values }?.distance
}





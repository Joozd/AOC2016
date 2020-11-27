package day24

import utils.dijkstra.BasicMazeNode

data class Node(override val x: Int, override val y: Int, val open: Boolean): BasicMazeNode(){
    var grid: List<Node>? = null
    var content: Char? = null


    override fun getNeighbours(): List<Node> =
        listOfNotNull(grid?.firstOrNull { it.x-1 == x && it.y == y },
                grid?.firstOrNull { it.x+1 == x && it.y == y },
                grid?.firstOrNull { it.x == x && it.y-1 == y },
                grid?.firstOrNull { it.x == x && it.y+1 == y }
        ).filter{it.open && !it.deadEnd}

    override val canBeDead: Boolean
        get() = content == null

    val character: Char
        get() = when{
            content != null -> content!!
            !open -> '#'
            deadEnd -> '@'
            else -> '.'
        }

    companion object{
        fun make(x: Int, y: Int, c: Char): Node =
            Node(x,y, c != '#').apply{
                if (c !in "#.") content = c
            }
    }

}

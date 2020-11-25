package day22

import Solution
import utils.dijkstra.Pathfinding

class Day22(day: Int): Solution(day) {
    private val nodes by lazy { inputLines.mapNotNull { Node.parse(it) } }

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String = nodes.filter{ it.used != 0 }.map{ notEmptyNode -> nodes.filter{ it != notEmptyNode && notEmptyNode.used <= it.avail}.map{ notEmptyNode to it }}.flatten().size.toString()

    /**
     * After some tinkering, I found what the whole thing is. Leaving the map dump here for clarity.
     */

    private fun two(): String {
        val maxX = nodes.maxByOrNull { it.x }!!.x
        val sourceNode = nodes.firstOrNull { it.x == maxX && it.y == 0 }!!

        ///// CODE BELOW CAN BE REMOVED AND IS JUST TO ILLUSTRATE THE PROCESS /////
        nodes.forEach { it.setNeighbours(nodes) }

        val smallestNode = nodes.minByOrNull { it.size }
        val biggestMovableData = nodes.filter{it.used < 100}.maxByOrNull { it.size }

        println("smallest node: $smallestNode\nbiggest data:$biggestMovableData")
        val allNodesInGrid = Array<Array<Node?>>(nodes.maxByOrNull { it.y }!!.y+1){ y ->
            Array<Node?>(nodes.maxByOrNull { it.x }!!.x+1){
                x -> nodes.firstOrNull { it.x == x && it.y == y }
            }
        }
        println(allNodesInGrid.map{ l ->
            l.map {it?.representation ?: '!'}.joinToString("")
        }.joinToString("\n"))

        ///// CODE ABOVE CAN BE REMOVED AND IS JUST TO ILLUSTRATE THE PROCESS /////

        val grid = ArrayList<SimplifiedNode>()
        nodes.forEach{
            grid.add(SimplifiedNode(it.x, it.y, it.used > 100, grid).apply{
                if (it.used == 0) occupied = false
                if (it == sourceNode) hasWantedData = true
            })
        }
        var wantedDataLocation = grid.firstOrNull { it.x == maxX && it.y == 0 }!!

        var emptyLocation = grid.first{!it.occupied}
        val target = grid.firstOrNull { it.x ==0 && it.y == 0 }!!

        @Suppress("UNCHECKED_CAST")
        var route = Pathfinding.gridRoute(wantedDataLocation, target, false)!!.drop(1) as List<SimplifiedNode>

        grid.forEach{ it.ignoreMovableData = false }

        var steps = 0
        while(route.isNotEmpty()){

            //the route the emptyness has to take
            val emptyRoute = Pathfinding.gridRoute(emptyLocation, route.first(),false)!!.drop(1)
            route.first().moveData(emptyLocation)
            steps += emptyRoute.size

            //move the data 1 step
            wantedDataLocation.moveData(route.first())
            wantedDataLocation = route.first()
            route = route.drop(1)
            steps++
            emptyLocation = grid.first{!it.occupied}
        }

        return steps.toString()
    }
}
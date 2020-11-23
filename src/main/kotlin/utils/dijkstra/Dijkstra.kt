package utils.dijkstra

import java.util.*

object Dijkstra{
    /**
     * Get a route from [start] to [end]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found. If unsure, leave false (only an unknown performance penalty)
     */
    fun <T: Number> getRoute(start: DijkstraNode<T>, end: DijkstraNode<T>, nodesAreGenerated: Boolean = true): List<DijkstraNode<T>>? =
        getRoute(start, nodesAreGenerated) { it == end }


    /**
     * Get a list from start to the first place that satisfies [endCondition]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found. If unsure, leave false (only an unknown performance penalty)
     */
    fun <T: Number> getRoute(start: DijkstraNode<T>, nodesAreGenerated: Boolean = true, endCondition: (DijkstraNode<T>) -> Boolean): List<DijkstraNode<T>>?{
        var activeNode: DijkstraNode<T>? = start
        val visitingList = hashSetOf<DijkstraNode<T>>()
        val knownNodes = hashSetOf(start)
        while(activeNode != null && !endCondition(activeNode)){

            print("looking at $activeNode, knownNodes has ${knownNodes.size}, visitingList has ${visitingList.size}\n")

            print("Neighbours: ${activeNode.getNeighbours().map{it.node}}\n")

            activeNode.getNeighbours().forEach { neighbour ->
                val node = if(nodesAreGenerated)
                    knownNodes.firstOrNull{ it == neighbour.node } ?: neighbour.node.also { knownNodes.add(it)} // Use the version that is in knownNodes
                    else neighbour.node
                if (node.isUnvisited()) {
                    visitingList.add(node)
                    if (node.distanceToStart == 0 || node.distanceToStart.toDouble() > activeNode!!.distanceToStart.toDouble() + neighbour.distance.toDouble()) {
                        node.distanceToStart = activeNode!!.distanceToStart + neighbour.distance
                        node.previousNode = activeNode
                    }
                }
                activeNode!!.visited()
                println("should be visited: $activeNode\n")
                visitingList.remove(activeNode)
            }
            activeNode = visitingList.minByOrNull { it.distanceToStart.toDouble() }
        }

        //Once we are here, searching is done.
        return activeNode?.let{
            val result = arrayListOf(it)
            var parent = it.previousNode
            while (parent != null){
                result.add(parent)
                parent = parent.previousNode
            }
            result.reversed()
        }
    }






    private operator fun Number.compareTo(other: Comparable<Number>): Int =
        when (this){
            is Int -> this.compareTo(other)
            is Double -> this.compareTo(other)
            is Float -> this.compareTo(other)
            is Long -> this.compareTo(other)
            is Byte -> this.compareTo(other)
            is Short -> this.compareTo(other)
            else -> error ("Cannot compare these types")
        }


    @Suppress("UNCHECKED_CAST")
    private operator fun <T: Number> Number.plus(other: Number): T =
        when (this){
            is Int -> (this + other.toInt()) as T
            is Double -> this + other.toDouble() as T
            is Float -> this + other.toFloat() as T
            is Long -> this + other.toLong() as T
            is Byte -> this + other.toByte() as T
            is Short -> this + other.toShort() as T
            else -> error ("Cannot compare these types")
        }

}

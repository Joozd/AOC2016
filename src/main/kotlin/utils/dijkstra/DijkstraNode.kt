package utils.dijkstra

interface DijkstraNode<T: Number> {
    /**
     * Initialize [distanceToStart] as distance to first node (usually zero if starting there)
     */
    var distanceToStart: T

    /**
     * Should start as null
     */
    var previousNode: DijkstraNode<T>?

    /**
     * This will be called when the shortest route to this DijkstraNode has been found
     * set [this.previousNode] to [from]
     */
    fun visited()

    /**
     * should be true is this DijkstraNode has not yet been visited (eg. if visit() has not yet been called)
     */
    fun isUnvisited(): Boolean


    /**
     * Return all of this nodes neighbours
     */
    fun getNeighbours(): List<Neighbour<T>>


    class Neighbour<T: Number>(val node: DijkstraNode<T>, val distance: T){
        override operator fun equals(other: Any?) =
            if (other !is Neighbour<*>) false
            else other.node == node && other.distance == distance

        override fun hashCode(): Int =
            31 * node.hashCode() + distance.hashCode()
    }
}
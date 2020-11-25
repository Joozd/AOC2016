package utils.dijkstra

abstract class BasicGridNode() : GridNode {
    override var distanceToStart: Int = 0
    override var previousNode: BasicNode<Int>? = null

    private var unvisited = true

    override fun visited() {
        unvisited = false
    }

    override fun isUnvisited(): Boolean = unvisited


    /**
     * Return all of this nodes neighbours
     */
}
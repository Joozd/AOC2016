package day22

import utils.dijkstra.BasicGridNode
import utils.dijkstra.GridNode

data class SimplifiedNode(override val x: Int,override  val y: Int, val blocked: Boolean): BasicGridNode(){

    constructor(x: Int, y: Int, blocked: Boolean, nodes: List<SimplifiedNode>): this(x,y,blocked){
        allNodes = nodes
    }
    private var allNodes: List<SimplifiedNode> = emptyList()
    var occupied: Boolean = true
    var hasWantedData: Boolean = false

    var ignoreMovableData: Boolean = true  // for first run to decide route the data will take

    fun moveData(target: SimplifiedNode){
        require(!target.occupied) { "Trying to move to an occupied target"}
        target.hasWantedData = hasWantedData
        target.occupied = occupied

        occupied = false
        hasWantedData = false
    }

    private val canBeCleared: Boolean
        get() = !blocked && !hasWantedData && occupied



    private val _neighbours: List<SimplifiedNode> by lazy{
        listOfNotNull(
                allNodes.firstOrNull { it.x == x-1 && it.y == y },
                allNodes.firstOrNull { it.x == x+1 && it.y == y },
                allNodes.firstOrNull { it.x == x && it.y == y+1 },
                allNodes.firstOrNull { it.x == x && it.y == y-1 })
    }
    override fun getNeighbours(): List<GridNode> = _neighbours.filter { if (ignoreMovableData) !it.blocked else it.canBeCleared }

    override fun toString() = "($x,$y)"
}

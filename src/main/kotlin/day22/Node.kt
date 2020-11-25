package day22

data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val avail: Int){
    val neighbours: List<Node>?
        get() = _neighbours

    val representation: Char
        get() = when{
            neighbours == null -> '?'
            used > 100 -> '@'
            used == 0 -> '_'
            neighbours!!.all{it.size < used} -> '@'
            neighbours!!.all{it.avail < used} -> '#'
            neighbours!!.none { it.used <= avail} -> 'x'
            neighbours!!.none { it.used <= size} -> 'X'
            neighbours!!.all { it.used <= size} -> '.'
            neighbours!!.any { it.used <= size} -> '-'
            else -> '-'
        }

    val amountOfNeighbours: Int
        get() = neighbours?.size ?: 8


    private var _neighbours: List<Node>? = null
    fun setNeighbours(nodes: List<Node>) {
        _neighbours = listOfNotNull(
                nodes.firstOrNull { it.x == x-1 && it.y == y },
                nodes.firstOrNull { it.x == x+1 && it.y == y },
                nodes.firstOrNull { it.x == x && it.y == y+1 },
                nodes.firstOrNull { it.x == x && it.y == y-1 })
    }





    companion object{
        private const val NUMBER = "([0-9]*)"
        private const val SIZE = "\\s+([0-9]*)T"
        private const val PERCENTAGE = "\\s+([0-9]*)%"
        ///dev/grid/node-x0-y0     85T   69T    16T   81%
        private val nodeRegex = "/dev/grid/node-x$NUMBER-y$NUMBER$SIZE$SIZE$SIZE$PERCENTAGE".toRegex()
        fun parse(s: String): Node? = nodeRegex.find(s)?.let{
            val r = it.groupValues
            Node(
                    r[1].toInt(),
                    r[2].toInt(),
                    r[3].toInt(),
                    r[4].toInt(),
                    r[5].toInt()
            )
        }
    }
}

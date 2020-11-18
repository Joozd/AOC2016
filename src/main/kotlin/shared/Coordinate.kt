package shared

/**
 * Coordinates:
 * y: higher is north,
 * x: higher is right
 */
data class Coordinate(val x: Int, val y: Int){
    override fun toString() = "($x,$y)"

    fun move(direction: Char, distance: Int): Coordinate =
        when (direction){
            NORTH -> this.copy(y = y + distance)
            SOUTH -> this.copy(y = y - distance)
            WEST -> this.copy(x = x - distance)
            EAST -> this.copy(x = x + distance)
            else -> error("$direction is not a direction.")
        }

    companion object{
        fun parse(s: String): Coordinate{
            require (',' in s)
            s.split(',').map{it.toInt()}.let{
                return Coordinate(it[0], it[1])
            }
        }
        const val NORTH = 'n'
        const val EAST = 'e'
        const val SOUTH = 's'
        const val WEST = 'w'

        const val UP = 'n'
        const val RIGHT = 'e'
        const val DOWN = 's'
        const val LEFT = 'w'
    }

}
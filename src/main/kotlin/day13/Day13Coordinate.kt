package day13

import shared.Coordinate

data class Day13Coordinate(val x: Int, val y: Int) {
    // var visited: Boolean = false
    val open: Boolean = x >=0 && y >= 0 && (x*x + 3*x + 2*x*y + y + y*y + Day13.extra!!).toString(2).filter { it == '1' }.length %2 == 0

    val neighbours: List<Day13Coordinate>
        get() = listOf(
                Day13Coordinate(x-1,y),
                Day13Coordinate(x+1,y),
                Day13Coordinate(x,y+1),
                Day13Coordinate(x,y-1)
        ).filter{it.open}

    override fun toString() = "Coord($x, $y) : ${(x*x + 3*x + 2*x*y + y + y*y + Day13.extra!!).toString(2)} - ${(x*x + 3*x + 2*x*y + y + y*y + Day13.extra!!).toString(2).filter{it == '1'}.length}"
}
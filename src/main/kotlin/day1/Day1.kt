package day1

import Solution
import shared.Coordinate
import utils.extensions.abs

class Day1(day: Int): Solution(day) {
    val inputs = inputString.split(", ")

    override val first = one()
    override val second = two()

    private fun one(): String {
        var pos = Coordinate(0,0)
        var direction = NORTH
        inputs.forEach{
            direction = direction.newDirection(it.first())
            pos = pos.move(directionToChar(direction), it.drop(1).toInt())
        }
        println(pos.toString())
        return (pos.x.abs() + pos.y.abs()).toString()

    }

    private fun Int.newDirection(turn: Char): Int = if (turn == 'L')
    (this+3)%4 else
    (this+1)%4

    private fun directionToChar(direction: Int): Char = when (direction){
        NORTH -> Coordinate.NORTH
        EAST -> Coordinate.EAST
        SOUTH -> Coordinate.SOUTH
        WEST ->Coordinate.WEST
        else -> error ("directionToChar(): $direction IS NOT A DIRECTION")
    }

    //257 too high
    private fun two(): String{
        var direction = NORTH
        val positions = listOf(Coordinate(0,0)).toMutableList()
        inputs.forEach{
            direction = direction.newDirection(it.first())
            repeat(it.drop(1).toInt()) {
                val newPos = positions.last().move(directionToChar(direction),1)
                if (newPos in positions) return (newPos.x.abs() + newPos.y.abs()).toString()
                positions.add(newPos)
            }

        }
        return "no doubles"
    }

    companion object{
        const val NORTH = 0
        const val EAST = 1
        const val SOUTH = 2
        const val WEST = 3
    }
}
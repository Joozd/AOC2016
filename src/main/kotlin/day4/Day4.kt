package day4

import Solution

class Day4(day: Int): Solution(day) {
    private val rooms: List<Room> by lazy { inputLines.map { Room.parse(it)} }

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String = rooms.filter{it.isReal}.sumBy { it.sectorID }.toString()


    private fun two() = rooms.firstOrNull{it.actualName == NORTH_POLE_OBJECTS}?.sectorID.toString()

    companion object{
        const val NORTH_POLE_OBJECTS = "northpole object storage"
    }
}
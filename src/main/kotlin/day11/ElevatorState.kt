package day11

/**
 * [floors] is a list of floors
 * a `floor` is a list of equipment, each represented by its ID
 */
data class ElevatorState(val floors: List<Set<Int>>, val elevatorPosition: Int) {
    var stepsToGetHere = 0
    // a chip is shielded if its +1 is on the same floor
    val dead: Boolean = floors.any{hasDeadChip(it)}
    val finished = (floors[0]+floors[1]+floors[2]).isEmpty()

    private fun hasDeadChip(floor: Collection<Int>): Boolean = floor.filter{it %2 == 0}.any{chip ->
        chip+1 !in floor &&     // if shielded, we good
        floor.any{it %2 == 1}   // if no generator, we good too
    }
    companion object{
        fun getPairs(f: Set<Int>): Set<Int> {
            val chips = f.filter { it % 2 == 0 }.filter { it + 1 in f }
            return (chips + chips.map{it+1}).toSet()
        }
    }

}
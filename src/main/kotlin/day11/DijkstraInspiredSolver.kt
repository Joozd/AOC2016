package day11

import java.util.*
import kotlin.collections.LinkedHashSet

class DijkstraInspiredSolver(private val initialState: ElevatorState) {
    private val visitedStates = LinkedHashSet<ElevatorState>()


    /**
     * Will give distance to all on top floor, or null if no solution found
     */
    fun getDistance(): Int? {
        val visitingList = LinkedHashSet<ElevatorState>().apply { add(initialState) }
        var currentState: ElevatorState? = initialState
        while(currentState != null && !currentState.finished) {
            if (visitedStates.size %10000 == 0) println("visiting list now has ${visitingList.size} items, visited ${visitedStates.size} states")
            visitedStates += currentState
            getPossibleNextStates(currentState, currentState.stepsToGetHere + 1).forEach{
                visitingList.add(it) // it's a set so won't do doubles
            }
            visitingList.remove(currentState)
            currentState = visitingList.first() // can take first because first one will always have lowest count
        }
        return currentState?.stepsToGetHere
    }


    private fun getPossibleNextStates(s: ElevatorState, d: Int): List<ElevatorState> {
        /**
         * The elevator is at floor x
         * It needs to load 1 or 2 items from that floor.
         * It needs to move up or down
         * There, it will unload all.
         */
        val currentFloor = s.floors[s.elevatorPosition].let{f ->
            f.filter{it !in ElevatorState.getPairs(f)} + ElevatorState.getPairs(f).take(2)
        }
        val possibilities = LinkedList<ElevatorState>()

        currentFloor.forEach { item1 ->
            currentFloor.forEach { item2 -> // if the same as item1 elevator will only take that
                val cargo = setOf(item1, item2)
                when (s.elevatorPosition) {
                    0 -> ElevatorState(moveItemsToFloor(s.floors, cargo, 0, 1), 1)//.also{println(it)}
                            .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                    1 -> {
                        ElevatorState(moveItemsToFloor(s.floors, cargo, 1, 2), 2)
                                .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                        ElevatorState(moveItemsToFloor(s.floors, cargo, 1, 0), 0)
                                .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                    }
                    2 -> {
                        ElevatorState(moveItemsToFloor(s.floors, cargo, 2, 3), 3)
                                .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                        ElevatorState(moveItemsToFloor(s.floors, cargo, 2, 1), 1)
                                .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                    }
                    3 -> ElevatorState(moveItemsToFloor(s.floors, cargo, 3, 2), 2)
                            .takeIf { !it.dead && it !in visitedStates }?.let { possibilities.add(it.apply{ stepsToGetHere = d}) }
                }
            }
        }
        return possibilities
    }

    private fun moveItemsToFloor(floors: List<Set<Int>>, items: Set<Int>, origin: Int, target: Int): List<Set<Int>> =
        floors.mapIndexed { index, set ->
            when (index) {
                origin -> set.filter { it !in items }.toSet()
                target -> set + items
                else -> set
            }
        }

    private fun makeCargos(floor: Collection<Int>): List<Set<Int>> = floor.mapIndexed{ i, eq ->
        floor.drop(i+1).map{ setOf(eq, it)}
    }.flatten()
}
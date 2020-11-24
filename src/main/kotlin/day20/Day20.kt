package day20

import Solution

class Day20(day: Int): Solution(day) {
    //these are lazy so they count for timing

    private val blockedRanges by lazy {
        inputLines.map{it.split("-")}.map{(it[0].toLong()..it[1].toLong())}
    }
    private val lowestInAllowedRangeList by lazy { blockedRanges.map{it.last+1}.filter { last -> blockedRanges.none { last in it} }.sorted().filter { it <= UINT_MAX} }

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    // 23923783
    private fun one(): String = lowestInAllowedRangeList.minOrNull().toString()


    private fun two(): String {
        val rangeStarts = blockedRanges.map { it.first }.sorted()
        return lowestInAllowedRangeList.map { allowedStart ->
            (rangeStarts.firstOrNull { it > allowedStart } ?: UINT_MAX) - allowedStart
        }.sum().toString()
    }

    companion object {
        const val UINT_MAX: Long = 4294967295


    }

}
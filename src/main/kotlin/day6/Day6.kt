package day6

import Solution

class Day6(day: Int): Solution(day) {
    private val characterLists = inputLines.map{it.toList()}

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String{
        val answer = CharArray(characterLists.first().size)
        answer.indices.forEach{i ->
            answer[i] = characterLists.map{it[i]}.groupingBy { it }.eachCount().maxByOrNull{it.value}!!.key
        }
        return answer.joinToString("")
    }

    private fun two(): String{
        val answer = CharArray(characterLists.first().size)
        answer.indices.forEach{i ->
            answer[i] = characterLists.map{it[i]}.groupingBy { it }.eachCount().minByOrNull{it.value}!!.key
        }
        return answer.joinToString("")
    }
}
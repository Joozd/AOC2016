package day18

import Solution
import utils.findRepeat

class Day18(day: Int): Solution(day) {
    val test1 = ".^^.^.^^^^"

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        return GridBuilder(inputString, 40).sumBy { it.count { c -> c == '.' } }.toString()
    }

    private fun two(): String {
        return GridBuilder(inputString, 400000).sumBy { it.count { c -> c == '.' } }.toString()
    }

    class GridBuilder(val startLine: String, private val maxLines: Int? = null): Iterable<String>{

        override fun iterator(): Iterator<String> = object: Iterator<String> {
            private var counter = 0
            private var value = ".$startLine."
            val width = startLine.length

            override fun hasNext(): Boolean = maxLines == null || counter < maxLines

            override fun next(): String {
                counter++
                val previous = value
                return value.drop(1).dropLast(1).also{_ ->
                    value = CharArray(width+2){
                        if (it == 0 || it == width+1) '.'
                        else if (previous.slice(it-1..it+1) in trapCombos) '^' else '.'
                    }.joinToString("")
                }
            }
        }
    }





    companion object{
        val trapCombos = listOf(
                "^^.",
                ".^^",
                "^..",
                "..^"
        )
    }
}
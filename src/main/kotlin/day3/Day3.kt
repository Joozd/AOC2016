package day3

import Solution

class Day3(day: Int): Solution(day) {
    private val values = inputLines.map{ l -> l.split(' ').filter{it.isNotBlank()}.map{it.toInt()}}
    private val values2 = (values.map{it[0]} + values.map{it[1]} + values.map{it[2]}).chunked(3)


    override val first: String
        get() = one()
    override val second: String
        get() = two()

    //1034 too high
    private fun one(): String = values.filter{ it.sorted().dropLast(1).sum() > it.maxOrNull()!!}.size.toString()



    private fun two(): String = values2.filter{ it.sorted().dropLast(1).sum() > it.maxOrNull()!!}.size.toString()
}
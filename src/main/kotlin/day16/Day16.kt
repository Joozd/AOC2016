package day16

import Solution

class Day16(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        return checkSum(generateEnoughData(inputString, 272))
    }

    private fun two(): String {
        return checkSum(generateEnoughData(inputString, 35651584))
    }

    private fun generateEnoughData(initial: String, size: Int): String{
        var data= initial
        while(data.length < size){
            val a = data
            val b = a.reversed().replace('0', 'o').replace('1', '0').replace('o', '1')
            data = a + "0" + b
        }
        return data.take(size)
    }

    private fun checkSum(data: String): String{
        val cs = data.chunked(2).joinToString("") { if (it[0] == it[1]) "1" else "0" }
        return if (cs.length%2 == 0) checkSum(cs) else cs
    }
}
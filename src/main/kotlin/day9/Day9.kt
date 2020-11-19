package day9

import Solution

class Day9(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String = decompress(inputString).length.toString()

    private fun two(): String = CompressedData(inputString).length.toString()



    private fun decompress(compressed: String): String {
        val result = ArrayList<Char>()
        var pointer = 0
        while (pointer < compressed.length) {
            if (compressed[pointer] != '(') result.add(compressed[pointer++])
            else {
                val markerContent = ArrayList<Char>(10)
                while (compressed[pointer] != ')') {
                    markerContent.add(compressed[pointer++])
                }
                markerContent.add(compressed[pointer++])
                val marker = Marker.parse(markerContent)
                val dataToAdd = compressed.slice((pointer until pointer + marker.length))
                repeat(marker.repeats) {
                    result.addAll(dataToAdd.toList())
                }
                pointer += marker.length
            }
        }
        return result.joinToString("")
    }
}



package day14

import Solution
import utils.LookForwardCache
import utils.md5HashString

@ExperimentalUnsignedTypes
class Day14(day: Int): Solution(day) {
    // String extension function String.md5Hash() can be found in `utils`

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    // 11876 too low
    private fun one(): String {
        var foundKeys = 0
        val allKeys = LookForwardCache(1000){
            (inputString + it.toString()).md5HashString()
        }
        while (foundKeys != 64){
            allKeys.next().checkForTriplet()?.let{ c ->
                //println("${allKeys.index -1} - ${allKeys.first()} - $c")
                if (allKeys.checkNext1000(c))
                    foundKeys++
            }
        }
        return (allKeys.index).toString()
    }

    // Elapsed time for 2: 33424 millis
    private fun two(): String {
        var foundKeys = 0
        val allKeys = LookForwardCache(1000){
            var result = (inputString + it.toString()).md5HashString()
            repeat(2016){
                result = result.md5HashString()
            }
            result
        }
        while (foundKeys != 64){
            allKeys.next().checkForTriplet()?.let{ c ->
                //println("${allKeys.index -1} - ${allKeys.first()} - $c")
                if (allKeys.checkNext1000(c))
                    foundKeys++
            }
        }
        return (allKeys.index).toString()
    }

    private fun String.checkForTriplet(): Char? {
        this.indices.toList().dropLast(2).forEach { i ->
            if (get(i) == get(i + 1) && get(i) == get(i + 2))
                return get(i)
        }
        return null
    }

    private fun LookForwardCache<String>.checkNext1000(character: Char): Boolean = any{ c ->
        (character.toString().repeat(5) in c).also{
            if (it) println("found: ${originalIndexOf(c)} - $c")
        }
    }
}
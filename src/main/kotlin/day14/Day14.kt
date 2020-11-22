package day14

import Solution
import utils.AsyncLookForwardCache
import utils.SingleThreadLookForwardCache
import utils.md5HashString

@ExperimentalUnsignedTypes
class Day14(day: Int): Solution(day) {
    // String extension function String.md5Hash() can be found in `utils`

    override val first: String
        get() = "---" // one()
    override val second: String
        get() = two()

    // async: 340, 329, 322, 328
    // singleThread: 285, 275, 265

    private fun one(): String {
        var foundKeys = 0
        val allKeys = SingleThreadLookForwardCache(1000){
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

    // single thread: 38712, 31839,
    //async 1 core: 39058, 35386, 39096
    // async 2 cores: 21186, 17273
    // async 3 cores: 14650, 14317,
    // async 4 cores: 12033, 9697, 9826, 11987
    // async 6 cores: 10307, 10267, 9109
    // async 7 cores: 10270, 9389, 10299, 9990, 9443, 9892
    private fun two(): String {
        var foundKeys = 0
        val allKeys = AsyncLookForwardCache(1000, cores = 7){
            var result = (inputString + it.toString()).md5HashString()
            repeat(2016){
                result = result.md5HashString()
            }
            result
        }
        while (foundKeys != 64){ // 64
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

    private fun SingleThreadLookForwardCache<String>.checkNext1000(character: Char): Boolean = any{ c ->
        (character.toString().repeat(5) in c)
    }

    private fun AsyncLookForwardCache<String>.checkNext1000(character: Char): Boolean = any{ c ->
        (character.toString().repeat(5) in c)
    }
}
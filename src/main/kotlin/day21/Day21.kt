package day21

import Solution
import utils.extensions.splitToWords

class Day21(day: Int): Solution(day) {
    val password = "abcdefgh"
    val password2 = "fbgdceah"
    var testResult1: String = "X"
    val testPass = "abcde"

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        var result = password
        inputLines.forEach { line ->
            //println("$line")
            val words = line.splitToWords()
            result = when{
                line.startsWith(SWAP_POSITION) ->
                    //swap position X with position Y
                    result.swapPos(words[2].toInt(), words[5].toInt())

                line.startsWith(SWAP_LETTER) ->
                    //swap letter X with letter Y
                    result.swapLetters(words[2][0], words[5][0])

                line.startsWith(ROTATE_BASED) ->
                    //rotate based on position of letter X
                    result.rotateBasedOnPosOf(words[6][0])

                line.startsWith(ROTATE) ->
                    //rotate left/right X steps
                    result.rotate(words[2].toInt(), words[1])

                line.startsWith(REVERSE) ->
                    //reverse positions X through Y
                    result.reverseBetween(words[2].toInt(), words[4].toInt())

                line.startsWith(MOVE) ->
                    //move position X to position Y
                    result.move(words[2].toInt(), words[5].toInt())

                else -> error("STOM")
            }
            //println(result + "\n")
        }
        return result.also{ testResult1 = it}
    }

    //reverse second input
    private fun two(): String {
        var result = password2
        inputLines.reversed().forEach { line ->
            //println("$line")
            val words = line.splitToWords()
            result = when{
                line.startsWith(SWAP_POSITION) ->
                    //stays the same
                    result.swapPos(words[2].toInt(), words[5].toInt())

                line.startsWith(SWAP_LETTER) ->
                    //stays the same
                    result.swapLetters(words[2][0], words[5][0])

                line.startsWith(ROTATE_BASED) ->
                    //rotate based on position of letter X
                    result.revertRotateBasedOnPosOf(words[6][0])!!

                line.startsWith(ROTATE) ->
                    //Rotate the other way round
                    result.rotate(words[2].toInt(), if (words[1] == LEFT) RIGHT else LEFT)

                line.startsWith(REVERSE) ->
                    //stays the same
                    result.reverseBetween(words[2].toInt(), words[4].toInt())

                line.startsWith(MOVE) ->
                    //move the other way round
                    result.move(words[5].toInt(), words[2].toInt())



                else -> error("STOM")
            }
            //println(result + "\n")
        }
        return result
    }

    private fun String.swapPos(x: Int, y: Int): String = String(this.toCharArray().apply{
        set(x, this@swapPos[y])
        set(y, this@swapPos[x])
    })

    private fun String.swapLetters(x: Char, y: Char): String = replace(x, 'X').replace(y, x).replace('X', y)

    private fun String.rotate(distance: Int, direction: String): String =
            if (direction == LEFT) drop(distance) + take(distance)
            else takeLast(distance%length) + dropLast(distance%length)

    private fun String.rotateBasedOnPosOf(c: Char) =
            rotate( indexOf(c).let{ if (it < 4) (it +1)%length else (it+2)%length}, RIGHT)

    private fun String.revertRotateBasedOnPosOf(c: Char) = (0..length).map { rotate(it, LEFT)}
            .firstOrNull{p -> p.rotateBasedOnPosOf(c) == this  }

    private fun String.reverseBetween(x: Int, y: Int) = take(x) + slice(x..y).reversed() + drop(y+1)

    private fun String.move(x: Int, y: Int) = (take(x) + drop(x+1)).let{
        it.take(y) + this[x] + it.drop(y)
    }

    companion object{
        const val LEFT = "left"
        const val RIGHT = "right"

        const val SWAP_POSITION = "swap position"
        const val SWAP_LETTER = "swap letter"
        const val ROTATE_BASED = "rotate based" // check this before next
        const val ROTATE = "rotate"
        const val REVERSE = "reverse"
        const val MOVE = "move"
    }

    val testScript = listOf("swap position 4 with position 0",
            "swap letter d with letter b",
            "reverse positions 0 through 4",
            "rotate left 1 step",
            "move position 1 to position 4",
            "move position 3 to position 0",
            "rotate based on position of letter b",
            "rotate based on position of letter d"
    )
}
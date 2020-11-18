package day2

import Solution

/**
 * Totally overengineered :D
 */
class Day2(day: Int): Solution(day) {
    private val inputs = inputLines.map{it.toCharArray()}

    private val secondPad = """    1
  2 3 4
5 6 7 8 9
  A B C
    D"""

    override val first = one()
    override val second = two()

    private fun one() = inputs.map{cc ->
        Keypad().apply{
            cc.forEach {
                when (it) {
                    'L' -> column--
                    'R' -> column++
                    'U' -> row--
                    'D' -> row++
                }
            }
        }.toString()
    }.joinToString("")

    private fun two(): String{
        val rawKeysData = secondPad.filter{it != ' '}.split('\n').map{it.toList()}
        val width = rawKeysData.maxByOrNull{it.size}!!.size
        val buttons = rawKeysData.map{
            val emptySpaces = width - it.size
            Array<Char?>(emptySpaces/2){null}.toList().let{fill ->
                fill + it + fill
            }
        }
        return inputs.map {cc ->
            VariableKeypad(buttons).apply {
                cc.forEach {
                    when (it) {
                        'L' -> l()
                        'R' -> r()
                        'U' -> u()
                        'D' -> d()
                    }
                }
            }.toString()
        }.joinToString("")
    }
}
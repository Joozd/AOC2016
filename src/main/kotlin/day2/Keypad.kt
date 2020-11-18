package day2

import kotlin.reflect.KProperty

class Keypad {
    private val keys = (1..9).toList().chunked(3)
    var row: Int by ZeroToTwo()
    var column: Int by ZeroToTwo()

    private class ZeroToTwo(){
        var current = 1
        operator fun getValue(keypad: Keypad, property: KProperty<*>): Int = current

        operator fun setValue(keypad: Keypad, property: KProperty<*>, i: Int) {
            current = when{
                i <= 0 -> 0
                i >= 2 -> 2
                else -> i
            }
        }
    }
    override fun toString() = "${keys[row][column]}"
}
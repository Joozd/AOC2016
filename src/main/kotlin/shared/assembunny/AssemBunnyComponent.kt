package shared.assembunny

import java.lang.Exception

abstract class AssemBunnyComponent {


    companion object {
        @JvmStatic
        protected val GET_REGISTER = 'a'.toInt() - 1

        @JvmStatic
        protected val INSTRUCTION_ADDRESS = 0

        @JvmStatic
        protected val INC = "inc"

        @JvmStatic
        protected val DEC = "dec"

        @JvmStatic
        protected val JNZ = "jnz" // fixed/fixed

        @JvmStatic
        protected val TGL = "tgl"

        @JvmStatic
        protected val CPY = "cpy"

        @JvmStatic
        protected val ADD = "add"

        @JvmStatic
        protected val MUL = "mul"

        @JvmStatic
        protected val NIL = "nil"


        @JvmStatic
        protected fun getRegister(reg: Char): Int = reg.toInt() - GET_REGISTER

        @JvmStatic
        protected fun getRegister(reg: String): Int = reg.first().toInt() - GET_REGISTER


        @JvmStatic
        protected val cpy: (IntArray, String, String) -> Unit = { memory, amount, target ->
            memory[getRegister(target)] = amount.getValue(memory)
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val inc: (IntArray, String) -> Unit = { memory, target ->
            memory[getRegister(target)]++
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val dec: (IntArray, String) -> Unit = { memory, target ->
            memory[getRegister(target)]--
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val add: (IntArray, String, String) -> Unit = { memory, amount, target ->
            memory[getRegister(target)] += amount.getValue(memory)
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val mul: (IntArray, String, String) -> Unit = { memory, amount, target ->
            memory[getRegister(target)] *= amount.getValue(memory)
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val jnz: (IntArray, String, String) -> Unit = { memory, valueToCheck, amount ->
            if (valueToCheck.getValue(memory) != 0) {
                // println("jumping by $amount (${amount.getValue(memory)} / value  == ${valueToCheck.getValue(memory)}")
                memory[INSTRUCTION_ADDRESS] += amount.getValue(memory)
            } else memory[INSTRUCTION_ADDRESS]++ // println("not jumping because ${valueToCheck.getValue(memory)} == 0")
        }

        @JvmStatic
        protected val tgl: (IntArray, String, AssemBunnyProgram) -> Unit = { memory, offset, program ->
            val pointer = memory[INSTRUCTION_ADDRESS]
            //println("changing ${pointer + offset.getValue(memory)} (p = $pointer, o = ${offset.getValue(memory)}")
            //println("debug 1")
            program.toggle(pointer + offset.getValue(memory))
            //println("debug 2")
            memory[INSTRUCTION_ADDRESS]++
        }

        private fun String.getValue(m: IntArray): Int = if (length == 1 && first() in "abcd") m[this.last().toInt() - GET_REGISTER] else this.toInt()
    }
}
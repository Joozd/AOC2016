package shared.assembunny

abstract class AssemBunnyComponent {


    companion object{
        @JvmStatic
        protected val GET_REGISTER = 'a'.toInt()-1
        @JvmStatic
        protected val INSTRUCTION_ADDRESS = 0
        @JvmStatic
        protected val INC = "inc"
        @JvmStatic
        protected val DEC = "dec"
        @JvmStatic
        protected val JNZ = "jnz"

        @JvmStatic
        protected fun getRegister(reg: Char): Int = reg.toInt()- GET_REGISTER
        @JvmStatic
        protected fun getRegister(reg: String): Int = reg.first().toInt()- GET_REGISTER


        @JvmStatic
        protected val cpy: (IntArray, Int, Int) -> Unit = { memory, target, amount ->
            memory[target] = amount
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val cpyFrom: (IntArray, Int, Int) -> Unit = { memory, target, source ->
            memory[target] = memory[source]
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val inc: (IntArray, Int, Int) -> Unit = { memory, target, _ ->
            memory[target]++
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val dec: (IntArray, Int, Int) -> Unit = { memory, target, _ ->
            memory[target]--
            memory[INSTRUCTION_ADDRESS]++
        }

        @JvmStatic
        protected val jnz: (IntArray, Int, Int) -> Unit = { memory, target, amount ->
            memory[INSTRUCTION_ADDRESS] += if (memory[target] != 0) amount else 1
        }

        @JvmStatic
        protected val jnzFrom: (IntArray, Int, Int) -> Unit = { memory, fixedValue, amount ->
            memory[INSTRUCTION_ADDRESS] += if (fixedValue != 0) amount else 1
        }
    }
}
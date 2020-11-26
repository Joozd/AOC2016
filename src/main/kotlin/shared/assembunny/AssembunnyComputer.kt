package shared.assembunny

import utils.extensions.isDigits
import utils.extensions.splitToWords

class AssembunnyComputer(private val memory: IntArray, private val program: MutableList<Instruction>) {
    fun run(){
        while(memory[INSTRUCTION_ADDRESS] < program.size){
            //println("instruction ${memory[INSTRUCTION_ADDRESS]}: ${program[memory[INSTRUCTION_ADDRESS]]}")
            program[memory[INSTRUCTION_ADDRESS]].execute()
        }
    }

    val result: String
        get() = memory[getRegister('a')].toString()


    companion object{
        private const val GET_REGISTER = 'a'.toInt()-1
        private const val INSTRUCTION_ADDRESS = 0
        private const val INC = "inc"
        private const val DEC = "dec"
        private const val JNZ = "jnz"

        private val cpy: (IntArray, Int, Int) -> Unit = { memory, target, amount ->
            memory[target] = amount
            memory[INSTRUCTION_ADDRESS]++
        }

        private val cpyFrom: (IntArray, Int, Int) -> Unit = { memory, target, source ->
            memory[target] = memory[source]
            memory[INSTRUCTION_ADDRESS]++
        }

        private val inc: (IntArray, Int, Int) -> Unit = { memory, target, _ ->
            memory[target]++
            memory[INSTRUCTION_ADDRESS]++
        }

        private val dec: (IntArray, Int, Int) -> Unit = { memory, target, _ ->
            memory[target]--
            memory[INSTRUCTION_ADDRESS]++
        }

        private val jnz: (IntArray, Int, Int) -> Unit = { memory, target, amount ->
            memory[INSTRUCTION_ADDRESS] += if (memory[target] != 0) amount else 1
        }

        private val jnzFrom: (IntArray, Int, Int) -> Unit = { memory, fixedValue, amount ->
            memory[INSTRUCTION_ADDRESS] += if (fixedValue != 0) amount else 1
        }

        fun create(m: IntArray, prog: List<String>): AssembunnyComputer =
            AssembunnyComputer(m, prog.map{ parseInstruction(m, it) }.toMutableList())



        fun getRegister(reg: Char): Int = reg.toInt()- GET_REGISTER
        fun getRegister(reg: String): Int = reg.first().toInt()- GET_REGISTER

        private fun parseInstruction(m: IntArray, s: String): Instruction =
            when{
                s.startsWith(INC) -> Instruction(inc, m, getRegister(s.last()), 0)
                s.startsWith(DEC) -> Instruction(dec, m, getRegister(s.last()), 0)
                s.startsWith(JNZ) -> s.splitToWords().let{ w->
                    if (w[1].isDigits()) Instruction(jnzFrom, m, w[1].toInt(), w[2].toInt())
                    else Instruction(jnz, m, getRegister(w[1]), w.last().toInt())
                }
                else -> s.splitToWords().let{w ->
                    if (w[1].isDigits()) Instruction(cpy, m, getRegister(w[2]), w[1].toInt())
                    else Instruction(cpyFrom, m, getRegister(w[2]), getRegister(w[1]))
                }
            }

        class Instruction(private val f: (IntArray, Int, Int) -> Unit, private val  m: IntArray, private val p1: Int, private val p2: Int){
            fun execute() = f(m, p1, p2)
        }


    }
}
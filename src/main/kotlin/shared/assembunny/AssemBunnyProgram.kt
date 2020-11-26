package shared.assembunny

import utils.extensions.isDigits
import utils.extensions.splitToWords


/**
 * Bytecode program for AssemBunnyVM
 */
class AssemBunnyProgram(p: List<AssemBunnyInstruction>): AssemBunnyComponent() {
    private val program = p // not mutable needed i think
    operator fun get(index: Int) = if (index in program.indices) program[index] else null
    // operator fun set(index: Int, new: AssemBunnyInstruction) { if (index in program.indices) program[index] = new }

    fun dump(){
        program.forEachIndexed { index, it ->
            println("$index\t$it")
        }
    }


    /**
     * 1. For one-argument instructions, inc becomes dec, and all other one-argument instructions become inc.
     * 2. For two-argument instructions, jnz becomes cpy, and all other two-instructions become jnz.
     * 3. The arguments of a toggled instruction are not affected.
     * 4. If an attempt is made to toggle an instruction outside the program, nothing happens.
     * 5. If toggling produces an invalid instruction (like cpy 1 2) and an attempt is later made to execute that instruction, skip it instead.
     * 6. If tgl toggles itself (for example, if a is 0, tgl a would target itself and become inc a), the resulting instruction is not executed until the next time it is reached.
     */
    fun toggle(index: Int){
        if (index !in program.indices) return
        when(program[index].function){
            INC -> program[index].function = DEC // 1.
            DEC -> program[index].function = INC // 1.
            JNZ -> program[index].function = CPY // 2.
            CPY -> program[index].function = JNZ // 2.
            TGL -> program[index].function = INC // 6.
        }
    }
}
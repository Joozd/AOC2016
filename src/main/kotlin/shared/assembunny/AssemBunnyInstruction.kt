package shared.assembunny

import java.lang.Exception

/**
 * One instruction for an AssemBunny program
 */
class AssemBunnyInstruction(f: String, vararg parameters: String): AssemBunnyComponent() {
    var function = f
        set(it) { println("changing $field to $it")
        field = it
        }

    private val params = parameters.toList()

    fun execute(memory: IntArray, program: AssemBunnyProgram, dump: Boolean = false){
        if (dump) println(decompile())
        try {
            when (function) {
                NIL -> memory[INSTRUCTION_ADDRESS]++
                INC -> inc(memory, params[0])
                DEC -> dec(memory, params[0])
                JNZ -> jnz(memory, params[0], params[1])
                CPY -> cpy(memory, params[0], params[1])
                ADD -> add(memory, params[0], params[1])
                MUL -> mul(memory, params[0], params[1])
                TGL -> tgl(memory, params[0], program)
            }
        }catch(e: Exception) {
            println("skipping illegal instruction $function, [${params.joinToString { it }}] ($e)")
            memory[INSTRUCTION_ADDRESS]++
        }
    }

    private fun decompile() = when(function){
        INC, DEC, TGL -> "$function ${params[0]}"
        JNZ, CPY -> "$function ${params[0] } ${params[1]}"
        else -> "DECOMPILE ERROR: $function ${params.joinToString { it }}"
    }

    override fun toString() = decompile()
}
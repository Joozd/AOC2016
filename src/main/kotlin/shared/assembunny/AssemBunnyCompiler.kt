package shared.assembunny

import utils.extensions.splitToWords

class AssemBunnyCompiler(private val program: String): AssemBunnyComponent() {
    fun compile(): List<AssemBunnyInstruction> = program.lines().map{ s ->
        when {
            s.startsWith(NIL) -> AssemBunnyInstruction(NIL)
            s.startsWith(ADD) -> s.splitToWords().let { w ->
                AssemBunnyInstruction(ADD, w[1], w[2])
            }
            s.startsWith(MUL) -> s.splitToWords().let { w ->
                AssemBunnyInstruction(MUL, w[1], w[2])
            }
            s.startsWith(INC) -> AssemBunnyInstruction(INC, s.last().toString())
            s.startsWith(DEC) -> AssemBunnyInstruction(DEC, s.last().toString())
            s.startsWith(TGL) -> AssemBunnyInstruction(TGL, s.last().toString())
            s.startsWith(JNZ) -> s.splitToWords().let { w ->
                AssemBunnyInstruction(JNZ, w[1], w[2])
            }
            else -> s.splitToWords().let { w ->
                AssemBunnyInstruction(CPY, w[1], w[2])
            }
        }
    }
}
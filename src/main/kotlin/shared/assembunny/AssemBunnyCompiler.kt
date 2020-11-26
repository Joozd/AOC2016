package shared.assembunny

import utils.extensions.isDigits
import utils.extensions.splitToWords

class AssemBunnyCompiler(private val program: String): AssemBunnyComponent() {
    fun compile(): List<AssemBunnyInstruction> = program.lines().map{ s ->
        when{
            s.startsWith(INC) -> AssemBunnyInstruction(getRegister(s.last()), 0, inc)
            s.startsWith(DEC) -> AssemBunnyInstruction(getRegister(s.last()), 0, dec)
            s.startsWith(JNZ) -> s.splitToWords().let{ w->
                if (w[1].isDigits()) AssemBunnyInstruction(w[1].toInt(), w[2].toInt(), jnzFrom)
                else AssemBunnyInstruction(getRegister(w[1]), w.last().toInt(), jnz)
            }
            else -> s.splitToWords().let{w ->
                if (w[1].isDigits()) AssemBunnyInstruction(AssembunnyComputer.getRegister(w[2]), w[1].toInt(), cpy)
                else AssemBunnyInstruction(AssembunnyComputer.getRegister(w[2]), AssembunnyComputer.getRegister(w[1]), cpyFrom)
            }
        }
    }
}
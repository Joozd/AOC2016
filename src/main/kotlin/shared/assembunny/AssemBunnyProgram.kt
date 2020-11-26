package shared.assembunny

import utils.extensions.isDigits
import utils.extensions.splitToWords


/**
 * Bytecode program for AssemBunnyVM
 */
class AssemBunnyProgram(private val program: List<AssemBunnyInstruction>) {
    operator fun get(index: Int) = if (index in program.indices) program[index] else null
}
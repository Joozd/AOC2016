package shared.assembunny

class AssemBunnyVM(private val memory: IntArray, private val program: AssemBunnyProgram): AssemBunnyComponent() {
    fun run(dump: Boolean = false){
        if (dump) {
            println(memory.toList())
            //program.dump()
            //print("\n")
        }


        while(true){
            //println("instruction ${memory[INSTRUCTION_ADDRESS]}: ${program[memory[INSTRUCTION_ADDRESS]]}")
            program[memory[INSTRUCTION_ADDRESS]]?.execute(memory, program, false) ?: return
            if (dump) {
                println(memory.toList())
                //if (dump) program.dump()
                //print("\n")
            }
        }

    }

    val result: String
        get() = memory[getRegister('a')].toString()

    companion object{
        fun create(m: IntArray, prog: String): AssemBunnyVM =
                AssemBunnyVM(m, AssemBunnyProgram(AssemBunnyCompiler(prog).compile()))
    }
}
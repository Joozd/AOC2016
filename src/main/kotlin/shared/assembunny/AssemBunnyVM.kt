package shared.assembunny

class AssemBunnyVM(private val memory: IntArray, private val program: AssemBunnyProgram): AssemBunnyComponent() {
    fun run(){
        while(true){
            //println("instruction ${memory[INSTRUCTION_ADDRESS]}: ${program[memory[INSTRUCTION_ADDRESS]]}")
            program[memory[INSTRUCTION_ADDRESS]]?.execute(memory) ?: return
        }
    }

    val result: String
        get() = memory[getRegister('a')].toString()

    companion object{
        fun create(m: IntArray, prog: String): AssemBunnyVM =
                AssemBunnyVM(m, AssemBunnyProgram(AssemBunnyCompiler(prog).compile()))
    }
}
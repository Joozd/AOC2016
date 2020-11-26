package shared.assembunny

/**
 * One instruction for an AssemBunny program
 */
class AssemBunnyInstruction(var p1: Int, var p2: Int, var function: (IntArray, Int, Int) -> Unit): AssemBunnyComponent() {
    fun execute(memory: IntArray){
        //println(decompile())
        function(memory, p1, p2)
    }
    fun decompile(): String = when (function){
        inc -> "inc ${(p1 + 'a'.toInt()).toChar()}"
        dec -> "dec ${(p1 + 'a'.toInt()).toChar()}"
        jnzFrom -> "jnz ${(p1 + 'a'.toInt()).toChar()} $p2"
        jnz -> "jnz $p1 $p2"
        cpyFrom -> "cpy ${(p1 + 'a'.toInt()).toChar()} $p2"
        cpy -> "cpy $p1 $p2"


        else -> "DECOMPILE ERROR"
    }
}
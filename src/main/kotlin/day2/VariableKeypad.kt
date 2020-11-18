package day2

class VariableKeypad(private val keys: List<List<Char?>>) {
    private val validKeypad = keys.size%2 == 1 && keys.first().size%2 == 1 && keys.all{it.size == keys.first().size}
    init{
        require(validKeypad) { "Keypads need to have an odd number of lines, with an odd number of positions"}
    }
    private var row = keys.size / 2
    private var column = keys.first().size / 2

    fun u(){
        if (row-1 in keys.indices && keys[row-1][column] != null) row--
    }

    fun d(){
        if (row+1 in keys.indices && keys[row+1][column] != null) row++
    }

    fun l(){
        if (column-1 in keys.first().indices && keys[row][column-1] != null) column--
    }

    fun r(){
        if (column+1 in keys.first().indices && keys[row][column+1] != null) column++
    }

    override fun toString() = "${keys[row][column]}"


}
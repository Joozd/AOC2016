package day8

import utils.extensions.getDigits

class TinyDisplayUnit(private val height: Int, private val width: Int) {
    private val pixels = Array<IntArray>(height){ IntArray(width){ 0 } }

    /**
     * Set the top left y by x square to 1
     */
    private fun rect(width: Int, height: Int){
        (0 until height).forEach {y ->
            (0 until width).forEach {
                x -> pixels[y][x] = 1
            }
        }
    }

    /**
     * shift a row to the right, wrapping around
     */
    private fun rotateRow(rowNumber: Int, distance: Int){
        val oldRow = pixels[rowNumber]
        val newRow = (oldRow.takeLast(distance) + oldRow.take(width - distance)).toIntArray()
        pixels[rowNumber] = newRow
    }

    /**
     * shift a column down, wrapping around
     */
    private fun rotateColumn(columnNumber: Int, distance: Int){
        val oldColumn = pixels.indices.map{pixels[it][columnNumber]}
        val newColumn = (oldColumn.takeLast(distance) + oldColumn.take(height - distance)).toIntArray()
        pixels.indices.forEach {
            pixels[it][columnNumber] = newColumn[it]
        }
    }

    fun execute(command: String, verbose: Boolean = false){
        val words = command.split(' ')
        when{
            words[0] == RECT -> words[1].split('x').map{it.toInt()}.let {
                rect(it[0], it[1])
            }
            command.startsWith(ROTATE_ROW) -> {
                val dist = words[4].toInt()
                val rowNumber = words[2].getDigits().toInt()
                rotateRow(rowNumber, dist)
            }
            command.startsWith(ROTATE_COLUMN) -> {
                val dist = words[4].toInt()
                val columnNumber = words[2].getDigits().toInt()
                rotateColumn(columnNumber, dist)
            }
            else -> error("error 1: BAD COMMAND")
        }
        if (verbose) println(command + "\n" + this.toString() + "\n")
    }

    val illuminatedPixels
        get() = pixels.sumBy{it.sum()}


    override fun toString(): String =
        pixels.map{l ->
            l.map{ p -> if (p == 0) ' ' else '█'}.joinToString("")
        }.joinToString("\n")



    companion object{

        const val RECT = "rect"
        const val ROTATE_ROW = "rotate row"
        const val ROTATE_COLUMN = "rotate column"

    }
}
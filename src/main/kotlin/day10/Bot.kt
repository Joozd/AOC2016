package day10

import utils.extensions.getOnlyNumbers

class Bot(private val botsMap: Map<Int, Bot>, private val outputMap: MutableMap<Int, Int>, val id: Int, private val lowTarget: Int, private val highTarget: Int, private val lowToOutput: Boolean, private val highToOutput: Boolean) {
    private val chips = ArrayList<Int>(2)

    private var seventeenToSixtyone: Boolean = false

    val winner: Boolean
        get() = seventeenToSixtyone

    fun give(chip: Int){
        chips.add(chip)
        if (chips.size == 2){
            val chipsToGive = chips.toList()
            if (chipsToGive.sorted() == listOf(17, 61)) seventeenToSixtyone = true.also{
                println (this)
            }
            chips.clear()
            if(lowToOutput) outputMap[lowTarget] = chipsToGive.minOrNull()!!
            else (botsMap[lowTarget] ?: error("ERROR 1: NONEXISTENT BOT $lowTarget")).give(chipsToGive.minOrNull()!!)

            if(highToOutput) outputMap[highTarget] = chipsToGive.maxOrNull()!!
            else (botsMap[highTarget] ?: error("ERROR 1: NONEXISTENT BOT $highTarget")).give(chipsToGive.maxOrNull()!!)
        }
    }

    override fun toString(): String = "Bot #$id holding ${chips.toList()}"

    companion object{
        fun insert(botsMap: MutableMap<Int, Bot>, outputMap: MutableMap<Int, Int>, s: String){
            //bot 111 gives low to output 18 and high to bot 103
            val number: Int
            val low: Int
            val high: Int
            val lowToOutput: Boolean
            val highToOutput: Boolean
            s.getOnlyNumbers().let{numbers ->
                number = numbers[0]
                require(botsMap[number] == null) { "Bot already exists! s = $s"}
                low = numbers[1]
                high = numbers[2]
            }
            s.split(' ').let { words ->
                lowToOutput = words[5] == "output"
                highToOutput = words[10] == "output"
            }
            botsMap[number] = Bot(botsMap, outputMap, number, low, high, lowToOutput, highToOutput)
        }
    }
}
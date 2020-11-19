package day10

import Solution
import utils.extensions.getOnlyNumbers

class Day10(day: Int): Solution(day) {
    private val botsMap = HashMap<Int, Bot>()
    private val outputs = HashMap<Int, Int>()

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String {
        inputLines.filter{it.startsWith("bot")}.map{
            Bot.insert(botsMap, outputs, it)
        }
        inputLines.filter{!it.startsWith("bot")}.map{ l ->
            l.getOnlyNumbers().let{
                botsMap[it[1]]!!.give(it[0])
            }
        }
        return botsMap.filter { it.value.winner }.keys.first().toString()
    }
    private fun two(): String {
        return (outputs[0]!! * outputs[1]!! * outputs[2]!!).toString()
    }
}
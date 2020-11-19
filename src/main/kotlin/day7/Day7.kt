package day7

import Solution

class Day7(day: Int): Solution(day) {

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String = inputLines.filter{it.hasTLS()}.size.toString()

    private fun two(): String = inputLines.filter{it.hasSSL()}.size.toString()

    /**
     * Will return true if an ABBA sequence is found
     */
    private fun String.hasAbba(): Boolean{
        if (length < 4) return false
        (0 until length-3).forEach{
            val s = this.drop(it).take(4)
            if (s[0] != s[1] && s.take(2) == s.drop(2).reversed()) return true
        }
        return false
    }

    private fun String.getAbaList(): List<String> {
        if (length < 3) return emptyList()
        val foundAbaList = ArrayList<String>()
        (0 until length-2).forEach{
            val s = this.drop(it).take(3)
            if (s[0] == s[2] && s[0]!= s[1]) foundAbaList.add(s)
        }
        return foundAbaList
    }

    private fun String.checkBab(aba: String): Boolean{
        val bab = "${aba[1]}${aba[0]}${aba[1]}"
        return bab in this
    }

    private fun String.hasTLS(): Boolean{
        if (getHypernetSequences().any{
            it.hasAbba()
        }) return false // due to abba in brackets
        return this.hasAbba()
    }

    private fun String.getHypernetSequences(): Sequence<String> =
        "\\[[a-z]*]".toRegex().findAll(this).map{it.value}

    private fun String.getSupernetSequences(alreadyFound: List<String> = emptyList()): List<String> {
        if (isEmpty()) return emptyList()
        return if ('[' in this){
            val found = take(indexOf('['))
            drop(indexOf(']')+1).getSupernetSequences(alreadyFound + found)
        } else alreadyFound + this
    }

    private fun String.hasSSL(): Boolean =
        this.getSupernetSequences().map{it.getAbaList()}.flatten().any{aba ->
            this.getHypernetSequences().any { hns -> hns.checkBab(aba)
        }
    }
}
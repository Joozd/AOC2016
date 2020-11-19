package day5

import java.security.MessageDigest
import Solution

class Day5(day: Int): Solution(day) {
    override val first: String
        get() = one()
    override val second: String
        get() = two()

    // global counter so we save 2 seconds of work
    private var counter = 0

    private val firstEightHashes: List<ByteArray> by lazy{
        val hashes = ArrayList<ByteArray>()

        //for dramatic effect!
        println(hashes.joinToString("") { it.getCharacter(6) }.padEnd(8, '.'))

        repeat(8){
            do{
                val c = checkHashForLeadingZeroes("$inputString$counter")?.also{ h ->
                    hashes.add(h)

                    //for dramatic effect!
                    println(hashes.joinToString("") { it.getCharacter(6) }.padEnd(8, '.'))
                }
                counter++
            } while(c == null)
        }
        hashes
    }



    private fun one() = firstEightHashes.joinToString("") { it.getCharacter(6) }

    private fun two(): String {
        val answer = Array<Char?>(8){ null }
        var previousAnswer = answer.toList()
        firstEightHashes.forEach { h ->
            insertIfAble(h, answer)

            // for dramatic effect!
            if (previousAnswer != answer.toList()){
                previousAnswer = answer.toList()
                println(answer.joinToString("") { (it ?: '.').toString() })
            }
        }
        while(answer.any{it == null}){
            do{
               val c = checkHashForLeadingZeroes("$inputString$counter")?.also{ h ->
                   insertIfAble(h, answer)

                    //for dramatic effect!
                    if (previousAnswer != answer.toList()){
                        previousAnswer = answer.toList()
                        println(answer.joinToString("") { (it ?: '.').toString() })
                    }
                }
                counter++
            } while(c == null)
        }
        return answer.joinToString("")
    }

    /**
     * Will return a hash starting with 5 zeroes or null
     */
    private fun checkHashForLeadingZeroes(s: String): ByteArray? {
        val hash = MessageDigest.getInstance("MD5").digest(s.toByteArray(Charsets.UTF_8))
        return if (hash[0] == hash[1] && hash[0] == 0.toByte() && hash[2].toInt().shr(4) == 0)
            hash
        else null
    }

    private fun ByteArray.getCharacter(index: Int): String {
        val i = (index-1)/2
        val left = index%2 == 1
        return if (left) (get(i).toInt().and(BITMASK_4_LEFT)).shr(4).toString(16)
        else get(i).toInt().and(BITMASK_4_RIGHT).toString(16)
    }
    private fun ByteArray.getCharacterAsInt(index: Int): Int {
        val i = (index-1)/2
        val left = index%2 == 1
        return if (left) (get(i).toInt().and(BITMASK_4_LEFT)).shr(4)
        else get(i).toInt().and(BITMASK_4_RIGHT)
    }


    private fun insertIfAble(hash: ByteArray, passwrd: Array<Char?>){
        val i = hash.getCharacterAsInt(6)
        //println("Trying to insert ${hash.getCharacter(7)} at position $i...")
        if (i !in (0..7) || passwrd[i] != null) return // if we get past this, we can insert the digit!
        passwrd[i] = hash.getCharacter(7).first()
    }

    companion object{
        const val BITMASK_4_RIGHT = 0xF
        const val BITMASK_4_LEFT = 0xF0
    }
}



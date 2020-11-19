package day4


class Room(private val encryptedName: String, val sectorID: Int, checksum: String) {
    private val characterCount = encryptedName.filter{it != '-'}.toList().sorted().groupingBy { it }.eachCount().toList()

    // First sort alphabetically, then sort by count
    @Suppress("ConvertCallChainIntoSequence") // Converting to a sequence in this case seems actually some 5% slower
    private val calculatedChecksum = characterCount.sortedBy{it.first}.sortedByDescending { it.second }.take(5).map{it.first}.joinToString("")

    val isReal = checksum == calculatedChecksum

    val actualName = encryptedName.toList().map{(it.toInt() + sectorID - A) % 26 + A}.map{it.toChar()}.toMutableList().apply{
        spaceRegEx.findAll(encryptedName).forEach{
            this[it.range.first] = ' '
        }
    }.joinToString("")



    companion object{
        const val A = 'a'.toInt()

        val spaceRegEx = "-".toRegex()
        private val roomRegex = "([a-z-]*)-([0-9]*)\\[([a-z]{5})]".toRegex()
        fun parse(s: String): Room =
            roomRegex.find(s)!!.groupValues.let{
                Room(it[1], it[2].toInt(), it[3])
            }

    }
}


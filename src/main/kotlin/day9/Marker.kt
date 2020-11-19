package day9

class Marker(val length: Int, val repeats: Int, val ownLength: Int = 0) {
    companion object{
        fun parse(input: List<Char>): Marker{
            require(input.size > 2 && input.first() == '(' && input.last() == ')') { "Marker error 1: Invalid marker string: ${input.joinToString("")}"}
            return input.drop(1).dropLast(1).joinToString("").split('x').map{it.toInt()}.let{
                Marker(it[0], it[1], input.size)
            }
        }
        fun parse(input: String) = parse(input.toList())
    }
}
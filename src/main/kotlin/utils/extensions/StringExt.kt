package utils.extensions

fun String.isDigits() = this.all{it.isDigit()}
fun String.getDigits() = this.filter { it in "1234567890-" }

fun String.getNumber(): Int = if (none{it.isDigit()}) 0 else {
    val firstNumber = indexOf(firstOrNull { it.isDigit() }!!)
    val negative = if (firstNumber > 0) get(firstNumber) == '-' else false
    val abs = getDigits().toInt()
    if (negative) abs * -1 else abs
}

fun String.splitToWords() = this.split(' ')

fun String.getOnlyNumbers(): List<Int> = this.splitToWords().filter{it.isDigits()}.map{it.toInt()}
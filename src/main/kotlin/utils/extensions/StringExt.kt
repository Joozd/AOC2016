package utils.extensions

fun String.isDigits() = this.all{it.isDigit()}
fun String.getDigits() = this.filter { it.isDigit() }
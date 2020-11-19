package utils.extensions

fun String.isDigits() = this.all{it.isDigit()}
fun String.getDigits() = this.filter { it.isDigit() }

fun String.splitWords() = this.split(' ')

fun String.getOnlyNumbers(): List<Int> = this.splitWords().filter{it.isDigits()}.map{it.toInt()}
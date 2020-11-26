package utils

import java.io.File

fun getExtraInputLinesForDay(day: Int, extension: String): List<String> =
        File("inputs\\day$day$extension.txt").readLines()

fun getExtraInputForDay(day: Int, extension: String): String =
        File("inputs\\day$day$extension.txt").readText()
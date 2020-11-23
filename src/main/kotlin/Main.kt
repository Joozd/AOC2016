import utils.Timer

@ExperimentalUnsignedTypes
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for. 0 for all!")
            val input = readLine()
            println("got input $input")
            when (input) {
                "1" -> day1.Day1(1).run()
                "2" -> day2.Day2(2).run()
                "3" -> day3.Day3(3).run()
                "4" -> day4.Day4(4).run()
                "5" -> day5.Day5(5).run()
                "6" -> day6.Day6(6).run()
                "7" -> day7.Day7(7).run()
                "8" -> day8.Day8(8).run()
                "9" -> day9.Day9(9).run()
                "10" -> day10.Day10(10).run()
                "11" -> day11.Day11(11).run()
                "12" -> day12.Day12(12).run()
                "13" -> day13.Day13(13).run()
                "14" -> day14.Day14(14).run()
                "15" -> day15.Day15(15).run()
                "16" -> day16.Day16(16).run()

                "0" -> {
                    val totalTime = day1.Day1(1).run() +
                            day2.Day2(2).run() +
                            day3.Day3(3).run() +
                            day4.Day4(4).run() +
                            day5.Day5(5).run() +
                            day6.Day6(6).run() +
                            day7.Day7(7).run() +
                            day8.Day8(8).run() +
                            day9.Day9(9).run() +
                            day10.Day10(10).run() +
                            day11.Day11(11).run() +
                            day12.Day12(12).run() +
                            day13.Day13(13).run() +
                            day14.Day14(14).run() +
                            day15.Day15(15).run() +
                            day16.Day16(16).run()

                    println("\n\nDone, total time: ${totalTime.toMillis()}.${totalTime.toNanosPart()} milliseconds.")
                }


                else -> println("Invalid input\n")
            }
        }
    }
}
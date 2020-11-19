import utils.Timer

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for. 0 for all!")
            when (readLine()) {
                "1" -> day1.Day1(1).run()
                "2" -> day2.Day2(2).run()
                "3" -> day3.Day3(3).run()
                "4" -> day4.Day4(4).run()
                "5" -> day5.Day5(5).run()

                "0" -> {
                    val totalTime = day1.Day1(1).run() +
                            day2.Day2(2).run() +
                            day3.Day3(3).run() +
                            day4.Day4(4).run() +
                            day5.Day5(5).run()

                    println("\n\nDone, total time: ${totalTime.toMillis()}.${totalTime.toNanosPart()} milliseconds.")
                }


                else -> println("Invalid input\n")
            }
        }
    }
}
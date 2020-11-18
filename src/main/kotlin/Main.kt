import utils.Timer

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for. 0 for all!")
            when (readLine()) {
                "1" -> day1.Day1(1).run()
                "2" -> day2.Day2(2).run()

                "0" -> {
                    val t = Timer().apply{ start() }
                    day1.Day1(1).run()
                    day2.Day2(2).run()

                    println("\n\nDone, total time: ${t.getElapsedTime().toMillis()} milliseconds.")
                }


                else -> println("Invalid input\n")
            }
        }
    }
}
package day9

/**
 * Class that represents compressed data according to AOC 2016 day 9
 */
class CompressedData(rawData: String, multiplier: Int = 1) {
    private val children: List<CompressedData>
    private val childless: Boolean

    init{
        val lll = ArrayList<CompressedData>()
        var workingData = rawData
        var regexResult = markerRegex.find(workingData)
        childless = regexResult == null
        while (regexResult != null){
            //println(regexResult.value)
            if (regexResult.range.first != 0) {
                lll.add(CompressedData(workingData.take(regexResult.range.first)))

                //take loose data at front:
                workingData = workingData.drop(regexResult.range.first)
            }

            //take marked data
            val marker = Marker.parse(regexResult.value)
            lll.add(
                CompressedData(workingData.drop(marker.ownLength).take(marker.length), marker.repeats)
            )
            workingData = workingData.drop(marker.length + marker.ownLength)
            regexResult = markerRegex.find(workingData)

        }
        if (workingData.isNotEmpty() && !childless) lll.add(CompressedData(workingData))
        children = lll
    }





    val length: Long = (if (childless) rawData.length.toLong() else children.fold(0L) { acc, c -> acc + c.length }) * multiplier



    companion object{
        private val markerRegex = "\\([0-9]*x[0-9]*\\)".toRegex()
    }
}
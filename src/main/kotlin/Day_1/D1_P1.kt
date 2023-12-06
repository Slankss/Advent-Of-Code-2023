package Day_1

import Function.readText

/*
    Question :
        The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves
        now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order)
        to form a single two-digit number.

        example : 1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet

        In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

        Consider your entire calibration document. What is the sum of all of the calibration values?

 */
fun main(){
    var lineList = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_1\\assets\\Day1_input.txt")
    var calibrationSum = 0
    lineList.forEach{
        calibrationSum += findCalibrationValue2(it)
    }
    println(calibrationSum)
}

private fun findCalibrationValue(line : String) : Int{

    var firstDigit : Int? = null
    var lastDigit : Int? = null

    for(c in line){
        if(c.isDigit()){
            if(firstDigit == null){
                firstDigit = c.toString().toInt()
                continue
            }
            lastDigit = c.toString().toInt()
        }
    }
    if(firstDigit == null){
        return 0
    }

    if(lastDigit == null){
        lastDigit = firstDigit
    }

    return (firstDigit.toString()+lastDigit.toString()).toInt()
}

fun findCalibrationValue2(line : String) : Int{
    var digitList = listOf<String>()
    for(c in line){
        if(c.isDigit()){
            digitList += c.toString()
        }
    }
    return if (digitList.size < 1) 0 else (digitList[0]+digitList[digitList.size-1]).toInt()
}

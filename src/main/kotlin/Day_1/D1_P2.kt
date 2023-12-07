package Day_1

import Function.readText

fun main(){

    var lineList = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_1\\assets\\Day1_input.txt")
    var calibrationSum = 0

    lineList.forEach{
        calibrationSum += findCalibrationValue(it)
    }
    println(calibrationSum)

}

private fun findCalibrationValue(line : String) : Int{

    var digitList = listOf<Int>()
    val digitMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    var numberStr = ""
    for( c in line){
        if(!c.isDigit()){
            numberStr += c
            for(digit in digitMap){
                if(numberStr.contains(digit.key)){
                    digitList += digit.value
                    numberStr = "$c"
                    break
                }
            }
            continue
        }
        digitList+=c.toString().toInt()
        numberStr = ""
    }
    var digitSum=if(digitList.isEmpty()) 0 else ("${digitList[0]}${digitList[digitList.size-1]}").toInt()
    println(digitSum)

    return if(digitList.isEmpty()) 0 else ("${digitList[0]}${digitList[digitList.size-1]}").toInt()
}
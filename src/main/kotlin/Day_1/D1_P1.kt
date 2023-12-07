package Day_1

import Function.readText

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

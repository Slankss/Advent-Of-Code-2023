package DayOne

import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\puzzle1.txt").inputStream()
    val lineList = mutableListOf<String>()

    var calibrationSum = 0
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    lineList.forEach{

        calibrationSum += findCalibrationValue(it)
    }
    println(calibrationSum)
}

fun findCalibrationValue(line : String) : Int{

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
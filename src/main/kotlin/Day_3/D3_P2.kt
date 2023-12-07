package Day_3

import Function.readText
import java.awt.font.NumericShaper.Range

fun main(){

    var engine = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_3\\assets\\Day3_input.txt")

    var sum = findEngineParts(engine)
    println(sum)

}
private fun findEngineParts(engine : MutableList<String>) : Int{

    val specialCharList = ConstantValues.specialCharList
    var enginePartRatioList = arrayListOf<Int>()
    for(row in 0..<engine.size){
        for(col in 0..<engine[row].length){
            if(specialCharList.contains(engine[row][col])){
                var gearList = arrayListOf<Int>()
                var top = if(row - 1 >= 0) row -1 else null
                var bottom = if(row +1 < engine.size) row + 1 else null
                var left = if(col - 1 >=0) col -1 else col
                var right = if(col + 1 < engine[row].length) col + 1 else col
                if(top != null){
                    var startIndex = left
                    var lastIndex = right
                    while(startIndex >=0 && engine[top][startIndex].isDigit()){
                        startIndex--
                    }
                    while(lastIndex < engine[row].length && engine[top][lastIndex].isDigit()){
                        lastIndex++
                    }
                    gearList.addAll(findAroundNumber(top,startIndex+1,lastIndex-1))
                }
                if(bottom != null){
                    var startIndex = left
                    var lastIndex = right
                    while(startIndex >=0 && engine[bottom][startIndex].isDigit()){
                        startIndex--
                    }
                    while(lastIndex < engine[row].length && engine[bottom][lastIndex].isDigit()){
                        lastIndex++
                    }
                    gearList.addAll(findAroundNumber(bottom,startIndex+1,lastIndex-1))
                }

                if(left != null){
                    var startIndex = left
                    var lastIndex = left
                    if(startIndex != col){
                        while(startIndex >=0 && engine[row][startIndex].isDigit()){
                            startIndex--
                        }
                        gearList.addAll(findAroundNumber(row,startIndex+1,lastIndex))
                    }
                }
                if(right != null){
                    var startIndex = right
                    var lastIndex = right
                    if(startIndex != col){
                        while(startIndex >=0 && engine[row][lastIndex].isDigit()){
                            lastIndex++
                        }
                        gearList.addAll(findAroundNumber(row,startIndex,lastIndex-1))
                    }
                }
                if(gearList.size > 1){
                    var ratio = 1
                    gearList.forEach {
                        ratio *= it
                    }
                    enginePartRatioList.add(ratio)
                }
            }
        }
    }
    return enginePartRatioList.sum()
}

fun findAroundNumber(row : Int,startCol : Int,endCol : Int) : ArrayList<Int>{

    var engine = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_3\\assets\\Day3_input.txt")

    var numberList = arrayListOf<Int>()
    var number = ""
    for(col in startCol..endCol){
        if(engine[row][col].isDigit()){
            number+=engine[row][col]
            if(col == endCol)
                numberList.add(number.toInt())
            continue
        }
        else if(number.isNotEmpty()){
            numberList.add(number.toInt())
        }
        number = ""

    }
    return numberList
}
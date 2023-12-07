package Day_3

import Function.readText

fun main(){

    var engine = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_3\\assets\\Day3_input.txt")

    var sum = findEngineParts(engine)
    println(sum)

}

private fun findEngineParts(engine : MutableList<String>) : Int{

    var enginePartList = listOf<Int>()
    val specialCharList = ConstantValues.specialCharList

    var number = ""
    for( row in 0..<engine.size){
        for(column in 0..<engine[row].length){
            var c = engine[row][column]
            if(c.isDigit()){
                number += c
            }
            if(column == engine[row].length-1 || !c.isDigit())
            {
                if(number.isNotEmpty()){
                    var new_col = column - number.length
                    var isEnginePart = false
                    var top = if (row -1 >= 0) row -1 else -1
                    var bottom = if(row +1 < engine.size) row +1 else -1

                    for(i in -1..number.length){
                        if(top >= 0 && new_col + i >= 0 && column +i < engine[row].length && specialCharList.contains(engine[row-1][new_col+i])){
                            isEnginePart = true
                            break
                        }
                        if(bottom >= 0 && new_col + i >= 0 && column +i < engine[row].length && specialCharList.contains(engine[row+1][new_col+i])){
                            isEnginePart = true
                            break
                        }
                        if(i == -1 && new_col-1 >=0 && specialCharList.contains(engine[row][new_col+i])){
                            // left
                            isEnginePart = true
                            break
                        }
                        else if(i == number.length && column + 1 < engine[row].length && specialCharList.contains(engine[row][column])){
                            // right
                            isEnginePart = true
                            break
                        }
                    }
                    if(isEnginePart){
                        enginePartList += number.toInt()
                        println("[${row+1}]${number}")
                    }
                }
                number=""
            }
        }
    }
    return enginePartList.sum()
}
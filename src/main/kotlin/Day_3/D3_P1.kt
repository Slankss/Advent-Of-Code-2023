package Day_3

import Function.readText

/*
    Question
    The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
    If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.

    The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand,
    but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)

    Here is an example engine schematic:

    467..114..
    ...*......
    ..35..633.
    ......#...
    617*......
    .....+.58.
    ..592.....
    ......755.
    ...$.*....
    .664.598..

    In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right).
    Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

    Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?

 */
fun main(){

    var engine = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_3\\assets\\Day3_input.txt")

    var sum = findEngineParts(engine)
    println(sum)

}

fun findEngineParts(engine : MutableList<String>) : Int{

    var enginePartList = listOf<Int>()
    var specialCharList = listOf<Char>('*','=','/','$','#','@','+','&','%','-')

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
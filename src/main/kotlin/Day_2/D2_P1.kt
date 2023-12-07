package Day_2

import Function.readText

fun main(){

    var lineList = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_2\\assets\\Day2_input.txt")

    var gameId = 1
    var gameIdSum = 0
    lineList.forEach {
        if(game(it)){
            gameIdSum += gameId
        }
        gameId++
    }
    println(gameIdSum)

}

private fun game(line : String) : Boolean{

    var colorMap = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    var newLine = line.replace(" ","")
    var gameState = GameTurn.NOT_STARTED
    var colorCount = ""
    var colorName = ""
    var index = 0
    for(c in newLine){
        if(gameState == GameTurn.STARTED){
            if(c.isDigit()){
                colorCount += c
            }
            else if(c == ',' || c == ';'){
                colorMap[colorName]?.let {
                    if(colorCount.toInt() > it)
                        return false
                }
                colorName = ""
                colorCount = ""
            }
            else if(index == newLine.length-1){
                colorName+=c
                colorMap[colorName]?.let {
                    if(colorCount.toInt() > it)
                        return false
                }
            }
            else{
                colorName+=c
            }
        }
        if(c == ':')
            gameState = GameTurn.STARTED
        index++
    }
    return true
}

enum class GameTurn{
    NOT_STARTED,
    STARTED
}


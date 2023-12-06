package Day_2

import Function.readText

fun main(){

    var lineList = readText("C:\\Users\\Okan\\IdeaProjects\\AdventOfCodePuzzles\\src\\main\\kotlin\\Day_2\\assets\\Day2_input.txt")

    var gameId = 1
    var gamePowSum = 0
    lineList.forEach {
        gamePowSum += game(it)

        gameId++
    }
    println(gamePowSum)

}

private fun game(line : String) : Int{

    var colorMap = hashMapOf(
        "red" to 0,
        "green" to 0,
        "blue" to 0
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
                    if(it < colorCount.toInt()){
                        colorMap[colorName] = colorCount.toInt()
                    }
                }
                colorName = ""
                colorCount = ""
            }
            else if(index == newLine.length-1){
                colorName+=c
                colorMap[colorName]?.let {
                    if(it < colorCount.toInt()){
                        colorMap[colorName] = colorCount.toInt()
                    }
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
    var pow = 1
    colorMap.forEach {
        pow *=it.value
    }
   return pow
}

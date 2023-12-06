package Day_2

import Function.readText
/*
    Question

    As you walk, the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you play this game,
    he will hide a secret number of cubes of each color in the bag, and your goal is to figure out information about the number of cubes.

    To get information, once a bag has been loaded with cubes, the Elf will reach into the bag, grab a handful of random cubes,
    show them to you, and then put them back in the bag. He'll do this a few times per game.

    You play several games and record the information from each game (your puzzle input). Each game is listed with its ID number (like the 11 in Game 11: ...)
    followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).
    For example, the record of a few games might look like this:

    In game 1, three sets of cubes are revealed from the bag (and then put back again). The first set is 3 blue cubes and 4 red cubes;
    the second set is 1 red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green cubes.
    The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

    In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration.
    However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly,
    game 4 would also have been impossible because the Elf showed you 15 blue cubes at once. If you add up the IDs of the games that would have been possible, you get 8.

    Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes,
     and 14 blue cubes.
    What is the sum of the IDs of those games?

 */
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


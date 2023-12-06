package Function

import java.io.File
import java.io.InputStream

fun readText(path : String) : MutableList<String>{
    val inputStream: InputStream = File(path).inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    return lineList
}
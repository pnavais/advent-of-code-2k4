import java.io.File

typealias Matrix<T> = List<List<T>>

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt").readLines()

/**
 * Reads lines from the given input txt file and converts them to a list of chars.
 */
fun readMatrix(name:String): Matrix<Char> = File("src/main/resources", "$name.txt").readLines().map { it.toCharArray().toList() }
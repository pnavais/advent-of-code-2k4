package day02

import readInput
import kotlin.math.abs

fun isReportValid(levels: List<Int>) = with(levels[0] < levels[1]) {
    levels.windowed(2).indexOfFirst { (a, b) -> ! (abs(a - b) in (1..3) && (if (this) a < b else a > b)) }
}

fun countSafeReports(input: List<String>) =
    input.map { it.split(" ").map(String::toInt) }.count { levels -> (isReportValid(levels) == -1) }

fun countSafeReportsWithDampener(input: List<String>) =
    input.map { it.split(" ").map(String::toInt) }.count { levels ->
        val idx = isReportValid(levels)
        val indicesToRemove = listOf(0, idx, idx + 1)
        indicesToRemove.any { i ->
            i != -1 && isReportValid(levels.toMutableList().also { it.removeAt(i) }) == -1
        }
    }

fun main() {
    val input = readInput("day02")
    println(countSafeReports(input))
    println(countSafeReportsWithDampener(input))
}
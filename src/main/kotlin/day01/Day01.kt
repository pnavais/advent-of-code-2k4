package day01

import readInput
import java.math.BigInteger
import kotlin.math.abs

fun countDistances(input: List<String>) =
    input
        .map { line -> line.split("\\s+".toRegex()).map(String::toInt).let { it[0] to it[1] } }
        .unzip()
        .let { it.first.sorted() zip it.second.sorted() }
        .fold(0) { acc, d -> acc + abs(d.second - d.first) }

fun countSimilarity(input: List<String>): BigInteger {
    return input
        .map { line -> line.split("\\s+".toRegex()).map(String::toInt).let { it[0] to it[1] } }
        .unzip()
        .toList()
        .map {
            it.fold(mutableMapOf<Int, Int>()) { acc, i ->
                acc.compute(i) { _, v -> v?.plus(1) ?: 1 }
                acc
            }
        }. let {
            it[0].keys.fold(BigInteger.ZERO) { acc, i ->
                acc.plus(it[0][i]!!.toBigInteger().times(i.toBigInteger().times(it[1][i]?.toBigInteger() ?: BigInteger.ZERO)))
            }
        }
}

fun main() {
    val testInput = readInput("day01")
    println(countDistances(testInput))
    println(countSimilarity(testInput))
}
package day04

import Matrix
import readMatrix

val DIRECTIONS = listOf(
    -1 to -1, -1 to 0, -1 to 1,
     0 to -1,           0 to 1,
     1 to -1,  1 to 0,  1 to 1
)


fun solvePart1(input: Matrix<Char>) {
    println(countXMAS(input))
}

private fun <T> Matrix<T>.isInside(x: Int, y: Int): Boolean {
    return y in indices && x in this[y].indices
}

private fun findInDirection(input: Matrix<Char>, indexSequence: Sequence<Pair<Int, Int>>): Boolean {
    val idxList = indexSequence.take(3).toList()
    return (input.isInside(idxList[0].first, idxList[0].second) && input[idxList[0].second][idxList[0].first] == 'M' &&
        input.isInside(idxList[1].first, idxList[1].second) && input[idxList[1].second][idxList[1].first] == 'A' &&
        input.isInside(idxList[2].first, idxList[2].second) && input[idxList[2].second][idxList[2].first] == 'S'
    )
}

fun countXMAS(input: Matrix<Char>): Int {
    return input.flatMapIndexed { y, row ->
        row.mapIndexed { x, c ->
            if (c == 'X') {
                // Check horizontal direct
                val mojon = DIRECTIONS.count { d -> (findInDirection(input, generateSequence(x + d.second to y + d.first) { x + it.second to y + it.first })) }
                return mojon
            } else 0
        }
    }.sum()
}

fun main() {
    val input = readMatrix("day04_min")
    solvePart1(input)
}


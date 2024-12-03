package day03

import readInput

val ALL_EXPR = "(do\\(\\))|(don't\\(\\))|mul\\(([0-9]+),([0-9]+)\\)".toRegex()
val MUL_EXPR = "mul\\(([0-9]+),([0-9]+)\\)".toRegex()

fun cleanInput(input: List<String>) = input.flatMap { ALL_EXPR.findAll(it).map(MatchResult::value) }

fun eval(input: List<String>): Int {
    var enableCount = true
    return input.sumOf { token ->
        when
        {
            token == "do()" ->  0.also { enableCount = true }
            token == "don't()" -> 0.also { enableCount = false }
            enableCount -> MUL_EXPR.find(token)?.let { m -> m.groupValues[1].toInt().times(m.groupValues[2].toInt()) } ?: 0
            else -> 0
        }
    }
}

fun main() {
    val input = readInput("day03")
    println(eval(cleanInput(input)))
}
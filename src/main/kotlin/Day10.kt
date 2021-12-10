package cberg.aoc2021

class Day10(private val input: List<String>) {
    constructor() : this(Input("day10.txt").lines())

    fun part1() = input.sumOf { line ->
        errorScore(line)
    }

    private val openingChars = "([{<"
    private val closingChars = ")]}>"

    private fun errorScore(line: String): Int {
        val stack = mutableListOf<Char>()
        for (c in line) {
            when (c) {
                in openingChars -> stack += c
                in closingChars -> {
                    val o = stack.removeLast()
                    if (openingChars.indexOf(o) != closingChars.indexOf(c)) {
                        return when (c) {
                            ')' -> 3
                            ']' -> 57
                            '}' -> 1197
                            '>' -> 25137
                            else -> error("Unexpected char $c")
                        }
                    }
                }
            }
        }
        return 0
    }

    fun part2(): Long {
        val scores = input.map { line -> completionScore(line) }
            .filterNot { score -> score == 0L }
            .sorted()
        return scores[scores.size / 2]
    }

    private fun completionScore(line: String): Long {
        val stack = mutableListOf<Char>()
        for (c in line) {
            when (c) {
                in openingChars -> stack += c
                in closingChars -> {
                    val o = stack.removeLast()
                    if (openingChars.indexOf(o) != closingChars.indexOf(c)) {
                        return 0
                    }
                }
            }
        }
        var score = 0L
        for (c in stack.reversed()) {
            score = score * 5 + when (c) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else -> error("Unexpected opening char $c")
            }
        }
        return score
    }
}

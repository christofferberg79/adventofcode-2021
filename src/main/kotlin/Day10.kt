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

    fun part2() = 0
}

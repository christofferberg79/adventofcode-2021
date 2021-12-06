package cberg.aoc2021

class Day6(input: String) {
    constructor() : this(Input("day6.txt").oneLine())

    private val state = input.split(",").map { it.toInt() }

    fun part1(days: Int): Int {
        return generateSequence(state) { state ->
            state.flatMap { daysUntilNew ->
                when (daysUntilNew) {
                    0 -> listOf(6, 8)
                    else -> listOf(daysUntilNew - 1)
                }
            }
        }.elementAt(days).size
    }

    fun part2() = 0
}

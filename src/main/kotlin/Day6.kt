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

    fun part2(days: Int): Long {
        val initialCount: Map<Int, Long> = state
            .groupingBy { daysUntilNew -> daysUntilNew }
            .eachCount()
            .mapValues { numberOfFish -> numberOfFish.value.toLong() }
        return generateSequence(initialCount) { count ->
            val newCount = mutableMapOf<Int, Long>()

            for ((daysUntilNew, numberOfFish) in count) {
                when (daysUntilNew) {
                    0 -> {
                        newCount[8] = numberOfFish
                        newCount.merge(6, numberOfFish, Long::plus)
                    }
                    else -> newCount.merge(daysUntilNew - 1, numberOfFish, Long::plus)
                }
            }
            newCount
        }.elementAt(days).values.sum()
    }

}

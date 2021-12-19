package cberg.aoc2021

class Day6(input: String) {
    private val state = input.split(",").map { it.toInt() }

    fun part1(days: Int) = solve(days).toInt()

    fun part2(days: Int) = solve(days)

    private fun solve(days: Int) = generateSequence(initialCount()) { previousCount ->
        previousCount.mapKeys { (daysUntilNew, _) -> if (daysUntilNew == 0) 8 else daysUntilNew - 1 }
            .toMutableMap().apply { merge(6, previousCount[0] ?: 0, Long::plus) }
    }.elementAt(days).values.sum()

    private fun initialCount() = state
        .groupingBy { daysUntilNew -> daysUntilNew }
        .eachCount()
        .mapValues { (_, numberOfFish) -> numberOfFish.toLong() }

}

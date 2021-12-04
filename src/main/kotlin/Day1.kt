package cberg.aoc2021

class Day1(private val measurements: List<Int>) {
    constructor() : this(Input("day1.txt").intLines())

    fun part1() = countIncreases(measurements)

    fun part2(): Int {
        val windowValues = measurements.windowed(3, 1).map { it.sum() }
        return countIncreases(windowValues)
    }

    private fun countIncreases(measurements: List<Int>) =
        measurements.zipWithNext().count { (m1, m2) -> m2 > m1 }
}

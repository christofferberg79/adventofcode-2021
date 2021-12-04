package cberg.aoc2021

class Day1 {
    fun part1(measurements: List<Int>) = countIncreases(measurements)

    fun part2(measurements: List<Int>): Int {
        val windowValues = measurements.windowed(3, 1).map { it.sum() }
        return countIncreases(windowValues)
    }

    private fun countIncreases(measurements: List<Int>) =
        measurements.zipWithNext().count { (m1, m2) -> m2 > m1 }
}
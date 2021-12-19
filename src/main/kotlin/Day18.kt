package cberg.aoc2021

class Day18(private val input: List<String>) {
    fun part1() = input.map { SnailfishNumber(it) }.reduce { a, b -> a + b }.magnitude

    fun part2(): Int = input.maxOf { a ->
        input.maxOf { b -> (SnailfishNumber(a) + SnailfishNumber(b)).magnitude }
    }
}

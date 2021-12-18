package cberg.aoc2021

class Day18(private val input: List<String>) {
    constructor() : this(Input("day18.txt").lines())

    fun part1() = input.map { SnailfishNumber(it) }.reduce { a, b -> a + b }.magnitude

    fun part2() = 0
}

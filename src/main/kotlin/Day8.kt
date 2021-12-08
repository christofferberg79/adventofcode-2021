package cberg.aoc2021

class Day8(private val input: List<String>) {
    constructor() : this(Input("day8.txt").lines())

    fun part1() = input
        .flatMap { line -> line.split(Regex("[| ]+")).takeLast(4) }
        .count { value -> value.length in setOf(2, 4, 3, 7) }

    fun part2() = 0
}

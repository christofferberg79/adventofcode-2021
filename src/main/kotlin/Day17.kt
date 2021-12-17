package cberg.aoc2021

class Day17(private val input: String) {
    constructor() : this(Input("day17.txt").oneLine())

    fun part1(): Int {
        val y = input.substringAfter("y=").substringBefore("..").toInt()
        return y * (y + 1) / 2
    }

    fun part2() = 0
}

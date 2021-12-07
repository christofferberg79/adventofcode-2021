package cberg.aoc2021

import kotlin.math.absoluteValue

class Day7(private val input: String) {
    constructor() : this(Input("day7.txt").oneLine())

    fun part1() = input.split(",").map { it.toInt() }.sorted().let { data ->
        val pos = data[data.size / 2]
        data.sumOf { (it - pos).absoluteValue }
    }

    fun part2() = 0
}

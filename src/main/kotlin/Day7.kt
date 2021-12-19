package cberg.aoc2021

import kotlin.math.absoluteValue

class Day7(input: String) {
    private val data = input.split(",").map { it.toInt() }.sorted()

    fun part1(): Int {
        val pos = data[data.size / 2]
        return data.sumOf { (it - pos).absoluteValue }
    }

    fun part2() = (data.first()..data.last()).minOf { p ->
        data.sumOf {
            val d = (it - p).absoluteValue
            (d * (d + 1) / 2)
        }
    }
}

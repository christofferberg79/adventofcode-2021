package cberg.aoc2021

class Day3(private val report: List<String>) {
    constructor() : this(Input("day3.txt").lines())

    fun part1(): Int {
        val count = report.first().indices.map { i ->
            report.map { line -> line[i] }.groupingBy { char -> char }.eachCount()
        }

        val gamma = count.map { it.maxByOrNull { (_, count) -> count }?.key ?: error("invalid input") }
            .joinToString(separator = "").toInt(2)

        val epsilon = count.map { it.minByOrNull { (_, count) -> count }?.key ?: error("invalid input") }
            .joinToString(separator = "").toInt(2)

        return gamma * epsilon
    }
}

package cberg.aoc2021

class Day3(private val report: List<String>) {
    constructor() : this(Input("day3.txt").lines())

    fun part1(): Int {
        val indices = report.minByOrNull { it.length }?.indices ?: error("invalid input")
        val gamma = indices.map { index ->
            report.map { line -> line[index] }
                .groupingBy { it }
                .eachCount()
                .maxByOrNull { it.value }
                ?.key ?: error("invalid input")
        }
            .joinToString(separator = "")
            .toInt(2)

        val epsilon = indices.map { index ->
            report.map { line -> line[index] }
                .groupingBy { it }
                .eachCount()
                .minByOrNull { it.value }
                ?.key ?: error("invalid input")
        }
            .joinToString(separator = "")
            .toInt(2)

        return gamma * epsilon
    }
}

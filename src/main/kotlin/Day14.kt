package cberg.aoc2021

class Day14(input: List<String>) {
    constructor() : this(Input("day14.txt").lines())

    private val template = input.first()
    private val rules = input.drop(2).map { it.split(" -> ") }
        .associate { (pair, insert) -> pair to insert.single() }

    fun part1(): Int {
        var s = template.map { it }
        repeat(10) {
            val insert = s.zipWithNext { a, b -> "$a$b" }.map { rules[it] ?: error("No rule found for $it") }
            val zip = s.zip(insert) { a, b -> listOf(a, b) }
            s = zip.flatten() + s.last()
        }
        val f = s.groupingBy { it }.eachCount()
        return f.values.maxOrNull()!! - f.values.minOrNull()!!
    }

    fun part2() = 0
}

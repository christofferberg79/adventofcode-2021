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

    fun part2(): Long {
        var pairCount = template.zipWithNext { a, b -> "$a$b" }
            .groupingBy { it }.eachCount()
            .mapValues { (_, count) -> count.toLong() }

        repeat(40) {
            val newCount = mutableMapOf<String, Long>()
            for ((pair, count) in pairCount) {
                val insert = rules[pair] ?: error("No rule found for $it")
                newCount.merge("${pair[0]}$insert", count, Long::plus)
                newCount.merge("$insert${pair[1]}", count, Long::plus)
                pairCount = newCount
            }
        }

        val charCount = mutableMapOf<Char, Long>()
        for ((pair, count) in pairCount) {
            charCount.merge(pair[0], count, Long::plus)
        }
        charCount.merge(template.last(), 1, Long::plus)
        return charCount.values.maxOrNull()!! - charCount.values.minOrNull()!!
    }

}

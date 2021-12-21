package cberg.aoc2021

class Day21(private val input: List<String>) {
    fun part1(): Int {
        val positions = input.map { it.substringAfter(": ").toInt() }.toIntArray()
        val scores = IntArray(positions.size)
        var player = positions.lastIndex
        var rolls = 0

        while (scores[player] < 1000) {
            player = (player + 1) % positions.size
            val moves = generateSequence { rolls++ % 100 + 1 }.take(3).sum()
            positions[player] = (positions[player] + moves - 1) % 10 + 1
            scores[player] += positions[player]
        }
        return scores.minOrNull()!! * rolls
    }

    fun part2() = 0
}

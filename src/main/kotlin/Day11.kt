package cberg.aoc2021

class Day11(input: List<String>) {
    constructor() : this(Input("day11.txt").lines())

    private val data = input.map { line -> line.map { it.digitToInt() }.toMutableList() }

    fun part1() = (1..100).sumOf { step() }

    fun part2() = generateSequence { step() }.takeWhile { it < 100 }.count() + 1

    private fun step(): Int {
        var flashes = 0
        val toIncrease = mutableListOf<P>()

        for (x in 0..9) for (y in 0..9) {
            toIncrease += P(x, y)
        }
        while (toIncrease.isNotEmpty()) {
            val p = toIncrease.removeFirst()
            if (++data[p.x][p.y] == 10) {
                flashes++
                toIncrease += p.adjacent
            }
        }
        for (x in 0..9) for (y in 0..9) {
            if (data[x][y] > 9) {
                data[x][y] = 0
            }
        }
        return flashes
    }

    private data class P(val x: Int, val y: Int)

    private val P.adjacent: List<P>
        get() = listOf(
            P(x - 1, y - 1), P(x, y - 1), P(x + 1, y - 1),
            P(x - 1, y), P(x + 1, y),
            P(x - 1, y + 1), P(x, y + 1), P(x + 1, y + 1)
        ).filter { p -> p.x in 0..9 && p.y in 0..9 }
}

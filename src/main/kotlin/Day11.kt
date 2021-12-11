package cberg.aoc2021

class Day11(input: List<String>) {
    constructor() : this(Input("day11.txt").lines())

    private val data = input.map { line -> line.map { it.digitToInt() }.toMutableList() }

    fun part1(): Int {
        var flashes = 0
        repeat(100) {
            val toFlash = mutableListOf<P>()
            for (x in 0..9) for (y in 0..9) {
                data[x][y]++
                if (data[x][y] == 10) {
                    toFlash += P(x, y)
                }
            }
            while (toFlash.isNotEmpty()) {
                flashes++
                val p0 = toFlash.removeFirst()
                for (p in p0.adjacent) {
                    data[p.x][p.y]++
                    if (data[p.x][p.y] == 10) {
                        toFlash.add(p)
                    }
                }
            }
            for (x in 0..9) for (y in 0..9) {
                if (data[x][y] > 9) {
                    data[x][y] = 0
                }
            }
        }
        return flashes
    }

    fun part2() = 0
}

private data class P(val x: Int, val y: Int)

private val P.adjacent: List<P>
    get() = listOf(
        P(x - 1, y - 1), P(x, y - 1), P(x + 1, y - 1),
        P(x - 1, y), P(x + 1, y),
        P(x - 1, y + 1), P(x, y + 1), P(x + 1, y + 1)
    ).filter { p -> p.x in 0..9 && p.y in 0..9 }
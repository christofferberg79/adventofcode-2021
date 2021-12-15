package cberg.aoc2021

class Day15(private val input: List<String>) {
    constructor() : this(Input("day15.txt").lines())

    fun part1(): Int {
        val risks = input.map { line -> line.map { value -> value.digitToInt() } }
        val pathRisks = input.map { line -> line.map { Int.MAX_VALUE }.toMutableList() }
        pathRisks[0][0] = 0
        val todo = mutableListOf(P(0, 0))
        while (todo.isNotEmpty()) {
            val p = todo.removeFirst()
            for (o in p.adjacent()) {
                if (o.x in risks.indices && o.y in risks[o.x].indices) {
                    val newRisk = pathRisks[p.x][p.y] + risks[o.x][o.y]
                    if (newRisk < pathRisks[o.x][o.y]) {
                        pathRisks[o.x][o.y] = newRisk
                        todo += o
                    }
                }
            }
        }
        return pathRisks.last().last()
    }

    private data class P(val x: Int, val y: Int)

    private fun P.adjacent() = listOf(
        P(x, y - 1),
        P(x, y + 1),
        P(x - 1, y),
        P(x + 1, y)
    )

    fun part2() = 0
}

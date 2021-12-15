package cberg.aoc2021

class Day15(private val input: List<String>) {
    constructor() : this(Input("day15.txt").lines())

    fun part1(): Int {
        val risk: (P) -> Int? = { p ->
            input.getOrNull(p.x)?.getOrNull(p.y)?.digitToInt()
        }

        val pathRisks = findPathRisks(risk)
        return pathRisks[P(input.lastIndex, input.last().lastIndex)]!!
    }

    fun part2(): Int {
        val xSize = input.size
        val ySize = input.first().length
        val max = P(xSize * 5 - 1, ySize * 5 - 1)

        val risk: (P) -> Int? = { p: P ->
            if (p.x < 0 || p.x > max.x || p.y < 0 || p.y > max.y) {
                null
            } else {
                val baseRisk = input[p.x % xSize][p.y % ySize].digitToInt()
                val additionalRisk = p.x / xSize + p.y / ySize
                (baseRisk + additionalRisk - 1) % 9 + 1
            }
        }

        val pathRisks = findPathRisks(risk)

        return pathRisks[max]!!
    }

    private data class P(val x: Int, val y: Int)

    private fun P.adjacent() = listOf(P(x, y - 1), P(x, y + 1), P(x - 1, y), P(x + 1, y))

    private data class Node(val p: P, val risk: Int)

    private fun findPathRisks(risk: (P) -> Int?): Map<P, Int> {
        val paths = mutableMapOf(P(0, 0) to 0).withDefault { Int.MAX_VALUE }
        val todo = sortedSetOf(compareBy({ it.risk }, { it.p.x }, { it.p.y }), Node(P(0, 0), 0))
        while (todo.isNotEmpty()) {
            val node = todo.pollFirst() ?: error("todo should not be empty")
            for (o in node.p.adjacent()) {
                risk(o)?.let { riskAtO ->
                    val oldRisk = paths.getValue(o)
                    val newRisk = paths.getValue(node.p) + riskAtO
                    if (newRisk < oldRisk) {
                        paths[o] = newRisk
                        todo += Node(o, newRisk)
                    }
                }
            }
        }
        return paths
    }
}

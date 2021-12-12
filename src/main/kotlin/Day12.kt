package cberg.aoc2021

class Day12(private val input: List<String>) {
    constructor() : this(Input("day12.txt").lines())

    fun part1(): Int {
        val map = input.flatMap { it.split("-").let { (from, to) -> listOf(from to to, to to from) } }
            .groupBy(keySelector = { (from, _) -> from }, valueTransform = { (_, to) -> to })
        return findPaths(map).size
    }

    fun part2() = 0

}

private fun findPaths(map: Map<String, List<String>>): List<List<String>> {
    val completePaths = mutableListOf<List<String>>()
    val incompletePaths = mutableListOf(listOf("start"))

    while (incompletePaths.isNotEmpty()) {
        val path = incompletePaths.removeFirst()
        val nexts = map[path.last()] ?: error("There's no way out of cave ${path.last()}. How did we get in here?")
        for (next in nexts) {
            if (next.first().isUpperCase() || next !in path) {
                val newPath = path + next
                if (next == "end") {
                    completePaths += newPath
                } else {
                    incompletePaths += newPath
                }
            }
        }
    }

    return completePaths
}

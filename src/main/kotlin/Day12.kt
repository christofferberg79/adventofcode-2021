package cberg.aoc2021

class Day12(input: List<String>) {
    private val map = input.flatMap { it.split("-").let { (from, to) -> listOf(from to to, to to from) } }
        .filter { (_, to) -> to != "start" }
        .groupBy(keySelector = { (from, _) -> from }, valueTransform = { (_, to) -> to })

    fun part1(): Int {
        return findPaths(map).size
    }

    fun part2(): Int {
        return findPaths(map, true).size
    }

    private fun findPaths(map: Map<String, List<String>>, pass: Boolean = false): List<List<String>> {
        val completePaths = mutableListOf<List<String>>()
        val incompletePaths = mutableListOf(listOf("start") to pass)

        while (incompletePaths.isNotEmpty()) {
            val (path, hasSmallCavePass) = incompletePaths.removeFirst()
            val nexts = map[path.last()] ?: error("There's no way out of cave ${path.last()}. How did we get in here?")
            for (next in nexts) {
                val needsSmallCavePass = next.first().isLowerCase() && next in path
                if (hasSmallCavePass || !needsSmallCavePass) {
                    val newPath = path + next
                    if (next == "end") {
                        completePaths += newPath
                    } else {
                        incompletePaths += newPath to (hasSmallCavePass && !needsSmallCavePass)
                    }
                }
            }
        }

        return completePaths
    }
}

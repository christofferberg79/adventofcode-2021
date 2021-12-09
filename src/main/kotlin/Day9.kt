package cberg.aoc2021

class Day9(private val input: List<String>) {
    constructor() : this(Input("day9.txt").lines())

    fun part1() = getLowPoints().sumOf { (row, col) -> 1 + getHeight(row, col) }

    private fun getLowPoints() = input.indices.flatMap { row ->
        input.first().indices
            .filter { col -> isLowPoint(row, col) }
            .map { col -> row to col }
    }

    private fun isLowPoint(row: Int, col: Int): Boolean {
        return getAdjacentLocations(row, col).all { location ->
            getHeight(location.first, location.second) > getHeight(row, col)
        }
    }

    private fun getAdjacentLocations(row: Int, col: Int) = buildList {
        if (row > 0) add(row - 1 to col)
        if (row < input.lastIndex) add(row + 1 to col)
        if (col > 0) add(row to col - 1)
        if (col < input.first().lastIndex) add(row to col + 1)
    }

    private fun getHeight(row: Int, col: Int) = input[row][col].digitToInt()

    fun part2() = getLowPoints().asSequence()
        .map { (row, col) -> getBasin(row, col) }
        .map { basin -> basin.size }
        .sortedDescending()
        .take(3)
        .reduce(Int::times)

    private fun getBasin(row: Int, col: Int): Set<Pair<Int, Int>> {
        val basin = mutableSetOf(row to col)
        val locationsToProcess = mutableListOf(row to col)
        while (locationsToProcess.isNotEmpty()) {
            val location = locationsToProcess.removeFirst()
            val adjacentLocations = getAdjacentLocations(location.first, location.second)
                .filter { it !in basin }
                .filter { getHeight(it.first, it.second) < 9 }
                .filter { getHeight(it.first, it.second) >= getHeight(location.first, location.second) }
            basin.addAll(adjacentLocations)
            locationsToProcess.addAll(adjacentLocations)
        }
        return basin
    }
}

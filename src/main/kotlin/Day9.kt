package cberg.aoc2021

class Day9(input: List<String>) {
    constructor() : this(Input("day9.txt").lines())

    private val heightmap = input.map { s -> s.map { c -> c.digitToInt() } }
    private val maxRow = heightmap.lastIndex
    private val maxCol = heightmap.first().lastIndex

    fun part1() = getLowPoints().sumOf { 1 + it.getHeight() }

    private fun getLowPoints() = getLocations().filter { it.isLowPoint() }

    private fun getLocations() = (0..maxRow).flatMap { row ->
        (0..maxCol).map { col -> Location(row, col) }
    }

    private fun Location.isLowPoint() = getAdjacentLocations().all { adjacent ->
        this.getHeight() < adjacent.getHeight()
    }

    private fun Location.getAdjacentLocations() = buildList {
        if (row > 0) add(Location(row - 1, col))
        if (row < maxRow) add(Location(row + 1, col))
        if (col > 0) add(Location(row, col - 1))
        if (col < maxCol) add(Location(row, col + 1))
    }

    private fun Location.getHeight() = heightmap[row][col]

    fun part2() = getLowPoints().asSequence()
        .map { location -> getBasin(location).size }
        .sortedDescending()
        .take(3)
        .reduce { acc, size -> acc * size }

    private fun getBasin(lowPoint: Location): Set<Location> {
        val basin = mutableSetOf(lowPoint)
        val locationsToProcess = mutableListOf(lowPoint)
        while (locationsToProcess.isNotEmpty()) {
            locationsToProcess.removeFirst()
                .getAdjacentLocations()
                .filter { adjacent -> adjacent !in basin && adjacent.getHeight() < 9 }
                .forEach { adjacent ->
                    basin.add(adjacent)
                    locationsToProcess.add(adjacent)
                }
        }
        return basin
    }

    private data class Location(val row: Int, val col: Int)
}

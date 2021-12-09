package cberg.aoc2021

class Day9(private val input: List<String>) {
    constructor() : this(Input("day9.txt").lines())

    fun part1() = input.indices.flatMap { row ->
        input.first().indices.filter { col ->
            val adjacents = buildList {
                if (row > 0) add(row - 1 to col)
                if (row < input.lastIndex) add(row + 1 to col)
                if (col > 0) add(row to col - 1)
                if (col < input.first().lastIndex) add(row to col + 1)
            }
            adjacents.all { input[it.first][it.second] > input[row][col] }
        }.map { col ->
            1 + input[row][col].digitToInt()
        }
    }.sum()

    fun part2() = 0
}

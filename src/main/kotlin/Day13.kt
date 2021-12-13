package cberg.aoc2021

import kotlin.math.abs

class Day13(private val input: List<String>) {
    constructor() : this(Input("day13.txt").lines())

    fun part1(): Int {
        val (positions, foldLines) = parse(input)
        return positions.map(foldLines.first().transform).toSet().size
    }

    fun part2(): Int {
        var (positions, foldLines) = parse(input)
        for (line in foldLines) {
            positions = positions.map(line.transform).toSet()
        }
        printData(positions)
        return positions.size
    }

    private fun parse(input: List<String>): Pair<Set<Position>, List<Line>> {
        val positions = input.filter { it.contains(',') }
            .map { it.split(",") }
            .map { (x, y) -> Position(x.toInt(), y.toInt()) }
            .toSet()

        val foldLines = input.filter { it.startsWith("fold") }
            .map { it.substringAfter("fold along ") }
            .map { it.split("=") }
            .map { (coord, value) -> Line(coord.single(), value.toInt()) }
        return Pair(positions, foldLines)
    }

    data class Position(val x: Int, val y: Int)
    data class Line(val coord: Char, val value: Int) {
        val transform: (Position) -> Position = when (coord) {
            'x' -> { p -> Position(value - abs(p.x - value), p.y) }
            'y' -> { p -> Position(p.x, value - abs(p.y - value)) }
            else -> error("Invalid line: $this")
        }
    }

    private fun printData(positions: Set<Position>) {
        val minX = positions.minOf { it.x }
        val minY = positions.minOf { it.y }
        val maxX = positions.maxOf { it.x }
        val maxY = positions.maxOf { it.y }
        println()
        (minY..maxY).map { y ->
            (minX..maxX).joinToString("") { x -> if (Position(x, y) in positions) "#" else " " }
                .let(::println)
        }
    }
}

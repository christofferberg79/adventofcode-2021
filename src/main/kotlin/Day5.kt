package cberg.aoc2021

import kotlin.math.sign

class Day5(input: List<String>) {
    constructor() : this(Input("day5.txt").lines())

    private val lines = input.map { s -> Line(s) }

    fun part1() = lines
        .filter { line -> line.isHorizontal() || line.isVertical() }
        .countOverlaps()

    fun part2() = lines.countOverlaps()

    private fun List<Line>.countOverlaps() = flatMap { line -> line.points }
        .groupingBy { point -> point }.eachCount()
        .count { (_, count) -> count >= 2 }
}

private data class Point(val x: Int, val y: Int)
private data class Line(val p1: Point, val p2: Point)

private fun Line.isHorizontal() = p1.y == p2.y
private fun Line.isVertical() = p1.x == p2.x

private val Line.points
    get() = generateSequence(p1) { p -> if (p == p2) null else p + direction }

private val Line.direction
    get() = (p2 - p1).sign

private operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)
private operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)
private val Point.sign get() = Point(x.sign, y.sign)

private fun Line(s: String): Line {
    val (p1, p2) = s.split(" -> ").map { Point(it) }
    return Line(p1, p2)
}

private fun Point(s: String): Point {
    val (x, y) = s.split(",").map { it.toInt() }
    return Point(x, y)
}

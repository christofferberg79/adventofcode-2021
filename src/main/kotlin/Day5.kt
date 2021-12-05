package cberg.aoc2021

import kotlin.math.max
import kotlin.math.min

class Day5(input: List<String>) {
    constructor() : this(Input("day5.txt").lines())

    private val lines = input.map { s -> Line(s) }

    fun part1() = lines
        .filter { line -> line.isHorizontal() || line.isVertical() }
        .flatMap { line -> line.points }
        .groupingBy { point -> point }.eachCount()
        .count { (_, count) -> count >= 2 }

    fun part2() = lines
        .flatMap { line -> line.points }
        .groupingBy { point -> point }.eachCount()
        .count { (_, count) -> count >= 2 }
}

private data class Point(val x: Int, val y: Int)
private data class Line(val p1: Point, val p2: Point)

private fun Line.isHorizontal() = p1.y == p2.y
private fun Line.isVertical() = p1.x == p2.x

private val Line.points: List<Point>
    get() = when {
        isHorizontal() -> (min(p1.x, p2.x)..max(p1.x, p2.x)).map { x -> Point(x, p1.y) }
        isVertical() -> (min(p1.y, p2.y)..max(p1.y, p2.y)).map { y -> Point(p1.x, y) }
        else -> {
            val dp = Point(
                x = (p2.x - p1.x).coerceIn(-1..1),
                y = (p2.y - p1.y).coerceIn(-1..1)
            )
            generateSequence(p1) { p ->
                if (p == p2) null
                else Point(p.x + dp.x, p.y + dp.y)
            }.toList()
        }
    }

private fun Line(s: String): Line {
    val (p1, p2) = s.split(" -> ").map { Point(it) }
    return Line(p1, p2)
}

private fun Point(s: String): Point {
    val (x, y) = s.split(",").map { it.toInt() }
    return Point(x, y)
}

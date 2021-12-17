package cberg.aoc2021

class Day17(input: String) {
    constructor() : this(Input("day17.txt").oneLine())

    private val xRange: IntRange
    private val yRange: IntRange

    init {
        val inputPattern = Regex("target area: x=(.*)\\.\\.(.*), y=(.*)\\.\\.(.*)")
        val (xMin, xMax, yMin, yMax) = inputPattern.matchEntire(input)
            ?.destructured
            ?: error("Invalid input: $input")
        xRange = xMin.toInt()..xMax.toInt()
        yRange = yMin.toInt()..yMax.toInt()
    }

    fun part1() = yRange.first * (yRange.first + 1) / 2

    fun part2(): Int {
        val ys = getYs().groupBy({ it.first }, { it.second })
        val xs = getXs(ys.keys)
        return xs.flatMap { (step, x) -> ys[step]!!.map { y -> x to y } }.distinct().size
    }

    private fun getYs(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (vy0 in yRange.first..-yRange.first) {
            var step = 0
            var y = 0
            var vy = vy0
            while (y > yRange.first) {
                y += vy
                vy--
                step++
                if (y in yRange) {
                    result += step to vy0
                }
            }
        }
        return result
    }

    private fun getXs(steps: Set<Int>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (vx0 in 0..xRange.last) {
            for (step in steps) {
                var x = 0
                var vx = vx0
                repeat(step) {
                    x += vx
                    if (vx > 0) {
                        vx--
                    }
                }
                if (x in xRange) {
                    result += step to vx0
                }
            }
        }
        return result
    }
}

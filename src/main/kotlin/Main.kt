package cberg.aoc2021

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

fun main() {
    println("Puzzle           Result               Time")
    println("------------------------------------------")
    run(1, 1) { Day1().part1() }
    run(1, 2) { Day1().part2() }
    run(2, 1) { Day2().part1() }
    run(2, 2) { Day2().part2() }
    run(3, 1) { Day3().part1() }
    run(3, 2) { Day3().part2() }
    run(4, 1) { Day4().part1() }
    run(4, 2) { Day4().part2() }
    run(5, 1) { Day5().part1() }
    run(5, 2) { Day5().part2() }
    run(6, 1) { Day6().part1(80) }
    run(6, 2) { Day6().part2(256) }
    run(7, 1) { Day7().part1() }
    run(7, 2) { Day7().part2() }
    run(8, 1) { Day8().part1() }
    run(8, 2) { Day8().part2() }
    run(9, 1) { Day9().part1() }
    run(9, 2) { Day9().part2() }
}

@OptIn(ExperimentalTime::class)
private fun run(day: Int, part: Int, block: () -> Any) {
    val result = measureTimedValue(block)
    println("Day %s, part %s    %-15s %6s ms".format(day, part, result.value, result.duration.inWholeMilliseconds))
}

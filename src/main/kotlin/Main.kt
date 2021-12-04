package cberg.aoc2021

import cberg.aoc2021.Input.readIntLines
import cberg.aoc2021.Input.readLines
import java.io.File

fun main() {
    val day1Input = readIntLines("day1.txt")
    println("Day 1, part 1: ${Day1().part1(day1Input)}")
    println("Day 1, part 2: ${Day1().part2(day1Input)}")

    val day2Input = readLines("day2.txt")
    println("Day 2, part 1: ${Day2().part1(day2Input)}")
}

object Input {
    fun readLines(filename: String): List<String> {
        return File("input/$filename").readLines()
    }

    fun readIntLines(filename: String): List<Int> {
        return readLines(filename).map { it.toInt() }
    }
}

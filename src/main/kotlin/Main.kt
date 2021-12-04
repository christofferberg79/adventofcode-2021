package cberg.aoc2021

import java.io.File

fun main() {
    val day1Input = readIntLines("day1.txt")
    println("Day 1, part 1: ${Day1().part1(day1Input)}")
    println("Day 1, part 2: ${Day1().part2(day1Input)}")
}

fun readIntLines(filename: String): List<Int> {
    return File("input/$filename").readLines().map { it.toInt() }
}

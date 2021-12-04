package cberg.aoc2021

import java.io.File

fun main() {
    val result = Day1().part1(readIntLines("day1.txt"))
    println("Day 1, part 1: $result")
}

fun readIntLines(filename: String): List<Int> {
    return File("input/$filename").readLines().map { it.toInt() }
}

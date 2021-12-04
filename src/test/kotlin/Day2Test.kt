package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    private val exampleInput = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )
    private val realInput = Input.readLines("day2.txt")

    @Test
    fun part1_Example() {
        val result = Day2().part1(exampleInput)
        assertEquals(150, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day2().part1(realInput)
        assertEquals(1604850, result)
    }
}
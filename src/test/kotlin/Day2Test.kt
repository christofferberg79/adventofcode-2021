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

    @Test
    fun part1_Example() {
        val result = Day2(exampleInput).part1()
        assertEquals(150, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day2().part1()
        assertEquals(1604850, result)
    }

    @Test
    fun part2_Example() {
        val result = Day2(exampleInput).part2()
        assertEquals(900, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day2().part2()
        assertEquals(1685186100, result)
    }
}
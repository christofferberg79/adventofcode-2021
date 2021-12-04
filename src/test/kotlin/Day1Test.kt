package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    private val exampleInput = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    private val realInput = Input.readIntLines("day1.txt")

    @Test
    fun part1_Example() {
        val result = Day1().part1(exampleInput)
        assertEquals(7, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day1().part1(realInput)
        assertEquals(1602, result)
    }

    @Test
    fun part2_Example() {
        val result = Day1().part2(exampleInput)
        assertEquals(5, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day1().part2(realInput)
        assertEquals(1633, result)
    }
}
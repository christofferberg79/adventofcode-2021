package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    private val exampleInput = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun part1_Example() {
        val result = Day1(exampleInput).part1()
        assertEquals(7, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day1().part1()
        assertEquals(1602, result)
    }

    @Test
    fun part2_Example() {
        val result = Day1(exampleInput).part2()
        assertEquals(5, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day1().part2()
        assertEquals(1633, result)
    }
}
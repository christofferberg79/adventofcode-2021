package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class DayXTest {
    private val exampleInput = listOf(
        ""
    )

    @Test
    fun part1_Example() {
        val result = DayX(exampleInput).part1()
        assertEquals(0, result)
    }

    @Test
    fun part1_RealInput() {
        val result = DayX().part1()
        assertEquals(0, result)
    }

    @Test
    fun part2_Example() {
        val result = DayX(exampleInput).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = DayX().part2()
        assertEquals(0, result)
    }
}
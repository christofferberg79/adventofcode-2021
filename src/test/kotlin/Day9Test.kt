package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    private val exampleInput = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    @Test
    fun part1_Example() {
        val result = Day9(exampleInput).part1()
        assertEquals(15, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day9().part1()
        assertEquals(504, result)
    }

    @Test
    fun part2_Example() {
        val result = Day9(exampleInput).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day9().part2()
        assertEquals(0, result)
    }
}
package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {
    private val exampleInput = Input("day20_example.txt").lines()

    private val realInput = Input("day20.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day20(exampleInput).part1()
        assertEquals(35, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day20(realInput).part1()
        assertEquals(5268, result)
    }

    @Test
    fun part2_Example() {
        val result = Day20(exampleInput).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day20(realInput).part2()
        assertEquals(0, result)
    }
}
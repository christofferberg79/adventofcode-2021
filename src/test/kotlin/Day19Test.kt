package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {
    private val exampleInput = Input("day19_example.txt").lines()

    private val realInput = Input("day19.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day19(exampleInput).part1()
        assertEquals(79, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day19(realInput).part1()
        assertEquals(303, result)
    }

    @Test
    fun part2_Example() {
        val result = Day19(exampleInput).part2()
        assertEquals(3621, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day19(realInput).part2()
        assertEquals(9621, result)
    }
}
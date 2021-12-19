package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    private val exampleInput = "16,1,2,0,4,2,7,1,2,14"

    private val realInput = Input("day7.txt").oneLine()

    @Test
    fun part1_Example() {
        val result = Day7(exampleInput).part1()
        assertEquals(37, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day7(realInput).part1()
        assertEquals(343468, result)
    }

    @Test
    fun part2_Example() {
        val result = Day7(exampleInput).part2()
        assertEquals(168, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day7(realInput).part2()
        assertEquals(96086265, result)
    }
}
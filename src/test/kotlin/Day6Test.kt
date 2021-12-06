package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    private val exampleInput = "3,4,3,1,2"

    @Test
    fun part1_Example() {
        val result18 = Day6(exampleInput).part1(18)
        assertEquals(26, result18)

        val result80 = Day6(exampleInput).part1(80)
        assertEquals(5934, result80)
    }

    @Test
    fun part1_RealInput() {
        val result = Day6().part1(80)
        assertEquals(356190, result)
    }

    @Test
    fun part2_Example() {
        val result18 = Day6(exampleInput).part2(18)
        assertEquals(26, result18)

        val result80 = Day6(exampleInput).part2(80)
        assertEquals(5934, result80)

        val result256 = Day6(exampleInput).part2(256)
        assertEquals(26984457539, result256)
    }

    @Test
    fun part2_RealInput() {
        val result = Day6().part2(256)
        assertEquals(1617359101538, result)
    }
}
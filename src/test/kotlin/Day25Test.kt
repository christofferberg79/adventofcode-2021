package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {
    private val exampleInput = listOf(
        "v...>>.vv>",
        ".vv>>.vv..",
        ">>.>v>...v",
        ">>v>>.>.v.",
        "v>v.vv.v..",
        ">.>>..v...",
        ".vv..>.>v.",
        "v.v..>>v.v",
        "....v..v.>"
    )

    private val realInput = Input("day25.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day25(exampleInput).part1()
        assertEquals(58, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day25(realInput).part1()
        assertEquals(337, result)
    }

    @Test
    fun part2_Example() {
        val result = Day25(exampleInput).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day25(realInput).part2()
        assertEquals(0, result)
    }
}
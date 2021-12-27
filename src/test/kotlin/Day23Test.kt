package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {
    private val exampleInput = listOf(
        "#############",
        "#...........#",
        "###B#C#B#D###",
        "  #A#D#C#A#",
        "  #########"
    )

    private val realInput = Input("day23.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day23(exampleInput).part1()
        assertEquals(12521, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day23(realInput).part1()
        assertEquals(15338, result)
    }

    @Test
    fun part2_Example() {
        val result = Day23(exampleInput).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day23(realInput).part2()
        assertEquals(0, result)
    }
}
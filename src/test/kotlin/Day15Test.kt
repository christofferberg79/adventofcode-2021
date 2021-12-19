package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {
    private val exampleInput = listOf(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    )

    private val realInput = Input("day15.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day15(exampleInput).part1()
        assertEquals(40, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day15(realInput).part1()
        assertEquals(553, result)
    }

    @Test
    fun part2_Example() {
        val result = Day15(exampleInput).part2()
        assertEquals(315, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day15(realInput).part2()
        assertEquals(2858, result)
    }
}
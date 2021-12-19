package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    private val exampleInput = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    private val realInput = Input("day11.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day11(exampleInput).part1()
        assertEquals(1656, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day11(realInput).part1()
        assertEquals(1617, result)
    }

    @Test
    fun part2_Example() {
        val result = Day11(exampleInput).part2()
        assertEquals(195, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day11(realInput).part2()
        assertEquals(258, result)
    }
}
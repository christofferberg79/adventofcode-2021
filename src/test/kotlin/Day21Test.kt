package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    private val exampleInput = listOf(
        "Player 1 starting position: 4",
        "Player 2 starting position: 8"
    )

    private val realInput = Input("day21.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day21(exampleInput).part1()
        assertEquals(739785, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day21(realInput).part1()
        assertEquals(929625, result)
    }

    @Test
    fun part2_Example() {
        val result = Day21(exampleInput).part2()
        assertEquals(444356092776315, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day21(realInput).part2()
        assertEquals(175731756652760, result)
    }
}
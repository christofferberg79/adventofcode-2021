package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {
    private val exampleInput = "target area: x=20..30, y=-10..-5"

    private val realInput = Input("day17.txt").oneLine()

    @Test
    fun part1_Example() {
        val result = Day17(exampleInput).part1()
        assertEquals(45, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day17(realInput).part1()
        assertEquals(5671, result)
    }

    @Test
    fun part2_Example() {
        val result = Day17(exampleInput).part2()
        assertEquals(112, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day17(realInput).part2()
        assertEquals(4556, result)
    }
}
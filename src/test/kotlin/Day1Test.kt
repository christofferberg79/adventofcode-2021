package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun part1_Example1() {
        val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        val result = Day1().part1(input)
        assertEquals(7, result)
    }

    @Test
    fun part1_RealInput() {
        val input = readIntLines("day1.txt")
        val result = Day1().part1(input)
        assertEquals(1602, result)
    }

    @Test
    fun part2_Example1() {
        val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        val result = Day1().part2(input)
        assertEquals(5, result)
    }

    @Test
    fun part2_RealInput() {
        val input = readIntLines("day1.txt")
        val result = Day1().part2(input)
        assertEquals(1633, result)
    }
}
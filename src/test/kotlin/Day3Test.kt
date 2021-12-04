package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    private val exampleInput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun part1_Example() {
        val result = Day3(exampleInput).part1()
        assertEquals(198, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day3().part1()
        assertEquals(3242606, result)
    }

//    @Test
//    fun part2_Example() {
//        val result = Day3(exampleInput).part2()
//        assertEquals(5, result)
//    }
//
//    @Test
//    fun part2_RealInput() {
//        val result = Day3().part2()
//        assertEquals(1633, result)
//    }
}
package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {
    private val exampleInput = listOf(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0",
        "",
        "fold along y=7",
        "fold along x=5"
    )

    @Test
    fun part1_Example() {
        val result = Day13(exampleInput).part1()
        assertEquals(17, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day13().part1()
        assertEquals(610, result)
    }

    @Test
    fun part2_Example() {
        val result = Day13(exampleInput).part2()
        assertEquals(16, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day13().part2()
        assertEquals(95, result)
    }
}
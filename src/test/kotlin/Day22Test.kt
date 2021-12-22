package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {
    private val exampleInput1 = Input("day22_example1.txt").lines()
    private val exampleInput2 = Input("day22_example2.txt").lines()
    private val realInput = Input("day22.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day22(exampleInput1).part1()
        assertEquals(590784, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day22(realInput).part1()
        assertEquals(546724, result)
    }

    @Test
    fun part2_Example() {
        val result = Day22(exampleInput2).part2()
        assertEquals(2758514936282235, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day22(realInput).part2()
        assertEquals(1346544039176841, result)
    }
}
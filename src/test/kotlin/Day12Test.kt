package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    private val exampleInput1 = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )

    private val exampleInput2 = listOf(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc"
    )

    private val exampleInput3 = listOf(
        "fs-end",
        "he-DX",
        "fs-he",
        "start-DX",
        "pj-DX",
        "end-zg",
        "zg-sl",
        "zg-pj",
        "pj-he",
        "RW-he",
        "fs-DX",
        "pj-RW",
        "zg-RW",
        "start-pj",
        "he-WI",
        "zg-he",
        "pj-fs",
        "start-RW"
    )

    @Test
    fun part1_Example1() {
        val result = Day12(exampleInput1).part1()
        assertEquals(10, result)
    }

    @Test
    fun part1_Example2() {
        val result = Day12(exampleInput2).part1()
        assertEquals(19, result)
    }

    @Test
    fun part1_Example3() {
        val result = Day12(exampleInput3).part1()
        assertEquals(226, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day12().part1()
        assertEquals(3495, result)
    }

    @Test
    fun part2_Example1() {
        val result = Day12(exampleInput1).part2()
        assertEquals(36, result)
    }

    @Test
    fun part2_Example2() {
        val result = Day12(exampleInput2).part2()
        assertEquals(103, result)
    }

    @Test
    fun part2_Example3() {
        val result = Day12(exampleInput3).part2()
        assertEquals(3509, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day12().part2()
        assertEquals(94849, result)
    }
}
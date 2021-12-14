package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    private val exampleInput = listOf(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C"
    )

    @Test
    fun part1_Example() {
        val result = Day14(exampleInput).part1()
        assertEquals(1588, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day14().part1()
        assertEquals(2712, result)
    }

    @Test
    fun part2_Example() {
        val result = Day14(exampleInput).part2()
        assertEquals(2188189693529, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day14().part2()
        assertEquals(8336623059567, result)
    }
}
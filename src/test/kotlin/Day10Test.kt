package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    private val exampleInput = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    private val realInput = Input("day10.txt").lines()

    @Test
    fun part1_Example() {
        val result = Day10(exampleInput).part1()
        assertEquals(26397, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day10(realInput).part1()
        assertEquals(345441, result)
    }

    @Test
    fun part2_Example() {
        val result = Day10(exampleInput).part2()
        assertEquals(288957, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day10(realInput).part2()
        assertEquals(3235371166, result)
    }
}
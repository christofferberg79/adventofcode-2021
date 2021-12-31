package cberg.aoc2021

import cberg.aoc2021.Day24.Alu
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Day24Test {
    private val realInput = Input("day24.txt").lines()

    @Test
    fun aluExample1() {
        val alu = Alu(
            "inp x",
            "mul x -1"
        )

        alu.run("3")
        assertEquals(-3, alu.x)
    }

    @Test
    fun aluExample2() {
        val alu = Alu(
            "inp z",
            "inp x",
            "mul z 3",
            "eql z x"
        )

        alu.run("39")
        assertEquals(1, alu.z)

        alu.run("38")
        assertEquals(0, alu.z)
    }

    @Test
    fun aluExample3() {
        val alu = Alu(
            "inp w",
            "add z w",
            "mod z 2",
            "div w 2",
            "add y w",
            "mod y 2",
            "div w 2",
            "add x w",
            "mod x 2",
            "div w 2",
            "mod w 2"
        )

        alu.run("0")
        assertEquals(0, alu.w)
        assertEquals(0, alu.x)
        assertEquals(0, alu.y)
        assertEquals(0, alu.z)

        alu.run("7")
        assertEquals(0, alu.w)
        assertEquals(1, alu.x)
        assertEquals(1, alu.y)
        assertEquals(1, alu.z)

        alu.run("9")
        assertEquals(1, alu.w)
        assertEquals(0, alu.x)
        assertEquals(0, alu.y)
        assertEquals(1, alu.z)
    }

    @Test
    fun monadExample() {
        val alu = Alu(realInput)
        for (n in '1'..'9') {
            alu.run("" + n + "1111111111111")
//            alu.run("1199141978151481")
            println(alu)
        }
        assertNotEquals(0, alu.z)
    }

    @Test
    fun part1_RealInput() {
        val result = Day24(realInput).part1()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day24(realInput).part2()
        assertEquals(0, result)
    }
}
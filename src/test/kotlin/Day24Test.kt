package cberg.aoc2021

import cberg.aoc2021.Day24.Alu
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {
    private val realInput = Input("day24.txt").lines()

    @Test
    fun aluDiv() {
        val alu = Alu("div x y")

        alu.run(x0 = 8, y0 = 3)
        assertEquals(2, alu.x)

        alu.run(x0 = 8, y0 = -3)
        assertEquals(-2, alu.x)

        alu.run(x0 = -8, y0 = 3)
        assertEquals(-2, alu.x)

        alu.run(x0 = -8, y0 = -3)
        assertEquals(2, alu.x)
    }

    @Test
    fun aluMod() {
        val alu = Alu("mod x y")

        alu.run(x0 = 0, y0 = 26)
        assertEquals(0, alu.x)

        alu.run(x0 = 13, y0 = 26)
        assertEquals(13, alu.x)

        alu.run(x0 = 25, y0 = 26)
        assertEquals(25, alu.x)

        alu.run(x0 = 26, y0 = 26)
        assertEquals(0, alu.x)

        alu.run(x0 = 51, y0 = 26)
        assertEquals(25, alu.x)

        alu.run(x0 = 52, y0 = 26)
        assertEquals(0, alu.x)

        alu.run(x0 = 53, y0 = 26)
        assertEquals(1, alu.x)
    }

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
    @Ignore
    fun test() {
        val first6 = first6()
        println("first 6 done")
        val last8 = last8()
        println("last 8 done")
        val filterValues = first6.filterValues { it in last8 }
        println("filterValues: ${filterValues.size}")
        val result = filterValues
            .maxByOrNull { (s, _) -> s }
            ?.let { (s, z) -> s + last8[z] }
        println(result)
    }

    private fun first6(): Map<String, Long> {
        val alu = Alu(realInput)
        val map = mutableMapOf<String, Long>()
        for (n1 in '9' downTo '1') {
            for (n2 in '9' downTo '1') {
                for (n3 in '9' downTo '1') {
                    for (n4 in '9' downTo '1') {
                        for (n5 in '9' downTo '1') {
                            for (n6 in '9' downTo '1') {
                                for (n7 in '9' downTo '1') {
                                    val input = "$n1$n2$n3$n4$n5$n6$n7"
                                    alu.run(input)
                                    map[input] = alu.z
                                }
                            }
                        }
                    }
                }
            }
        }
        return map
    }

    private fun last8(): Map<Long, String> {
        val todo = mutableListOf("" to 0L)
        val chunked = realInput.chunked(18)
        var curlen = 0
        while (todo.firstOrNull()?.first?.length != 7) {
            val (tail, z1) = todo.removeFirst()
            if (tail.length > curlen) {
                curlen = tail.length
                println("curlen: $curlen")
            }
            val alu = Alu(chunked[13 - tail.length])
            for (z0 in 0L..1000L) {
                for (n in '1'..'9') {
                    alu.run("$n", z0 = z0)
                    if (alu.z == z1) {
                        todo += "$n$tail" to z0
                    }
                }
            }
        }
        return todo.sortedBy { (s, _) -> s }.associate { (s, z) -> z to s }
    }

    @Test
    @Ignore
    fun test14() {
        val chunked = realInput.chunked(18)
        val alu = Alu(chunked[13])
        for (n in '1'..'9') {
            for (z in 0L..10000L) {
                alu.run("$n", z0 = z)
                if (alu.z == 0L) {
                    println("z = $z, n = $n -> ${alu.z}")
                }
            }
        }
    }

    @Test
    @Ignore
    fun test13() {
        val chunked = realInput.chunked(18)
        val alu = Alu(chunked[12])
        for (n in '1'..'9') {
            for (z in 0L..10000) {
                alu.run("$n", z0 = z)
                if (alu.z in 13L..21L) {
                    println("z = $z, n = $n -> ${alu.z}")
                }
            }
        }
    }

    @Test
    @Ignore
    fun test12() {
        val chunked = realInput.chunked(18)
        val alu = Alu(chunked[11])
        for (n in '1'..'9') {
            for (z in 0L..1000L) {
                alu.run("$n", z0 = z)
                if (alu.z in 13L..14L) {
                    println("z = $z, n = $n -> ${alu.z}")
                }
            }
        }
    }

    @Test
    fun part1_RealInput() {
        val result = Day24().part1()
        assertEquals("98998519596997", result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day24().part2()
        assertEquals(0, result)
    }
}
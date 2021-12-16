package cberg.aoc2021

import cberg.aoc2021.Day16.LiteralPacket
import cberg.aoc2021.Day16.OperatorPacket
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class Day16Test {
    private val exampleInput1 = "8A004A801A8002F478"
    private val exampleInput2 = "620080001611562C8802118E34"
    private val exampleInput3 = "C0015000016115A2E0802F182340"
    private val exampleInput4 = "A0016C880162017C3686B18A3D4780"

    @Test
    fun testLiteral() {
        val p = Day16("D2FE28").packet
        assertIs<LiteralPacket>(p)
        assertEquals(2021, p.value)
    }

    @Test
    fun testOperatorType0() {
        val p = Day16("38006F45291200").packet
        assertIs<OperatorPacket>(p)
        assertEquals(2, p.subPackets.size)

        val sub1 = p.subPackets[0]
        assertIs<LiteralPacket>(sub1)
        assertEquals(10, sub1.value)

        val sub2 = p.subPackets[1]
        assertIs<LiteralPacket>(sub2)
        assertEquals(20, sub2.value)
    }

    @Test
    fun testOperatorType1() {
        val p = Day16("EE00D40C823060").packet
        assertIs<OperatorPacket>(p)
        assertEquals(3, p.subPackets.size)

        val sub1 = p.subPackets[0]
        assertIs<LiteralPacket>(sub1)
        assertEquals(1, sub1.value)

        val sub2 = p.subPackets[1]
        assertIs<LiteralPacket>(sub2)
        assertEquals(2, sub2.value)

        val sub3 = p.subPackets[2]
        assertIs<LiteralPacket>(sub3)
        assertEquals(3, sub3.value)
    }

    @Test
    fun part1_Example1() {
        val result = Day16(exampleInput1).part1()
        assertEquals(16, result)
    }

    @Test
    fun part1_Example2() {
        val result = Day16(exampleInput2).part1()
        assertEquals(12, result)
    }

    @Test
    fun part1_Example3() {
        val result = Day16(exampleInput3).part1()
        assertEquals(23, result)
    }

    @Test
    fun part1_Example4() {
        val result = Day16(exampleInput4).part1()
        assertEquals(31, result)
    }

    @Test
    fun part1_RealInput() {
        val result = Day16().part1()
        assertEquals(891, result)
    }

    @Test
    fun part2_Example() {
        val result = Day16(exampleInput1).part2()
        assertEquals(0, result)
    }

    @Test
    fun part2_RealInput() {
        val result = Day16().part2()
        assertEquals(0, result)
    }
}
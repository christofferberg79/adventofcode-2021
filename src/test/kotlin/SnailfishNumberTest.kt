package cberg.aoc2021

import kotlin.test.Test
import kotlin.test.assertEquals

class SnailfishNumberTest {
    @Test
    fun stringRepresentation() {
        val testData = listOf(
            "[1,2]",
            "[[1,2],3]",
            "[9,[8,7]]",
            "[[1,9],[8,5]]",
            "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]",
            "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]",
            "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"
        )
        for (s in testData) {
            assertEquals(s, SnailfishNumber(s).toString())
        }
    }

    @Test
    fun magnitude() {
        val testData = mapOf(
            "[[1,2],[[3,4],5]]" to 143,
            "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]" to 1384,
            "[[[[1,1],[2,2]],[3,3]],[4,4]]" to 445,
            "[[[[3,0],[5,3]],[4,4]],[5,5]]" to 791,
            "[[[[5,0],[7,4]],[5,5]],[6,6]]" to 1137,
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]" to 3488
        )

        for ((s, m) in testData) {
            val sfn = SnailfishNumber(s)
            assertEquals(m, sfn.magnitude, "Magnitude of $sfn")
        }
    }

    @Test
    fun addWithoutReduction() {
        val sfn1 = SnailfishNumber("[1,2]")
        val sfn2 = SnailfishNumber("[[3,4],5]")
        val sum = sfn1 + sfn2
        assertEquals("[[1,2],[[3,4],5]]", sum.toString())
    }

    @Test
    fun explode() {
        val testData = mapOf(
            "[[[[[9,8],1],2],3],4]" to "[[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]]" to "[7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1]" to "[[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]" to "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"
        )
        for ((before, after) in testData) {
            assertEquals(after, SnailfishNumber(before).toString(), "$before should be reduced by explosion")
        }
    }

    @Test
    fun explodeAndSplit() {
        val before = "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"
        val after = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        assertEquals(after, SnailfishNumber(before).toString(), "$before should be reduced")
    }

    @Test
    fun addAndReduce() {
        val sfn1 = SnailfishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]")
        val sfn2 = SnailfishNumber("[1,1]")
        val sum = sfn1 + sfn2
        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", sum.toString())
    }

    @Test
    fun addAndReduceEdgeCase() {
        val sfn1 = SnailfishNumber("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        val sfn2 = SnailfishNumber("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")
        val sum = sfn1 + sfn2
        assertEquals("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", sum.toString())
    }
}
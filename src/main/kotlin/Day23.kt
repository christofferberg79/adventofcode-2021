package cberg.aoc2021

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

class Day23(private val input: List<String>) {

    fun part1() = getBestSolution(parse(input), 3)

    fun part2(): Int {
        val diagram = input.subList(0, 3) + "  #D#C#B#A#" + "  #D#B#A#C#" + input.subList(3, 5)
        return getBestSolution(parse(diagram), 5)
    }

    private data class Node(val state: Map<Pos, Char>, val dist: Int, val maxY: Int) {
        fun possibleNextStates(): List<Node> {
            return possibleMoves().map { (from, to, d) ->
                val newState = state.mapKeys { (p, _) -> if (p == from) to else p }
                val newDist = dist + d
                Node(newState, newDist, maxY)
            }
        }

        private fun possibleMoves(): List<Move> {
            firstRoomToRoomOrNull()?.let { move -> return listOf(move) }
            firstHallwayToRoomOrNull()?.let { move -> return listOf(move) }
            return roomToHallway()
        }

        private fun firstRoomToRoomOrNull(): Move? {
            for (x in (3..9 step 2)) {
                inner@ for (y in 2..maxY) {
                    val c = state[Pos(x, y)] ?: continue@inner
                    if (roomX(c) == x) break@inner
                    val to = findRoomTarget(c) ?: break@inner
                    val from = Pos(x, y)
                    if (hallwayIsClear(from.x, to.x)) {
                        return Move(from, to, dist(from, to, c))
                    }
                }
            }
            return null
        }

        private fun hallwayIsClear(fromX: Int, toX: Int) =
            (min(fromX, toX)..max(fromX, toX)).none { x -> Pos(x, 1) in state }

        private fun findRoomTarget(c: Char): Pos? {
            val x = roomX(c)
            for (y in maxY downTo 2) {
                val pos = Pos(x, y)
                val cAtPos = state[pos] ?: return pos
                if (cAtPos != c) return null
            }
            return null
        }

        private fun firstHallwayToRoomOrNull(): Move? {
            for (x in 1..11) {
                val c = state[Pos(x, 1)] ?: continue
                val to = findRoomTarget(c) ?: continue
                val from = Pos(x, 1)
                if (hallwayIsClear(from.x + (to.x - from.x).sign, to.x)) {
                    return Move(from, to, dist(from, to, c))
                }
            }
            return null
        }

        private fun roomToHallway(): List<Move> {
            return (3..9 step 2).flatMap { x ->
                for (y in 2..maxY) {
                    val from = Pos(x, y)
                    val c = state[from] ?: continue
                    return@flatMap if (roomX(c) == x && (y + 1..maxY).all { y1 -> state[Pos(x, y1)] == c }) {
                        emptyList()
                    } else {
                        listOf(1, 2, 4, 6, 8, 10, 11)
                            .map { toX -> Pos(toX, 1) }
                            .filter { to -> hallwayIsClear(from.x, to.x) }
                            .map { to -> Move(from, to, dist(from, to, c)) }
                    }
                }
                emptyList()
            }
        }

        private fun dist(from: Pos, to: Pos, c: Char) =
            ((from.y - 1) + abs(to.x - from.x) + (to.y - 1)) * dist(c)

        private fun dist(c: Char) = when (c) {
            'A' -> 1
            'B' -> 10
            'C' -> 100
            'D' -> 1000
            else -> error("Invalid char $c")
        }

        private data class Move(val from: Pos, val to: Pos, val dist: Int)

        private fun roomX(c: Char) = when (c) {
            'A' -> 3
            'B' -> 5
            'C' -> 7
            'D' -> 9
            else -> error("invalid char $c")
        }

        fun isTargetState() = state.all { (p, c) ->
            p.y > 1 && p.x == roomX(c)
        }
    }

    private fun getBestSolution(startState: Map<Pos, Char>, maxY: Int): Int {
        val dist = mutableMapOf(startState to 0).withDefault { Int.MAX_VALUE }
        val todo = mutableSetOf(Node(startState, 0, maxY))
        while (todo.isNotEmpty()) {
            val node = todo.minByOrNull { it.dist }!!
            if (node.isTargetState()) {
                return node.dist
            }
            todo.remove(node)
            for (o in node.possibleNextStates()) {
                if (o.dist < dist.getValue(o.state)) {
                    dist[o.state] = o.dist
                    todo += Node(o.state, o.dist, maxY)
                }
            }
        }
        error("No solution found")
    }

    private fun parse(input: List<String>): Map<Pos, Char> {
        return input.flatMapIndexed { y: Int, line: String ->
            line.mapIndexedNotNull { x, c -> if (c in 'A'..'D') Pos(x, y) to c else null }
        }.toMap()
    }

    data class Pos(val x: Int, val y: Int)
}

package cberg.aoc2021

import kotlin.math.abs

class Day23(input: List<String>) {

    private val startState = parse(input)

    private val targetState = mapOf(
        Pos(3, 2) to 'A', Pos(3, 3) to 'A',
        Pos(5, 2) to 'B', Pos(5, 3) to 'B',
        Pos(7, 2) to 'C', Pos(7, 3) to 'C',
        Pos(9, 2) to 'D', Pos(9, 3) to 'D'
    )

    fun part1() = getBestSolution(startState, targetState)

    private data class Node(val state: Map<Pos, Char>, val dist: Int) {
        fun possibleNextStates(): List<Node> {
            return possibleMoves().map { (from, to, d) ->
                val newState = state.mapKeys { (p, _) -> if (p == from) to else p }
                val newDist = dist + d
                Node(newState, newDist)
            }
        }

        private fun possibleMoves() = state.flatMap { (p, c) -> possibleMoves(p, c) }

        private fun possibleMoves(pos: Pos, c: Char): List<Move> {
            return buildList {
                if (pos.isInHallway()) {
                    val roomBottom = Pos(roomX(c), 3)
                    val roomTop = Pos(roomX(c), 2)
                    if (canMove(from = pos, to = roomBottom)) {
                        add(Move(from = pos, to = roomBottom, dist(pos, roomBottom, c)))
                    } else if (state[roomBottom] == c && canMove(from = pos, to = roomTop)) {
                        add(Move(from = pos, to = roomTop, dist(pos, roomTop, c)))
                    }
                } else if (pos.x != roomX(c) || pos.y == 2 && state[pos.copy(y = 3)] != c) {
                    addAll(listOf(1, 2, 4, 6, 8, 10, 11).map { x -> Pos(x, 1) }
                        .filter { to -> canMove(from = pos, to) }
                        .map { to -> Move(from = pos, to, dist(from = pos, to, c)) })
                }
            }
        }

        private fun dist(from: Pos, to: Pos, c: Char): Int {
            val md = abs(from.x - to.x) + abs(from.y - to.y)
            return md * when (c) {
                'A' -> 1
                'B' -> 10
                'C' -> 100
                'D' -> 1000
                else -> error("Invalid char $c")
            }
        }

        private data class Move(val from: Pos, val to: Pos, val dist: Int)

        private fun Pos.isInHallway() = y == 1
        private fun roomX(c: Char) = when (c) {
            'A' -> 3
            'B' -> 5
            'C' -> 7
            'D' -> 9
            else -> error("invalid char $c")
        }

        private fun canMove(from: Pos, to: Pos): Boolean {
            check(from.isInHallway() xor to.isInHallway())
            if (to in state) {
                return false
            }
            if (from.isInHallway()) {
                if (from.x < to.x) {
                    if ((from.x + 1..to.x).any { x -> Pos(x, 1) in state }) {
                        return false
                    }
                } else if (from.x > to.x) {
                    if ((from.x - 1 downTo to.x).any { x -> Pos(x, 1) in state }) {
                        return false
                    }
                } else {
                    error("from.x and to.x should not be equal")
                }
                if (to.y == 3 && to.copy(y = 2) in state) {
                    return false
                }
                return true
            } else if (to.isInHallway()) {
                if (from.y == 3 && from.copy(y = 2) in state) {
                    return false
                }
                if (from.x < to.x) {
                    if ((from.x..to.x).any { x -> Pos(x, 1) in state }) {
                        return false
                    }
                } else if (from.x > to.x) {
                    if ((from.x downTo to.x).any { x -> Pos(x, 1) in state }) {
                        return false
                    }
                } else {
                    error("from.x and to.x should not be equal")
                }
                return true
            } else {
                error("from or to should be in the hallway")
            }
        }
    }

    private fun getBestSolution(startState: Map<Pos, Char>, targetState: Map<Pos, Char>): Int {
        val dist = mutableMapOf(startState to 0).withDefault { Int.MAX_VALUE }
        val todo = mutableSetOf(Node(startState, 0))
        while (todo.isNotEmpty()) {
            val node = todo.minByOrNull { it.dist }!!
            if (node.state == targetState) {
                return node.dist
            }
            todo.remove(node)
            for (o in node.possibleNextStates()) {
                if (o.dist < dist.getValue(o.state)) {
                    dist[o.state] = o.dist
                    todo += Node(o.state, o.dist)
                }
            }
        }
        error("No solution found")
    }

    private fun parse(input: List<String>): Map<Pos, Char> {
        return listOf(
            Pos(3, 2), Pos(3, 3), Pos(5, 2), Pos(5, 3),
            Pos(7, 2), Pos(7, 3), Pos(9, 2), Pos(9, 3)
        ).associateWith { p -> input[p.y][p.x] }
    }

    fun part2() = 0

    data class Pos(val x: Int, val y: Int)
}

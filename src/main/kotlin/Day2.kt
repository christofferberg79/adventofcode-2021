package cberg.aoc2021

class Day2(private val commands: List<String>) {
    constructor() : this(Input("day2.txt").lines())

    fun part1(): Int {
        val finalPosition = commands.fold(Position()) { pos, command -> pos.apply1(command) }
        return finalPosition.horizontal * finalPosition.depth
    }

    fun part2(): Int {
        val finalPosition = commands.fold(Position()) { pos, command -> pos.apply2(command) }
        return finalPosition.horizontal * finalPosition.depth
    }
}

private fun Position.apply1(command: String): Position {
    val (dir, dist) = parseCommand(command)
    return when (dir) {
        "forward" -> copy(horizontal = horizontal + dist)
        "down" -> copy(depth = depth + dist)
        "up" -> copy(depth = depth - dist)
        else -> error("Invalid command $command")
    }
}

private fun Position.apply2(command: String): Position {
    val (dir, dist) = parseCommand(command)
    return when (dir) {
        "forward" -> copy(horizontal = horizontal + dist, depth = depth + aim * dist)
        "down" -> copy(aim = aim + dist)
        "up" -> copy(aim = aim - dist)
        else -> error("Invalid command $command")
    }
}

private fun parseCommand(command: String) =
    command.split(" ").let { (dir, dist) -> dir to dist.toInt() }

private data class Position(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)
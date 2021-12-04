package cberg.aoc2021

class Day2(commands: List<String>) {
    constructor() : this(Input("day2.txt").lines())

    private val commands = commands.map { Command(it) }

    fun part1() = solve(Interpreter1)
    fun part2() = solve(Interpreter2)

    private fun solve(interpreter: Interpreter): Int {
        val finalPosition = commands.fold(Position()) { pos, command -> interpreter.run(command, pos) }

        return finalPosition.horizontal * finalPosition.depth
    }
}

private data class Position(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)

private data class Command(val dir: String, val dist: Int)

private fun Command(command: String) =
    command.split(" ").let { (dir, dist) -> Command(dir, dist.toInt()) }

private interface Interpreter {
    fun run(command: Command, position: Position) = when (command.dir) {
        "forward" -> position.forward(command.dist)
        "down" -> position.down(command.dist)
        "up" -> position.up(command.dist)
        else -> error("Invalid command $command")
    }

    fun Position.forward(dist: Int): Position
    fun Position.down(dist: Int): Position
    fun Position.up(dist: Int): Position
}

private object Interpreter1 : Interpreter {
    override fun Position.forward(dist: Int) = copy(horizontal = horizontal + dist)
    override fun Position.down(dist: Int) = copy(depth = depth + dist)
    override fun Position.up(dist: Int) = copy(depth = depth - dist)
}

private object Interpreter2 : Interpreter {
    override fun Position.forward(dist: Int) = copy(horizontal = horizontal + dist, depth = depth + aim * dist)
    override fun Position.down(dist: Int) = copy(aim = aim + dist)
    override fun Position.up(dist: Int) = copy(aim = aim - dist)
}

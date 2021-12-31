package cberg.aoc2021

class Day24(private val input: List<String>) {

    fun part1(): Long {
        val alu = Alu(input)
        var input = ""
        for (i in 0..13) {
            bla@ for (n in 9 downTo 1) {
                val testInput = input + n.toString()
                alu.run(testInput)
                if (alu.z == 0L) {
                    input = testInput
                    break@bla
                }
            }

        }
        return input.toLong()
    }

    fun part2() = 0

    class Alu(private val instructions: List<String>) {
        constructor(vararg instructions: String) : this(instructions.toList())

        private val variables = mutableMapOf('w' to 0L, 'x' to 0L, 'y' to 0L, 'z' to 0L)

        val w: Long get() = variables['w']!!
        val x: Long get() = variables['x']!!
        val y: Long get() = variables['y']!!
        val z: Long get() = variables['z']!!

        fun run(input: String) {
            variables.replaceAll { _, _ -> 0 }
            var nextInput = 0
            instructions.forEachIndexed { index, ins ->
                val parts = ins.split(" ")
                when (parts[0]) {
                    "inp" -> {
//                        println("${index + 1}: before $ins -> w: ${get("w")}, x: ${get("x")}, y: ${get("y")}, z: ${get("z")}")
                        if (nextInput > input.lastIndex) {
                            return
                        }
                        set(parts[1], input[nextInput++].digitToInt().toLong())
                    }
                    "add" -> set(parts[1], get(parts[1]) + get(parts[2]))
                    "mul" -> set(parts[1], get(parts[1]) * get(parts[2]))
                    "div" -> set(parts[1], get(parts[1]) / get(parts[2]))
                    "mod" -> set(parts[1], get(parts[1]) % get(parts[2]))
                    "eql" -> set(parts[1], if (get(parts[1]) == get(parts[2])) 1 else 0)
                }
//                println("${index + 1}: $ins -> w: ${get("w")}, x: ${get("x")}, y: ${get("y")}, z: ${get("z")}")
            }
//            println("final state -> w: ${get("w")}, x: ${get("x")}, y: ${get("y")}, z: ${get("z")}")
        }

        override fun toString() = "w: ${get("w")}, x: ${get("x")}, y: ${get("y")}, z: ${get("z")}"

        private fun set(variable: String, value: Long) {
            variables[variable.single()] = value
        }

        private fun get(variable: String): Long {
            return variable.toLongOrNull() ?: variables[variable.single()]!!
        }
    }
}

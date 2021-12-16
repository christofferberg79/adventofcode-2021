package cberg.aoc2021

class Day16(input: String) {
    constructor() : this(Input("day16.txt").oneLine())

    val packet = parse(input)

    fun part1() = packet.asSequence().sumOf(Packet::version)

    fun part2() = 0

    private fun parse(s: String): Packet {
        val binary = s.map { it.digitToInt(16).toString(2).padStart(4, '0') }
            .joinToString("")
        return parse(PacketReader(binary))
    }

    class PacketReader(private val s: String) {
        private var pos = 0
        fun read(n: Int) = s.substring(pos, pos + n).also { pos += n }
        fun readInt(n: Int) = read(n).toInt(2)
        fun isNotDone() = pos < s.length
    }

    private fun parse(r: PacketReader): Packet {
        val version = r.readInt(3)
        val typeId = r.readInt(3)
        return when (typeId) {
            4 -> {
                LiteralPacket(version, parseLiteral(r))
            }
            else -> { // operator
                val lengthTypeId = r.readInt(1)
                val subPackets = when (lengthTypeId) {
                    0 -> { // by length
                        val length = r.readInt(15)
                        parseMany(PacketReader(r.read(length)))
                    }
                    1 -> { // by number
                        val number = r.readInt(11)
                        List(number) { parse(r) }
                    }
                    else -> error("Invalid length type ID: $lengthTypeId")
                }
                OperatorPacket(version, subPackets)
            }
        }
    }

    private fun parseMany(r: PacketReader): List<Packet> {
        val packets = mutableListOf<Packet>()
        while (r.isNotDone()) {
            packets.add(parse(r))
        }
        return packets
    }

    private fun parseLiteral(r: PacketReader): Long {
        var prefix = 1
        val sb = StringBuilder()
        while (prefix == 1) {
            prefix = r.readInt(1)
            sb.append(r.read(4))
        }
        return sb.toString().toLong(2)
    }

    sealed interface Packet {
        val version: Int
        fun asSequence(): Sequence<Packet> = sequenceOf(this)
    }

    class LiteralPacket(override val version: Int, val value: Long) : Packet
    class OperatorPacket(override val version: Int, val subPackets: List<Packet>) : Packet {
        override fun asSequence() = subPackets.fold(super.asSequence()) { s, p -> s + p.asSequence() }
    }
}

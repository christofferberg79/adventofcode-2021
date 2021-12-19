package cberg.aoc2021

class Day16(input: String) {
    val packet = parse(input)

    fun part1() = packet.asSequence().sumOf(Packet::version)

    fun part2() = packet.evaluate()

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
            4 -> LiteralPacket(version, parseLiteral(r))
            0 -> SumPacket(version, parseSubPackets(r))
            1 -> ProductPacket(version, parseSubPackets(r))
            2 -> MinimumPacket(version, parseSubPackets(r))
            3 -> MaximumPacket(version, parseSubPackets(r))
            5 -> GreaterThanPacket(version, parseSubPackets(r))
            6 -> LessThanPacket(version, parseSubPackets(r))
            7 -> EqualToPacket(version, parseSubPackets(r))
            else -> error("Invalid type ID: $typeId")
        }
    }

    private fun parseSubPackets(r: PacketReader): List<Packet> {
        val lengthTypeId = r.readInt(1)
        return when (lengthTypeId) {
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
        fun evaluate(): Long
        fun asSequence(): Sequence<Packet> = sequenceOf(this)
    }

    class LiteralPacket(override val version: Int, val value: Long) : Packet {
        override fun evaluate() = value
    }

    abstract class OperatorPacket(override val version: Int, val subPackets: List<Packet>) : Packet {
        override fun evaluate() = calc(subPackets.map(Packet::evaluate))
        abstract fun calc(values: List<Long>): Long
        override fun asSequence() = subPackets.fold(super.asSequence()) { s, p -> s + p.asSequence() }
    }

    class SumPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, subPackets) {
        override fun calc(values: List<Long>) = values.sum()
    }

    class ProductPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, subPackets) {
        override fun calc(values: List<Long>) = values.reduce { a, b -> a * b }
    }

    class MinimumPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, subPackets) {
        override fun calc(values: List<Long>) = values.minOrNull() ?: error("subPackets should not be empty")
    }

    class MaximumPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, subPackets) {
        override fun calc(values: List<Long>) = values.maxOrNull() ?: error("subPackets should not be empty")
    }

    abstract class ComparisonPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, subPackets) {
        abstract fun comp(v1: Long, v2: Long): Boolean
        override fun calc(values: List<Long>) = if (comp(values.first(), values.last())) 1L else 0L
    }

    class GreaterThanPacket(version: Int, subPackets: List<Packet>) : ComparisonPacket(version, subPackets) {
        override fun comp(v1: Long, v2: Long) = v1 > v2
    }

    class LessThanPacket(version: Int, subPackets: List<Packet>) : ComparisonPacket(version, subPackets) {
        override fun comp(v1: Long, v2: Long) = v1 < v2
    }

    class EqualToPacket(version: Int, subPackets: List<Packet>) : ComparisonPacket(version, subPackets) {
        override fun comp(v1: Long, v2: Long) = v1 == v2
    }
}

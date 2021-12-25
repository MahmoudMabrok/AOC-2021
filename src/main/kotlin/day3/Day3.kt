package day3

import readInput
import java.io.File


val zero = '0'
val ones = '1'

data class BitCount(val zeros: Int, val ones: Int) {

    fun getMax(): String {
        return if (zeros > ones) {
            "0"
        } else {
            "1"
        }
    }
}

fun main() {

    fun part1(data: List<String>): Int {
        // Part 1
        val inputs = data.map { it.toCharArray().toList() }
        // prepare data
        val board = mutableListOf<HashMap<Char, Int>>()
        inputs.forEachIndexed { _, line ->
            line.forEachIndexed { index, c ->
                if (board.getOrNull(index) == null) {
                    board.add(
                        index, hashMapOf(
                            zero to 0,
                            ones to 0
                        )
                    )
                }
                val count = board[index].getOrDefault(c, 0) + 1
                board[index][c] = count
            }
        }
        // calculate
        val gammas = board.map {
            if (it.getValue(zero) > it.getValue(ones))
                zero
            else {
                ones
            }
        }

        val epsiloin = board.map {
            if (it.getValue(zero) < it.getValue(ones))
                zero
            else {
                ones
            }
        }
        val gamma = String(gammas.toCharArray()).toInt(2)
        val empsilon = String(epsiloin.toCharArray()).toInt(2)

        return gamma * empsilon
    }


    fun part1Improved(data: List<String>): Int {
        // Part 1
        val columns = data[0].indices
        val inputs = columns.map {
            data.buildColumn(it)
        }

        val gamma = buildString {
            inputs.forEach {
                append(it.getMax())
            }
        }

        val epsilon = gamma.invertBits()

        return gamma.toInt(radix = 2) * epsilon.toInt(radix = 2)
    }



    println("results ${part1(readInput("day3"))}")
    println("results ${part1Improved(readInput("day3"))}")

}

private fun String.invertBits(): String {
    return asIterable()
        .joinToString(separator = "") {
            if (it.toString() == "0") "1" else "0"
        }

}

private fun List<String>.buildColumn(column: Int): BitCount {
    var zeros = 0
    var ones = 0
    forEach {
        if (it[column].toString() == "0") {
            zeros += 1
        } else {
            ones += 1
        }
    }
    return BitCount(zeros, ones)
}

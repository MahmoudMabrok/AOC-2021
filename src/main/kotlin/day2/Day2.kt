package day2

import java.io.File

val dataSource = File("src/main/kotlin/day2", "day2.txt")

data class Operation(val op: String, val value: Int) {

    fun performPartOne(result: Result) {
        when (op) {
            "forward" -> result.pos += value
            "up" -> result.depth -= value
            "down" -> result.depth += value
        }
    }

    fun performPartTwo(result: Result) {
        when (op) {
            "forward" -> {
                result.pos += value
                result.depth += (value * result.aim)
            }
            "up" -> {
                // result.depth -= value
                result.aim -= value
            }
            "down" -> {
                //  result.depth += value
                result.aim += value
            }
        }
    }


}

data class Result(var pos: Int = 0, var depth: Int = 0, var aim: Int = 0) {
    fun calculate() = pos * depth
    fun reset() {
        pos = 0
        depth = 0
        aim = 0
    }
}

fun main() {
    // Part 1
    val result = Result()
    val inputs = dataSource
        .readLines()
        .map {
            val line = it.split(" ")
            Operation(line.first().toString(), line.last().toString().toInt())
        }

    inputs.forEach {
        it.performPartOne(result)
    }
    println("result 1  ${result.calculate()}")


    result.reset()
    inputs.forEach {
        it.performPartTwo(result)
    }
    println("result 2  ${result.calculate()}")

}
package day1

import java.io.File

val dataSource =  File("src/main/kotlin/day1" , "day1.txt")

fun main() {
    // Part 1
    val inputs = dataSource.readLines().map { it.toInt() }
    var result = inputs
        .zipWithNext()
        .map { it.second > it.first }
        .count { it }

    println("res 1  $result")

    // Part 2
    result = inputs.windowed(size = 3)
        .map { it.sum() }
        .zipWithNext()
        .map { it.second > it.first }
        .count { it }

    println("res 2  $result")

}
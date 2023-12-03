package day02


import readInput

fun q1(testInput: List<String>) : Int {

    var sum = 0

    val available = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    for (row in testInput) {
        val rowList = row.trim().split(":")
        val game = rowList[0].split(" ")[1]
        val setList = rowList[1].split(";")
        var isPlayable = true

        for (set in setList) {
            val subset = set.split(",")
            subset.forEach { cubes ->
                val amount = cubes.split(" ")[1]
                val color = cubes.split(" ")[2]

                if (available[color]!! < amount.toInt()) {
                    isPlayable = false
                }
            }
        }

        if (isPlayable) {
            sum += game.toInt()
        }

    }

    return sum

}

fun q2(testInput: List<String>) : Int {

    var sum = 0

    for (row in testInput) {
        val rowList = row.trim().split(":")
        val game = rowList[0].split(" ")[1]
        val setList = rowList[1].split(";")

        val available = mutableMapOf(
            "red" to 0,
            "green" to 0,
            "blue" to 0,
        )

        for (set in setList) {
            val subset = set.split(",")
            subset.forEach { cubes ->
                val amount = cubes.split(" ")[1].toInt()
                val color = cubes.split(" ")[2]

                if (available[color]!! < amount) {
                    available[color] = amount
                }
            }
        }

        var power = 1
        for (key in available.keys) {
            power *= available[key]!!
        }
        sum += power
    }

    return sum

}


fun main() {
    val input = readInput("Day02/input")

    println("Q1: " + q1(input))
    println("Q2: " + q2(input))

}
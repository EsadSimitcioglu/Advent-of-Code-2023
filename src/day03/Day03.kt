package day03


import readInput


fun q1(testInput: List<String>) : Int {

    var sum = 0
    testInput.forEachIndexed { lineIndex, line ->

        var number = 0
        var isAdjacent = false

        line.forEachIndexed { charIndex, char ->
            if (char.isDigit()) {
                number = number * 10 + char.toString().toInt()
                for (ii in -1..1) {
                    for (jj in -1..1) {
                        if (lineIndex + ii >= 0 && lineIndex + ii < testInput.size && charIndex + jj >= 0 && charIndex + jj < line.length) {
                            val cPrime = testInput[lineIndex + ii][charIndex + jj]
                            if (cPrime != '.' && !cPrime.isDigit()) {
                                isAdjacent = true
                            }
                        }
                    }
                }
            }
            if (!char.isDigit() || charIndex == line.length - 1){
                if (isAdjacent) {
                    sum += number
                    isAdjacent = false
                }
                number = 0
            }
        }
    }
    return sum
}

fun q2(testInput: List<String>) : Int {

    var sum = 0

    val gearMap = mutableMapOf<Pair<Int,Int>, List<Int>>()

    testInput.forEachIndexed { lineIndex, line ->

        var number = 0
        var gearIndex = Pair(0,0)

        line.forEachIndexed { charIndex, char ->
            if (char.isDigit()) {
                number = number * 10 + char.toString().toInt()
                for (ii in -1..1) {
                    for (jj in -1..1) {
                        if (lineIndex + ii >= 0 && lineIndex + ii < testInput.size && charIndex + jj >= 0 && charIndex + jj < line.length) {
                            val cPrime = testInput[lineIndex + ii][charIndex + jj]
                            if (cPrime == '*') {
                                gearIndex = Pair(lineIndex + ii, charIndex + jj)
                            }
                        }
                    }
                }
            }
            if (!char.isDigit() || charIndex == line.length - 1){
                if (gearIndex != Pair(0,0)) {
                    if (gearMap.containsKey(gearIndex)) {
                        gearMap[gearIndex] = gearMap[gearIndex]!!.plus(number)
                    } else {
                        gearMap[gearIndex] = listOf(number)
                    }
                    gearIndex = Pair(0,0)
                }
                number = 0
            }
        }
    }

    gearMap.forEach { (_, v) ->
        if (v.size == 2) {
            sum += v[0] * v[1]
        }
    }

    return sum


}



    fun main() {
    val input = readInput("Day03/input")

    println("Q1: ${q1(input)}")
    println("Q2: ${q2(input)}")

}
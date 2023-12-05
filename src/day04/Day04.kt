package day04


import readInput


fun q1(testInput: List<String>) : Int {

    var sum = 0

    testInput.forEach { line ->
        val winningNumbers = line.split(":")[1].split("|")[0].split(" ")
        val winningNumbersFiltered = winningNumbers.filter { it.isNotBlank() }

        val gameNumbers = line.split(":")[1].split("|")[1].split(" ")
        val gameNumbersFiltered = gameNumbers.filter { it.isNotBlank() }

        var point = 0
        for (gameNumber in gameNumbersFiltered) {
            if (winningNumbersFiltered.contains(gameNumber)) {
                point = if (point==0) 1 else point*2
            }
        }
        sum += point
     }
    return sum

}

fun q2(testInput: List<String>) : Int {

    val pointMap = mutableMapOf<String, Int>()
    val cardMap = mutableMapOf<String, MutableList<List<String>>>()

    testInput.forEach { line ->
        val card_no = line.split(":")[0].split(" ").last()
        val winningNumbers = line.split(":")[1].split("|")[0].split(" ")
        val winningNumbersFiltered = winningNumbers.filter { it.isNotBlank() }

        val gameNumbers = line.split(":")[1].split("|")[1].split(" ")
        val gameNumbersFiltered = gameNumbers.filter { it.isNotBlank() }

        var point = 0
        for (gameNumber in gameNumbersFiltered) {
            if (winningNumbersFiltered.contains(gameNumber)) {
                point+=1
            }
        }
        pointMap[card_no] = point
        cardMap[card_no] = mutableListOf(mutableListOf(line))
    }

    var card_no = "1"
    var index = 0

    while (true) {
        if (card_no !in cardMap) {
            break
        }

        val row = cardMap[card_no]?.get(index)
        val copy = pointMap[card_no] ?: 0

        for (i in 0 until copy) {
            val inserted_card_no = (card_no.toInt() + i + 1).toString()
            val inserted_row = cardMap[inserted_card_no]?.get(0)
            if (inserted_row != null) {
                cardMap[inserted_card_no]?.add(inserted_row)
            }
        }

        index++

        if (index >= (cardMap[card_no]?.size ?: 0)) {
            index = 0
            card_no = (card_no.toInt() + 1).toString()
        }
    }

    var scracard = 0

    for ((_, rowList) in cardMap) {
        scracard += rowList.size
    }

    return scracard

}



fun main() {
    val input = readInput("Day04/input")

    println("Q1: ${q1(input)}")
    println("Q2: ${q2(input)}")

}
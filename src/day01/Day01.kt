package day01

import readInput


fun q1(testInput: List<String>) : Int {

    var sum = 0

    for (line in testInput){
        val digitList: MutableList<String> = mutableListOf()

        for (char in line){
            if (char.isDigit()){
                digitList.add(char.toString())
            }
        }
        val number = (digitList.first() + digitList.last()).toInt()
        sum += number
    }

    return sum

}

fun q2(testInput: List<String>) : Int {

    val wordToNumDict = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    var sum = 0

    for (line in testInput){
        val digitList: MutableList<String> = mutableListOf()
        var word = ""

        for (char in line){

            if (char.isDigit()){
                word = ""
                digitList.add(char.toString())
            }
            else{
                word += char

                for (key in wordToNumDict.keys) {
                    if (key in word) {
                        digitList.add(wordToNumDict[key]!!)
                        word = word.substring(word.length-1)
                        break
                    }
                }
            }
        }
        val number = (digitList.first() + digitList.last()).toInt()
        sum += number
    }

    return sum

}

fun main() {
    val testInput = readInput("Day01/input")

    println("Q1: " + q1(testInput))
    println("Q2: " + q2(testInput))

}

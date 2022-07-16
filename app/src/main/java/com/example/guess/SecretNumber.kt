package com.example.guess

import java.util.*
import kotlin.random.Random

class SecretNumber() {
    var secret: Int = Random.nextInt(10) + 1
    var count = 0

    fun validate(number: Int):Int {
        count++
        return secret - number
    }

    fun reset() {
        secret = Random.nextInt(10) + 1
        count = 0
    }

}

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(3)}, count ${secretNumber.count}")
}



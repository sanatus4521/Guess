package com.example.guess

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel : ViewModel() {
    var counter = MutableLiveData<Int>()
    var counterNum = 0
    var result = MutableLiveData<Result>()
    var secret = 0
    init {
        reset()
    }

    fun guess(num: Int) {
        counterNum++
        counter.value = counterNum
        val gameResult = when (secret - num) {
            0 -> Result.YES
            in 1..Int.MAX_VALUE -> Result.BIGGER
            else -> Result.LOWER
        }
        result.value = gameResult
    }

    fun reset() {
        secret = Random.nextInt(10) + 1
        counter.value = 0
        counterNum = 0
    }
}

enum class Result {
    BIGGER, LOWER, YES
}
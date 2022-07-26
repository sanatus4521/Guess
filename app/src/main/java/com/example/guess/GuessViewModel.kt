package com.example.guess

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guess.data.Record
import kotlin.random.Random

class GuessViewModel : ViewModel() {
    var counter = MutableLiveData<Int>()
    var counterNum = 0
    var result:Result? = null
    var secret = 0
    init {
        reset()
    }

    fun guess(num: Int) {
        counterNum++
        counter.value = counterNum
        result = when (secret - num) {
            0 -> Result.YES
            in 1..Int.MAX_VALUE -> Result.BIGGER
            else -> Result.LOWER
        }
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
package com.example.travel_expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel( application: Application) : AndroidViewModel(application) {
    var count = MutableLiveData<String>()
    var change = MutableLiveData<Double>()
    init {
        count.value = "0"
        change.value = 0.0
    }

    fun set_money(money : String, unit: String){
        count.value = (change.value?.times(money.toDouble())).toString() + unit
    }

    fun set_change(c : Double){
        change.value = c
    }
}
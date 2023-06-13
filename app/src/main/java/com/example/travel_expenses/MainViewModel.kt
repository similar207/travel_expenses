package com.example.travel_expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel( application: Application) : AndroidViewModel(application) {
    var count = MutableLiveData<String>()
    init {
        count.value = "0"
    }

    fun set_money(money : String){
        count.value = money
    }
}
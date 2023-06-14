package com.example.travel_expenses

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class apiresponse(
    @SerializedName("date")
    var date:String?=null,
    @SerializedName("usd")
    var usd:Double
)

package com.agauchat.android.hackerrank.data.model

import com.google.gson.annotations.SerializedName

data class BestSellersResponse(
    @SerializedName("results") val bestSellers: BestSellers
)

data class BestSellers(
    @SerializedName("best_sellers") val isbnList: List<String>
)
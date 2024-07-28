package com.example.vimos.utils

import android.icu.text.DecimalFormat

object APIConstants {

    const val BASE_URL = "https://vimos.ru:1480/"

    const val BASE_IMAGE_URL = "https://vimos.ru"

    fun formatPrice(price: Int): String {
        val df = DecimalFormat()
        df.isGroupingUsed = true
        df.groupingSize = 3
        return df.format(price).replace(",", " ")
    }

}
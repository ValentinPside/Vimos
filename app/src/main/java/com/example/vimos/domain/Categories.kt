package com.example.vimos.domain

import android.os.Parcelable

interface Categories {
    val title: String
    val slug: String
    val subCategories: List<Categories>
}
package com.example.vimos.domain

import android.os.Parcelable

interface Categories: Parcelable {
    val title: String
    val subCategories: List<Categories>
}
package com.example.vimos.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vimos.R
import com.example.vimos.domain.Categories

class MainActivity : AppCompatActivity() {

    private var categoryList: Categories? = null
    private var secondFragmentIndex: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setData(categoryList: Categories){
        this.categoryList = categoryList
    }

    fun getData(): Categories?{
        return categoryList
    }

    fun setSecondFragmentIndex(secondFragmentIndex: Int){
        this.secondFragmentIndex = secondFragmentIndex
    }

    fun getSecondFragmentIndex(): Int?{
        return secondFragmentIndex
    }
}
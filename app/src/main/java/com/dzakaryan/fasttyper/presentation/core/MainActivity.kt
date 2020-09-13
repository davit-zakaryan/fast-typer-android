package com.dzakaryan.fasttyper.presentation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dzakaryan.fasttyper.R

class MainActivity : AppCompatActivity() {

    //val appBarConfiguration = AppBarConfiguration(.graph, drawerLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
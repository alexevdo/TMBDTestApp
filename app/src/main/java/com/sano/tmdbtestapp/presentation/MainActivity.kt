package com.sano.tmdbtestapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sano.tmdbtestapp.R
import com.sano.tmdbtestapp.presentation.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MovieListFragment())
            .commit()
    }
}
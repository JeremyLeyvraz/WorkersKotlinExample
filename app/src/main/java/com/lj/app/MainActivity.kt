package com.lj.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this.baseContext
        setContent {

            Column {
                Row {
                    Button(onClick = {
                        mainViewModel.addDateOnceWorker(context)
                    }) {
                        Text(text = "Add date once")
                    }
                }
                Row {
                    Button(onClick = {
                        mainViewModel.addDatePeriodicWorker(context)
                    }) {
                        Text(text = "Add date periodic: 15min")
                    }
                }
                Row {
                    // Load all bankrolls
                    LaunchedEffect(mainViewModel) {
                        mainViewModel.loadAllDates()
                    }
                    // Display each bankroll
                    LazyColumn {
                        items(mainViewModel.allDates) {
                            Text(text = it.date)
                        }
                    }
                }
            }
        }
    }
}

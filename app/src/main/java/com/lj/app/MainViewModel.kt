package com.lj.app

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.lj.libraryExample.MyWorker
import com.lj.libraryExample.Date
import com.lj.libraryExample.DateDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dateDao: DateDao
) : ViewModel() {

    private var _allDates by mutableStateOf(emptyList<Date>())

    /**
     * All saved bankrolls
     */
    val allDates : List<Date>
        get() = _allDates

    /**
     * Load all saved bankrolls.
     */
    fun loadAllDates() {
        viewModelScope.launch(Dispatchers.IO) {
            getDates().collect {
                _allDates = it
            }
        }
    }

    /**
     * Get all saved dates.
     */
    private fun getDates(): Flow<List<Date>> = dateDao.getAllDates()

    /**
     * Run the worker once time.
     */
    fun addDateOnceWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    /**
     * Run the worker periodically: every 15 minutes.
     */
    fun addDatePeriodicWorker(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}

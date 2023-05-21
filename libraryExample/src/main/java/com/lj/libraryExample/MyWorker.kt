package com.lj.libraryExample

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Instant

/**
 * Worker with Hilt annotation to allow the injection of the DAO.
 */
@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val dateDao: DateDao
): CoroutineWorker(context, workerParams) {

    /**
     * Execute the worker
     */
    override suspend fun doWork(): Result {
        // Insert the current time
        dateDao.insert(Date(0, Instant.now().toString()))
        return Result.success()
    }
}
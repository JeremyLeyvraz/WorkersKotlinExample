package com.lj.libraryExample

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to inject/provide dao.
 */
@InstallIn(SingletonComponent::class)
@Module
object Module {

    /**
     * Provide the date DAO.
     *
     * @param appDatabase The application database.
     *
     * @return The date DAO.
     */
    @Provides
    fun provideDateDao(appDatabase: AppDatabase): DateDao {
        return appDatabase.dateDao()
    }

    /**
     * Provide the application database.
     *
     * @param appContext The application context.
     *
     * @return The application database.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }
}

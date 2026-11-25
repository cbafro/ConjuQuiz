package com.example.conjuquiz.depInj

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.conjuquiz.daos.QuizResultDAO
import com.example.conjuquiz.data.ConjuDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ConjuDB =
        Room.databaseBuilder<ConjuDB>(context, "boardgames.db")
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()


    @Provides
    fun provideQuizResultDao(db: ConjuDB): QuizResultDAO {
        return db.quizResultDao()
    }

}


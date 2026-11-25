package com.example.conjuquiz.depInj



import com.example.conjuquiz.data.reps.ResultsRep
import com.example.conjuquiz.data.reps.RoomResultsRep
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // @Binds => When a VM asks for a ResultsRepository give it a RoomResultsRepository."
    @Binds
    @Singleton
    abstract fun bindResultsRepository(impl: RoomResultsRep): ResultsRep
}
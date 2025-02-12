package com.example.curd.Di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.curd.Domain.usecase.getTitles
import com.example.curd.data.local.TitleDao
import com.example.curd.data.local.titleDatabased
import com.example.curd.data.local.userTitile
import com.example.curd.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context):titleDatabased
    {
        return Room.databaseBuilder(
            context,titleDatabased::class.java,"user_db"
        ).build()
    }
    @Provides
    fun provideTitleDao(database:titleDatabased):TitleDao
    {
        return database.user()
    }

    @Provides
    fun provideuserRepository(tilteDao: TitleDao):Repository
    {
        return Repository(titleDao = tilteDao)
    }

    @Provides
    fun provideuserUseCase(repository: Repository):getTitles
    {
        return getTitles(repository)
    }

}
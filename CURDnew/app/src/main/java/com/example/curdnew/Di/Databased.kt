package com.example.curdnew.Di

import android.content.Context
import androidx.room.Room
import com.example.curdnew.Data.Local.Databased
import com.example.curdnew.Data.Local.TitleDao
import com.example.curdnew.Data.Repositry.Repositryimp
import com.example.curdnew.Module.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Databasedmodule
{
    @Provides
    @Singleton
    fun provideDatabased(@ApplicationContext context:Context): Databased
    {
      return Room.databaseBuilder(context,Databased::class.java,"DataBased").build()

    }
    @Provides
    @Singleton
    fun provideDao(databased: Databased):TitleDao
    {
        return databased.titleDao()
    }




}
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositryModul{
    @Binds
    @Singleton
    abstract fun bind(imp:Repositryimp):Repository
}



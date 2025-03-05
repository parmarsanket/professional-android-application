package com.example.notesapp.Notes.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.Notes.data.Repo.RepoImp

import com.example.notesapp.Notes.data.locatdata.NoteDatabase
import com.example.notesapp.Notes.domain.Repo.NoteRepo
import com.example.notesapp.Notes.domain.usecase.AddNote
import com.example.notesapp.Notes.domain.usecase.DeleteNote
import com.example.notesapp.Notes.domain.usecase.GetNote
import com.example.notesapp.Notes.domain.usecase.GetNotes
import com.example.notesapp.Notes.domain.usecase.NoteUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoredatabased(app:Application):NoteDatabase {
        return Room.databaseBuilder(
            app, NoteDatabase::class.java, "Notes"
        ).build()
    }
    @Provides
    @Singleton
    fun provideRepo( db:    NoteDatabase):NoteRepo{
        return RepoImp(db.noteDao())
    }
      fun provideUsecase(repo: NoteRepo):NoteUsesCases
      {
          return NoteUsesCases(
              getNotes = GetNotes(repo),
              deleteNote = DeleteNote(repo),
              addNote = AddNote(repo   ),
              getNote = GetNote(repo)
          )
      }

}
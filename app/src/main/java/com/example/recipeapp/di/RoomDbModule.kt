package com.example.recipeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.Room.RecipeDatabse
import com.example.recipeapp.Room.RecipeEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule{
    @Provides
    @Singleton
    fun ProvideRoom(@ApplicationContext context: Context) =Room.databaseBuilder(
    context, RecipeDatabse::class.java, "Recipe_Database")
    .allowMainThreadQueries()
    .fallbackToDestructiveMigration()
    .build()

    @Provides
    @Singleton
    fun ProvideDao(db: RecipeDatabse)= db.dao

    @Provides
    @Singleton
    fun ProvideEntity() = RecipeEntity()

}

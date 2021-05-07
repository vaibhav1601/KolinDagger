package com.example.codingassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.codingassignment.data.dto.RepoDAO
import com.example.codingassignment.data.model.Repo

@Database(
    entities = [Repo::class],
    version = 1,exportSchema = false
)

abstract class GitRepoDataBase : RoomDatabase()  {
    abstract fun getRepoDTO():RepoDAO


}


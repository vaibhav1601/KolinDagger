package com.example.codingassignment.data.dto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.codingassignment.data.model.Repo

@Dao
interface RepoDAO {

    @Query("SELECT count(id) FROM items") // items is the table in the @Entity tag of ItemsYouAreStoringInDB.kt, id is a primary key which ensures each entry in DB is unique
    suspend fun numberOfItemsInDB() : Int // suspend keyword to run in coroutine


    @Query("SELECT * FROM items")
    suspend fun getAll(): List<Repo>

    @Insert
    suspend fun insertAll(users: List<Repo>)

    @Delete
    suspend fun delete(user: Repo)
}
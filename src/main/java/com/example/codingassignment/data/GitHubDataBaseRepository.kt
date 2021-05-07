package com.example.codingassignment.data

import com.example.codingassignment.data.dto.RepoDAO
import com.example.codingassignment.data.model.Repo
import javax.inject.Inject

class GitHubDataBaseRepository @Inject constructor(
    private val repoDAO: RepoDAO

) {

    suspend fun numberOfItemsInDB() = repoDAO.numberOfItemsInDB()

     suspend fun insertAll(repo: List<Repo>) = repoDAO.insertAll(repo)


}
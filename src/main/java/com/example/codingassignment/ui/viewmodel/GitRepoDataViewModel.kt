package com.example.codingassignment.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject

import androidx.lifecycle.ViewModel
import com.example.codingassignment.data.GitHubDataBaseRepository
import com.example.codingassignment.data.model.Repo


class GitRepoDataViewModel @ViewModelInject constructor(
    private val repository: GitHubDataBaseRepository
) : ViewModel() {
    suspend fun databaseSize(): Int {
        return repository.numberOfItemsInDB()
    }

    suspend fun insertAllData(repo: List<Repo>) {

        return repository.insertAll(repo);

    }

}






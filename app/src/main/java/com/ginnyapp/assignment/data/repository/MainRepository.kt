package com.ginnyapp.assignment.data.repository

import com.ginnyapp.assignment.data.remote.RemoteDataSource
import com.ginnyapp.assignment.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun fetchNumbers() = remoteDataSource.getNumbers()

}
package com.ginnyapp.assignment.data.repository

import android.util.Log
import com.ginnyapp.assignment.data.remote.DataResponse
import com.ginnyapp.assignment.data.remote.RemoteDataSource
import com.ginnyapp.assignment.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private const val TAG = "MainRepository"

@Singleton
class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchNumbers(): Resource<DataResponse> {
        return withContext(ioDispatcher) {
            remoteDataSource.getNumbers()
        }
    }

}
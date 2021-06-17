package com.ginnyapp.assignment.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
        private val remoteServiceApi: RemoteServiceApi
) : BaseDataSource() {

    suspend fun getNumbers() = getResult { remoteServiceApi.getAllNumbers() }

}
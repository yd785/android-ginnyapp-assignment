package com.ginnyapp.assignment.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface RemoteServiceApi {

    companion object {
        const val BASE_URL = "https://pastebin.com"
    }

    @GET("/raw/8wJzytQX")
    suspend fun getAllNumbers() : Response<DataResponse>

}
package com.example.app4.network

import com.example.app4.Model.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface JokesService {

    @GET(RANDOM_PATH)
    suspend fun getJoke(
        @Path("uId") uId: Int? = 1,
        @Query("firstName")firstName :String? = null,
        @Query("lastName")lastName :String? = null
    ): Response<JokesResponse>

    companion object{
        //http://api.icndb.com/jokes/random/1?firstName=David&lastName=Gonzalez

        const val BASE_URL = "http://api.icndb.com/jokes/"
        private const val RANDOM_PATH = "random/{uId}"
    }
}
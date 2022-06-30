package com.example.app4.network

import com.example.app4.Model.JokeResponse
import com.example.app4.Model.Jokes
import com.example.app4.Model.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface JokesService {

    @GET(RANDOM_PATH)
    suspend fun getJoke(
    ): Response<JokeResponse>

    @GET(RANDOM_PATH)
    suspend fun getJokeNames(
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String
    ): Response<JokeResponse>

    @GET(ENDING_LIST_PATH)
    suspend fun getJokeList20(
    ): Response<JokeResponse>

    companion object{
        //http://api.icndb.com/jokes/random/1?firstName=David&lastName=Gonzalez

        const val BASE_URL = "http://api.icndb.com/jokes/"
        private const val RANDOM_PATH = "random/"
        private const val ENDING_LIST_PATH = "random/10"

    }
}
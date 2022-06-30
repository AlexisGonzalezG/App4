package com.example.app4.network

import com.example.app4.Model.JokeResponse
import com.example.app4.Model.JokesResponse
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

interface JokesRepository {
    suspend fun getJoke(): Response<JokeResponse>

    suspend fun getJokeName(
        first_Name: String,
        last_Name: String
    ): Response<JokeResponse>

    suspend fun getJokeList20(): Response<JokeResponse>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesService : JokesService
) : JokesRepository{

    override suspend fun getJoke(): Response<JokeResponse> =
        jokesService.getJoke()

    override suspend fun getJokeName(first_Name:String,last_Name:String): Response<JokeResponse> =
        jokesService.getJokeNames(first_Name,last_Name)

    override suspend fun getJokeList20(): Response<JokeResponse> =
        jokesService.getJokeList20()
}
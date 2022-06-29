package com.example.app4.network

import com.example.app4.Model.JokesResponse
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

interface JokesRepository {
    suspend fun getJoke(
        uId: Int = 1,
        firstName :String? = null,
        lastName :String? = null
    ):Response<JokesResponse>
}

class JokesRepositoryImpl @Inject constructor(
    private val jokesService : JokesService
) : JokesRepository{

    override suspend fun getJoke(uId: Int,firstName: String?,lastName: String?): Response<JokesResponse> =
        jokesService.getJoke(uId,firstName,lastName)

//        return uId.let {
//            jokesService.getJoke(uId,firstName,lastName)
//        } ?:jokesService.getJoke()




}
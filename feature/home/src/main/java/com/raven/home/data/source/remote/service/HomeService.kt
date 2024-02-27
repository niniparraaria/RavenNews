package com.raven.home.data.source.remote.service

import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("svc/mostpopular/v2/emailed/{period}.json")
    suspend fun getNews(@Path("period") period:String): NewsServiceResponse
}

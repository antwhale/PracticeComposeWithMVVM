package io.github.antwhale.compose.api

import io.github.antwhale.compose.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {
    @GET("/v3/b/65ec0731266cfc3fde95d1f2?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<TweetListItem>>

    @GET("/v3/b/65ec0731266cfc3fde95d1f2?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : Response<List<String>>
}

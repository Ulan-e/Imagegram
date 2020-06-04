package ulanapp.imagegram.data.api

import ulanapp.imagegram.data.model.Result
import retrofit2.Call
import retrofit2.http.GET

/**
 * API KEY = 563492ad6f917000010000016f8f084d5a7f487a8f9b64ee49229734
 *
 */

interface PexelsApiService {

    @GET("curated?per_page=15&page=1")
    fun getResult(): Call<Result>
}
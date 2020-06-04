package ulanapp.imagegram.data.repository

import androidx.lifecycle.MutableLiveData
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ulanapp.imagegram.data.api.PexelsApiService
import ulanapp.imagegram.data.model.Result

class PexelsRepositoryImpl : PexelsRepository {

    private val BASE_URL = "https://api.pexels.com/v1/"
    private val API_KEY = "563492ad6f917000010000016f8f084d5a7f487a8f9b64ee49229734"
    private var mApiService: PexelsApiService

    constructor() {
        val okHttpClient = OkHttpClient.Builder()
            .authenticator(object : Authenticator {
                override fun authenticate(route: Route?, response: Response): Request? {
                    val request: Request = response.request()
                    if (request.header("Authorization") != null) {
                        return null
                    }
                    return request.newBuilder()
                        .header("Authorization", API_KEY)
                        .build()
                }
            })
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        mApiService = retrofit.create(PexelsApiService::class.java)
    }

    override fun getPhotos(query: String): MutableLiveData<Result> {
        val liveData: MutableLiveData<Result> = MutableLiveData()
        val call = mApiService.getResult()
        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                liveData.value = null
            }

            override fun onResponse(call: Call<Result>, response: retrofit2.Response<Result>) {
                liveData.value = response.body()
            }

        })
        return liveData
    }

}
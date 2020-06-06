package ulanapp.imagegram.data.repository

import androidx.lifecycle.MutableLiveData
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ulanapp.imagegram.data.api.ApiService
import ulanapp.imagegram.data.model.PhotosResponse

class PixabayRepositoryImpl : Repository {

    private val BASE_URL = "https://pixabay.com/"
    private val API_KEY = "12507765-afe1775f327e2cb1464aa7821"
    private var mApiService: ApiService

    constructor() {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        mApiService = retrofit.create(ApiService::class.java)
    }

    override fun getPhotos(query: String): MutableLiveData<PhotosResponse> {
        val liveData: MutableLiveData<PhotosResponse> = MutableLiveData()
        val call = mApiService.getPhotos(API_KEY, query, 15)
        call?.enqueue(object : Callback<PhotosResponse> {
            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                liveData.value = null
            }

            override fun onResponse(call: Call<PhotosResponse>, response: retrofit2.Response<PhotosResponse>) {
                liveData.value = response.body()
            }

        })
        return liveData
    }

}
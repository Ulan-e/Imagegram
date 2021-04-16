package ulanapp.imagegram.data.repository

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ulanapp.imagegram.data.api.ApiService
import ulanapp.imagegram.data.model.PhotosResponse
import ulanapp.imagegram.helpers.IMAGE_TYPE_ALL
import ulanapp.imagegram.helpers.LATEST
import ulanapp.imagegram.helpers.POPULAR

class PixabayRepositoryImpl : Repository {

    companion object{
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "12507765-afe1775f327e2cb1464aa7821"
    }

    private var mApiService: ApiService

    constructor() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        mApiService = retrofit.create(ApiService::class.java)
    }

    override fun getPhotos(isPopular: Boolean, query: String): Observable<PhotosResponse> {
        return mApiService.getPhotos(
            API_KEY,
            IMAGE_TYPE_ALL,
            getLatestTitle(isPopular),
            30,
            query)
    }

    private fun getLatestTitle(isPopular: Boolean): String{
        return if(isPopular){
            POPULAR
        }else{
            LATEST
        }
    }
}
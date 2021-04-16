package ulanapp.imagegram.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ulanapp.imagegram.data.model.PhotosResponse

/**
 * API KEY = 563492ad6f917000010000016f8f084d5a7f487a8f9b64ee49229734
 *
 */
interface ApiService {

    @GET("api/")
    fun getPhotos(
        @Query("key") key: String?,
        @Query("image_type") imageType: String?,
        @Query("order") order: String?,
        @Query("per_page") page: Int,
        @Query("q") query: String?
    ): Observable<PhotosResponse>
}
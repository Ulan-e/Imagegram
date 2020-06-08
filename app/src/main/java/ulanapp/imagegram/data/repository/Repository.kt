package ulanapp.imagegram.data.repository

import io.reactivex.Observable
import ulanapp.imagegram.data.model.PhotosResponse

interface Repository {

    fun getPhotos(isPopular: Boolean, query: String): Observable<PhotosResponse>
}
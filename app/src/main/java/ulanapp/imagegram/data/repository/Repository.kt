package ulanapp.imagegram.data.repository

import androidx.lifecycle.MutableLiveData
import ulanapp.imagegram.data.model.PhotosResponse

interface Repository {

    fun getPhotos(query: String): MutableLiveData<PhotosResponse>
}
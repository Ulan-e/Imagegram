package ulanapp.imagegram.data.repository

import androidx.lifecycle.MutableLiveData
import ulanapp.imagegram.data.model.Result

interface PexelsRepository {

    fun getPhotos(query: String): MutableLiveData<Result>
}
package ulanapp.imagegram.listeners

import androidx.lifecycle.LiveData
import ulanapp.imagegram.data.model.PhotosResponse

interface OnChangePhotoResponseListener {

    // смена списка фотографий
    fun onChange(photos: LiveData<PhotosResponse>)
}